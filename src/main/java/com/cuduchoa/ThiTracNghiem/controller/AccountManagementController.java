package com.cuduchoa.ThiTracNghiem.controller;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
import com.cuduchoa.ThiTracNghiem.mapper.ResultMapper;
import com.cuduchoa.ThiTracNghiem.mapper.SubjectMapper;
import com.cuduchoa.ThiTracNghiem.model.Class;
import com.cuduchoa.ThiTracNghiem.model.ClassExample;
import com.cuduchoa.ThiTracNghiem.model.Class_detailKey;
import com.cuduchoa.ThiTracNghiem.model.Class_detailExample;
import com.cuduchoa.ThiTracNghiem.model.Nguoidung;
import com.cuduchoa.ThiTracNghiem.model.NguoidungExample;
import com.cuduchoa.ThiTracNghiem.model.Subject;
import com.cuduchoa.ThiTracNghiem.model.SubjectExample;

@Controller
public class AccountManagementController {
	@Autowired
	NguoidungMapper nguoidungMapper;
	

	@Autowired
	ClassMapper classMapper;
	
	@Autowired
	Class_detailMapper classDetailMapper;
	
	@Autowired
	SubjectMapper subjectMapper;
	
	@Autowired
	ResultMapper resultlMapper; 
	
	@GetMapping("/account-list")
	public ModelAndView accountList(@RequestParam("userRole") String userRole,@RequestParam("order") String order,@CookieValue(value="userId", defaultValue = "ngu") String userId , ModelMap modelMap) {
		ModelAndView modelAndView = new ModelAndView();
		Nguoidung nguoiDung = nguoidungMapper.selectByPrimaryKey(userId); 
		SubjectExample subjectExample = new SubjectExample();
		List<Subject> listSubject = subjectMapper.selectByExample(subjectExample);
		modelMap.addAttribute("listSubject", listSubject); //lấy ds môn học
		if (nguoiDung.getNguoidungRole().equals("student") == false) {
			modelMap.addAttribute("userName", nguoiDung.getNguoidungFullName()); //lấy họ tên
			modelMap.addAttribute("userRole", userRole); //chức vụ của tk
			modelMap.addAttribute("Role", nguoiDung.getNguoidungRole()); //chức vụ của người đang dùng hệ thống
			List<Map<String,Object>> listNguoiDung;
			if (userRole.equals("student") == true) {
					if (order.equals("className") == true)
					{
						listNguoiDung = nguoidungMapper.getAllStudentOrderByClassName();
					}
					else if(order.equals("userState") == true) {
						listNguoiDung = nguoidungMapper.getAllStudentOrderByUserState();
					}
					else {
						listNguoiDung = nguoidungMapper.getAllStudent();
					}
			}
			else {
				if (order.equals("className") == true)
				{
					listNguoiDung = nguoidungMapper.getAllTeacherOrderByClassName();
				}
				else if(order.equals("userState") == true) {
					listNguoiDung = nguoidungMapper.getAllTeacherOrderByUserState();
				}
				else {
					listNguoiDung = nguoidungMapper.getAllTeacher();
				}
			}
			modelMap.addAttribute("listAccount", listNguoiDung);
			modelAndView.setViewName("account-list");
		}
		else {
			modelAndView.setViewName("redirect:/home");
		}
		return modelAndView;
	}
	
	@GetMapping("/create-account")
	public ModelAndView createAccount(@CookieValue(value="userId", defaultValue = "ngu") String userId , ModelMap modelMap) {
		ModelAndView modelAndView = new ModelAndView();
		Nguoidung nguoiDung = nguoidungMapper.selectByPrimaryKey(userId); //tìm người dùng theo id
		SubjectExample subjectExample = new SubjectExample();
		List<Subject> listSubject = subjectMapper.selectByExample(subjectExample);
		modelMap.addAttribute("listSubject", listSubject); //lấy ds lớp học
		if (nguoiDung.getNguoidungRole().equals("student") == false) {
			modelMap.addAttribute("userName", nguoiDung.getNguoidungFullName()); //họ tên người dùng
			modelMap.addAttribute("userRole", nguoiDung.getNguoidungRole()); //chức vụ người dùng
			ClassExample classExample = new ClassExample();
			classExample.createCriteria().andClassNameNotEqualTo("None").andClassStateEqualTo(true);
			List<Class> listClass = classMapper.selectByExample(classExample);
			modelMap.addAttribute("listClass", listClass); //lấy ds lớp học
			modelAndView.setViewName("create-account");
		}
		else {
			modelAndView.setViewName("redirect:/home");
		}
		return modelAndView;
	}
	
