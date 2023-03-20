package com.cuduchoa.ThiTracNghiem.controller;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.cuduchoa.ThiTracNghiem.mapper.ClassMapper;
import com.cuduchoa.ThiTracNghiem.mapper.Class_detailMapper;
import com.cuduchoa.ThiTracNghiem.mapper.NguoidungMapper;
import com.cuduchoa.ThiTracNghiem.mapper.QuestionMapper;
import com.cuduchoa.ThiTracNghiem.mapper.ResultMapper;
import com.cuduchoa.ThiTracNghiem.mapper.SubjectMapper;
import com.cuduchoa.ThiTracNghiem.mapper.TestMapper;
import com.cuduchoa.ThiTracNghiem.model.Class;
import com.cuduchoa.ThiTracNghiem.model.ClassExample;
import com.cuduchoa.ThiTracNghiem.model.Class_detailKey;
import com.cuduchoa.ThiTracNghiem.model.Class_detailExample;
import com.cuduchoa.ThiTracNghiem.model.Nguoidung;
import com.cuduchoa.ThiTracNghiem.model.NguoidungExample;
import com.cuduchoa.ThiTracNghiem.model.Question;
import com.cuduchoa.ThiTracNghiem.model.QuestionExample;
import com.cuduchoa.ThiTracNghiem.model.Result;
import com.cuduchoa.ThiTracNghiem.model.ResultExample;
import com.cuduchoa.ThiTracNghiem.model.Subject;
import com.cuduchoa.ThiTracNghiem.model.SubjectExample;
import com.cuduchoa.ThiTracNghiem.model.Test;
import com.cuduchoa.ThiTracNghiem.model.TestExample;

@Controller
public class HomeController {
	
	@Autowired
	NguoidungMapper nguoidungMapper;
	

	@Autowired
	ClassMapper classMapper;
	
	@Autowired
	Class_detailMapper classDetailMapper;
	
	@Autowired
	SubjectMapper subjectMapper;
	
	@Autowired
	TestMapper testMapper;
	
	@Autowired
	ResultMapper resultMapper;
	
	@GetMapping("/home")
	public ModelAndView home(@CookieValue(value="userId", defaultValue = "ngu") String userId , @CookieValue(value="password", defaultValue = "ngu") String password, ModelMap modelMap) {
		ModelAndView modelAndView = new ModelAndView();
		if(userId.equals("ngu") == true && password.equals("ngu") == true) {
			modelAndView.setViewName("login"); //nếu chưa có cookie thì về trang đăng nhập
		}
		else { 
			NguoidungExample nguoiDungExample = new NguoidungExample(); 
			nguoiDungExample.createCriteria().andNguoidungIdEqualTo(userId).andNguoidungPasswordEqualTo(password).andNguoidungStateEqualTo(true); //kiểm tra tk, mk
			List<Nguoidung> listNguoiDung = nguoidungMapper.selectByExample(nguoiDungExample);
			if (listNguoiDung.size() > 0) {
				Nguoidung nguoiDung = nguoidungMapper.selectByPrimaryKey(userId);
				modelMap.addAttribute("userName", nguoiDung.getNguoidungFullName()); //lấy họ tên
				modelMap.addAttribute("userRole", nguoiDung.getNguoidungRole()); //lấy chức vụ
				SubjectExample subjectExample = new SubjectExample();
				List<Subject> listSubject = subjectMapper.selectByExample(subjectExample);
				modelMap.addAttribute("listSubject", listSubject); //lấy danh sách môn học
				if (listNguoiDung.get(0).getNguoidungRole().equals("student") == true) { //nếu là hs
					Class_detailExample classDetailExample = new Class_detailExample();
					classDetailExample.createCriteria().andNguoidungIdEqualTo(userId); 
					List<Class_detailKey> listClassDetail = classDetailMapper.selectByExample(classDetailExample); //lấy các lớp mà người này tham gia
					String className = null;
					if (listClassDetail.size() > 0) {
						for (int i = 0; i < listClassDetail.size(); i ++) {
							Class classz = classMapper.selectByPrimaryKey(listClassDetail.get(i).getClassId());
							if(classz.getClassState().equals(true)) { //kt trạng thái còn hoạt động hay k
								className = classz.getClassName(); //lấy tên lớp đó
								break;
							}
						}
					}
					String grade = Character.toString(className.charAt(0)); //lấy tên khối
					TestExample testExample = new TestExample();
					long millis = System.currentTimeMillis();   
					java.sql.Date date = new java.sql.Date(millis); 
					testExample.createCriteria().andTestGradeEqualTo(grade).andTestDateEqualTo(date).andTestStateEqualTo(true);
					List<Test> listTest = testMapper.selectByExample(testExample); 
					List<Test> listTest1 =  new ArrayList<Test>();
					if (listTest.size() > 0) {
						for (int i =0; i < listTest.size(); i ++) {
							ResultExample resultExample = new ResultExample();
							resultExample.createCriteria().andNguoidungIdEqualTo(userId).andTestIdEqualTo(listTest.get(i).getTestId());
							List<Result> listResult = resultMapper.selectByExample(resultExample);
							if(listResult.isEmpty()) { //kiểm tra người dùng đã làm bài kiểm tra này chưa
								listTest1.add(listTest.get(i)); //lấy ds các bài kt trong ngày
							}
						}
						modelMap.addAttribute("listTest", listTest1); 
					}
					modelAndView.setViewName("student-homepage");
				}
				else {
					modelAndView.setViewName("redirect:/account-list?userRole=student&order=null");
				}
			}
			else { //nếu sai thì báo lỗi
				modelAndView.setViewName("redirect:/login?fail");
			}
		}
		return modelAndView;
	}

	
	@GetMapping("/account-information")
	public ModelAndView accountInformation(@CookieValue(value="userId", defaultValue = "ngu") String userId ,ModelMap modelMap) throws ParseException
	{
		SubjectExample subjectExample = new SubjectExample();
		List<Subject> listSubject = subjectMapper.selectByExample(subjectExample);
		modelMap.addAttribute("listSubject", listSubject); //lấy ds các môn học
		ModelAndView modelAndView = new ModelAndView("account-information");
		Nguoidung nguoiDung = nguoidungMapper.selectByPrimaryKey(userId); //tìm người dùng theo mã và gán giá trị vào các biến
		modelMap.addAttribute("userId", nguoiDung.getNguoidungId());
		modelMap.addAttribute("userName", nguoiDung.getNguoidungFullName());
		modelMap.addAttribute("userGmail", nguoiDung.getNguoidungEmail());
		modelMap.addAttribute("userPhoneNumber", nguoiDung.getNguoidungPhoneNumber());
		String pattern = "dd-MM-yyyy";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		String date = simpleDateFormat.format(nguoiDung.getNguoidungDateOfBirth());
		modelMap.addAttribute("userDateOfBirth", date);
		modelMap.addAttribute("userAddress", nguoiDung.getNguoidungAddress());
		modelMap.addAttribute("userGender", nguoiDung.getNguoidungGender());
		modelMap.addAttribute("userImage", nguoiDung.getNguoidungImage());
		modelMap.addAttribute("userRole", nguoiDung.getNguoidungRole());
		Class_detailExample classDetailExample = new Class_detailExample();
		classDetailExample.createCriteria().andNguoidungIdEqualTo(userId); //tìm lớp mà người này đang tham gia
		List<Class_detailKey> listClassDetail = classDetailMapper.selectByExample(classDetailExample);
		if (listClassDetail.size() > 0) {
			for (int i = 0; i < listClassDetail.size(); i ++) {
				Class classz = classMapper.selectByPrimaryKey(listClassDetail.get(i).getClassId());
				if(classz.getClassState().equals(true)) {
					modelMap.addAttribute("className", classz.getClassName());
					modelMap.addAttribute("classYear", classz.getClassYear());
					break;
				}
			}
		}
		return modelAndView;
	}
	
	@PostMapping("/account-information")
	public ModelAndView accountInformation(@ModelAttribute("userGender") String userGender, @ModelAttribute("userAddress") String userAddress
			,@ModelAttribute("userPhoneNumber") String userPhoneNumber,@ModelAttribute("userGmail") String userGmail,@RequestParam("userImage") MultipartFile userImage
			,@CookieValue(value="userId", defaultValue = "ngu") String userId,ModelMap modelMap) throws IOException
	{
		String fileName = org.springframework.util.StringUtils.cleanPath(userImage.getOriginalFilename()); //lấy tên file hình ảnh
		Nguoidung nguoiDung = nguoidungMapper.selectByPrimaryKey(userId); //tìm người dùng theo mã
		if (fileName.equals("") == false) {
			nguoiDung.setNguoidungImage("/images/" + fileName);
			String uploadDir = "./src/main/resources/static/images";
			Path uploadPath = Paths.get(uploadDir);
			if (!Files.exists(uploadPath)) {
	            Files.createDirectories(uploadPath); //nếu chưa tồn tại thì tạo mới
	        }
	        try (InputStream inputStream = userImage.getInputStream()) {
	            Path filePath = uploadPath.resolve(fileName);
	            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING); //nếu đã tồn tại thì thay thế
	        } catch (IOException ioe) {        
	            throw new IOException("Could not save image file: " + fileName, ioe);
	        }  
		}
		nguoiDung.setNguoidungAddress(userAddress);
		nguoiDung.setNguoidungPhoneNumber(userPhoneNumber);
		nguoiDung.setNguoidungGender(userGender);
		nguoiDung.setNguoidungEmail(userGmail);
		nguoidungMapper.updateByPrimaryKey(nguoiDung); //cập nhật lại thông tin người dùng
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("redirect:/account-information?success");
		return modelAndView;
	}
}