	@PostMapping("/create-account")
	public ModelAndView createAccount(
			@ModelAttribute("userDateOfBirth") String userDateOfBirth
			,@ModelAttribute("userGmail") String userGmail
			,@ModelAttribute("accountName") String accountName
			,@ModelAttribute("userGender") String userGender
			,@ModelAttribute("userAddress") String userAddress
			,@ModelAttribute("userPhoneNumber") String userPhoneNumber
			,@RequestParam("userImage") MultipartFile userImage
			,@ModelAttribute("classId") Long classId
			,@ModelAttribute("accountRole") String accountRole
			,@CookieValue(value="userId", defaultValue = "ngu") String userId, ModelMap modelMap) throws IOException, ParseException
	{
		
        ModelAndView modelAndView = new ModelAndView();
        Class_detailExample classDetailExample = new Class_detailExample();
		classDetailExample.createCriteria().andClassIdEqualTo(classId);
		List<Class_detailKey> listClassDetail = classDetailMapper.selectByExample(classDetailExample); //tìm lớp trùng mã với lớp đc chọn
		long a = 0;
		if (listClassDetail.size() > 0) {
			for (int i = 0; i < listClassDetail.size(); i ++) {
				Nguoidung nguoiDung1 = nguoidungMapper.selectByPrimaryKey(listClassDetail.get(i).getNguoidungId());
				if(nguoiDung1.getNguoidungRole().equals("teacher") && nguoiDung1.getNguoidungState().equals(true)) {
					a ++; //nếu lớp đó đã có giáo viên thì tăng a
					break;
				}
			}
		}
       if(a > 0 && accountRole.equals("teacher") == true) { //nếu cố tình tạo thêm 1 giáo viên trong lớp đã có giáo viên thì trả về fail
    	   modelAndView.setViewName("redirect:/create-account?fail");
       }
       else {
   			String fileName = org.springframework.util.StringUtils.cleanPath(userImage.getOriginalFilename());
   			Nguoidung nguoiDung = new Nguoidung();
   			Class_detailKey classDetail = new Class_detailKey();
   			classDetail.setClassId(classId);
   			if(fileName.equals("") == false) {
   				nguoiDung.setNguoidungImage("/images/" + fileName);
   				String uploadDir = "./src/main/resources/static/images";
   				Path uploadPath = Paths.get(uploadDir);
   				if (!Files.exists(uploadPath)) {
   		            Files.createDirectories(uploadPath);
   		        }
   		        try (InputStream inputStream = userImage.getInputStream()) {
   		            Path filePath = uploadPath.resolve(fileName);
   		            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
   		        } catch (IOException ioe) {        
   		            throw new IOException("Could not save image file: " + fileName, ioe);
   		        }  
   			}
   			else {
   				nguoiDung.setNguoidungImage("/images/userImageDefault.png");
   			}
   			int accountId;
   			int listCount = 0;
   			if (accountRole.equals("student")) {
   				List<Map<String,Object>> listNguoiDung = nguoidungMapper.getAllStudent();
   				listCount = listNguoiDung.size();
   			}
   			else if(accountRole.equals("teacher")) {
   				List<Map<String,Object>> listNguoiDung = nguoidungMapper.getAllTeacher();
   				listCount = listNguoiDung.size();
   			}
   			if (listCount < 9 && accountRole.equals("student")) {
   				accountId = listCount + 1;
   				nguoiDung.setNguoidungId("HS000"+accountId);
   				nguoiDung.setNguoidungPassword("HS000"+accountId);
   				classDetail.setNguoidungId("HS000"+accountId);
   			}
   			else if(listCount > 9 && listCount < 99 && accountRole.equals("student")) {
   				accountId = listCount + 1;
   				nguoiDung.setNguoidungId("HS00"+accountId);
   				nguoiDung.setNguoidungPassword("HS00"+accountId);
   				classDetail.setNguoidungId("HS00"+accountId);
   			}
   			else  if(listCount > 99 && listCount < 999 && accountRole.equals("student")) {
   				accountId = listCount + 1;
   				nguoiDung.setNguoidungId("HS0"+accountId);
   				nguoiDung.setNguoidungPassword("HS0"+accountId);
   				classDetail.setNguoidungId("HS0"+accountId);
   			}
   			else  if(listCount > 999 && listCount < 9999 && accountRole.equals("student")) {
   				accountId = listCount + 1;
   				nguoiDung.setNguoidungId("HS"+accountId);
   				nguoiDung.setNguoidungPassword("HS"+accountId);
   				classDetail.setNguoidungId("HS"+accountId);
   			}
   			if (listCount < 9 && accountRole.equals("teacher")) {
   				accountId = listCount + 1;
   				nguoiDung.setNguoidungId("GV000"+accountId);
   				nguoiDung.setNguoidungPassword("GV000"+accountId);
   				classDetail.setNguoidungId("GV000"+accountId);
   			}
   			else if(listCount > 9 && listCount < 99 && accountRole.equals("teacher")) {
   				accountId = listCount + 1;
   				nguoiDung.setNguoidungId("GV00"+accountId);
   				nguoiDung.setNguoidungPassword("GV00"+accountId);
   				classDetail.setNguoidungId("GV00"+accountId);
   			}
   			else  if(listCount > 99 && listCount < 999 && accountRole.equals("teacher")) {
   				accountId = listCount + 1;
   				nguoiDung.setNguoidungId("GV0"+accountId);
   				nguoiDung.setNguoidungPassword("GV0"+accountId);
   				classDetail.setNguoidungId("GV0"+accountId);
   			}
   			else  if(listCount > 999 && listCount < 9999 && accountRole.equals("teacher")) {
   				accountId = listCount + 1;
   				nguoiDung.setNguoidungId("GV"+accountId);
   				nguoiDung.setNguoidungPassword("GV"+accountId);
   				classDetail.setNguoidungId("GV"+accountId);
   			}
   			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
   			Date date = formatter.parse(userDateOfBirth);
   			nguoiDung.setNguoidungDateOfBirth(date);
   			nguoiDung.setNguoidungEmail(userGmail);
   			nguoiDung.setNguoidungFullName(accountName);
   			nguoiDung.setNguoidungAddress(userAddress);
   			nguoiDung.setNguoidungPhoneNumber(userPhoneNumber);
   			nguoiDung.setNguoidungState(true);
   			nguoiDung.setNguoidungGender(userGender);
   			nguoiDung.setNguoidungRole(accountRole);
   			nguoidungMapper.insert(nguoiDung);
   			classDetailMapper.insert(classDetail);
   	        modelAndView.setViewName("redirect:/account-list?userRole="+accountRole+"&order=null");
       }
		return modelAndView;
	}
	
	@GetMapping("/change-account-information")
	public ModelAndView changeAccountInformation(@CookieValue(value="userId", defaultValue = "null") String userId,@RequestParam("accountId") String accountId ,ModelMap modelMap)
	{
		ModelAndView modelAndView = new ModelAndView();
		Nguoidung nguoiDung = nguoidungMapper.selectByPrimaryKey(userId); //tìm người dùng theo mã
		SubjectExample subjectExample = new SubjectExample();
		List<Subject> listSubject = subjectMapper.selectByExample(subjectExample);
		modelMap.addAttribute("listSubject", listSubject); //lấy ds môn học
		if(nguoiDung.getNguoidungRole().equals("student") == false) 
		{
			Nguoidung nguoiDung1 = nguoidungMapper.selectByPrimaryKey(accountId); //tìm tài khoản theo mã
			modelMap.addAttribute("accountId", nguoiDung1.getNguoidungId());
			Class_detailExample classDetailExample = new Class_detailExample();
			classDetailExample.createCriteria().andNguoidungIdEqualTo(accountId); //tìm lớp mà tài khoản này đã hoặc đang tham gia
			List<Class_detailKey> listClassDetail = classDetailMapper.selectByExample(classDetailExample);
			if (listClassDetail.size() > 0) {
				for (int i = 0; i < listClassDetail.size(); i ++) {
					Class classz = classMapper.selectByPrimaryKey(listClassDetail.get(i).getClassId());
					if(classz.getClassState().equals(true)) { //tìm lớp mà tài khoản này đang tham gia
						modelMap.addAttribute("classId", classz.getClassId());
						modelMap.addAttribute("className", classz.getClassName());
						modelMap.addAttribute("classYear", classz.getClassYear());
						break;
					}
				}
			}
			modelMap.addAttribute("userPassword", nguoiDung1.getNguoidungPassword());
			modelMap.addAttribute("accountName", nguoiDung1.getNguoidungFullName());
			modelMap.addAttribute("userGmail", nguoiDung1.getNguoidungEmail());
			modelMap.addAttribute("userPhoneNumber", nguoiDung1.getNguoidungPhoneNumber());
			String pattern = "yyy-MM-dd";
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
			String date = simpleDateFormat.format(nguoiDung1.getNguoidungDateOfBirth());
			modelMap.addAttribute("userDateOfBirth", date);
			modelMap.addAttribute("userAddress", nguoiDung1.getNguoidungAddress());
			modelMap.addAttribute("userGender", nguoiDung1.getNguoidungGender());
			modelMap.addAttribute("userImage", nguoiDung1.getNguoidungImage());
			modelMap.addAttribute("userState", nguoiDung1.getNguoidungState());
			modelMap.addAttribute("accountRole", nguoiDung1.getNguoidungRole());
			///
			modelMap.addAttribute("userName", nguoiDung.getNguoidungFullName());
			modelMap.addAttribute("userId", nguoiDung.getNguoidungId());
			modelMap.addAttribute("userRole", nguoiDung.getNguoidungRole());
			modelAndView.setViewName("change-account-information");
			ClassExample classExample = new ClassExample();
			classExample.createCriteria().andClassNameNotEqualTo("None").andClassStateEqualTo(true);
			List<Class> listClass = classMapper.selectByExample(classExample);
			modelMap.addAttribute("listClass", listClass);
			List<Map<String, Object>> listResult = resultlMapper.getUserResult(nguoiDung1.getNguoidungId());
			modelMap.addAttribute("listResult", listResult);
		}
		else {
			modelAndView.setViewName("home");
		}
		return modelAndView;
	}
	
	@PostMapping("/change-account-information")
	public ModelAndView changeAccountInformation(@ModelAttribute("accountId") String accountId
			,@ModelAttribute("accountName") String accountName
			,@ModelAttribute("classId") String classId
			,@ModelAttribute("userDateOfBirth") String userDateOfBirth
			,@ModelAttribute("userGender") String userGender
			,@ModelAttribute("userGmail") String userGmail
			,@ModelAttribute("userPhoneNumber") String userPhoneNumber
			,@ModelAttribute("userAddress") String userAddress
			,@RequestParam("userImage") MultipartFile userImage
			,@ModelAttribute("userState") String userState
			,@CookieValue(value="userId", defaultValue = "ngu") String userId, ModelMap modelMap) throws IOException, ParseException
	{
        ModelAndView modelAndView = new ModelAndView();
		String fileName = org.springframework.util.StringUtils.cleanPath(userImage.getOriginalFilename());
		Nguoidung nguoiDung = nguoidungMapper.selectByPrimaryKey(accountId); //tìm tài khoản người dùng theo mã
		if(fileName.equals("") == false) {
			nguoiDung.setNguoidungImage("/images/" + fileName);
			String uploadDir = "./src/main/resources/static/images";
			Path uploadPath = Paths.get(uploadDir);
			if (!Files.exists(uploadPath)) {
	            Files.createDirectories(uploadPath); //tạo mới hình
	        }
	        try (InputStream inputStream = userImage.getInputStream()) {
	            Path filePath = uploadPath.resolve(fileName);
	            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING); //thay thế
	        } catch (IOException ioe) {        
	            throw new IOException("Could not save image file: " + fileName, ioe);
	        }  
		}
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date date = formatter.parse(userDateOfBirth);
		nguoiDung.setNguoidungDateOfBirth(date);
		nguoiDung.setNguoidungEmail(userGmail);
		nguoiDung.setNguoidungFullName(accountName);
		nguoiDung.setNguoidungAddress(userAddress);
		nguoiDung.setNguoidungPhoneNumber(userPhoneNumber);
		if(userState.equals("true") == true) {
			nguoiDung.setNguoidungState(true);
		}
		else {
			nguoiDung.setNguoidungState(false);
		}
		nguoiDung.setNguoidungGender(userGender);
		Class_detailExample classDetailExample = new Class_detailExample();
		classDetailExample.createCriteria().andNguoidungIdEqualTo(accountId);
		List<Class_detailKey> listClassDetail = classDetailMapper.selectByExample(classDetailExample);
		long a = 0;
		if (listClassDetail.size() > 0) {
			for (int i = 0; i < listClassDetail.size(); i ++) {
				Class classz = classMapper.selectByPrimaryKey(listClassDetail.get(i).getClassId());
				if(classz.getClassState().equals(true)) {
					a = classz.getClassId();
					break;
				}
			}
		}
		Class_detailExample classDetailExample1 = new Class_detailExample();
		classDetailExample1.createCriteria().andNguoidungIdEqualTo(accountId).andClassIdEqualTo(a);
		List<Class_detailKey> listClassDetail1 = classDetailMapper.selectByExample(classDetailExample1);
		Class_detailKey classDetailKey = listClassDetail1.get(0);
		long id1 = Long.parseLong(classId);
		classDetailKey.setClassId(id1);
		nguoidungMapper.updateByPrimaryKey(nguoiDung);
		classDetailMapper.updateByExample(classDetailKey, classDetailExample1);
        Nguoidung nguoiDung1 = nguoidungMapper.selectByPrimaryKey(accountId);
        modelAndView.setViewName("redirect:/account-list?userRole="+nguoiDung1.getNguoidungRole()+"&order=null");
		return modelAndView;
	}
}
