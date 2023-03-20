package com.cuduchoa.ThiTracNghiem.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cuduchoa.ThiTracNghiem.mapper.ClassMapper;
import com.cuduchoa.ThiTracNghiem.mapper.Class_detailMapper;
import com.cuduchoa.ThiTracNghiem.mapper.NguoidungMapper;
import com.cuduchoa.ThiTracNghiem.mapper.ResultMapper;
import com.cuduchoa.ThiTracNghiem.mapper.SubjectMapper;
import com.cuduchoa.ThiTracNghiem.mapper.TestMapper;
import com.cuduchoa.ThiTracNghiem.model.Class;
import com.cuduchoa.ThiTracNghiem.model.Class_detailKey;
import com.cuduchoa.ThiTracNghiem.model.Class_detailExample;
import com.cuduchoa.ThiTracNghiem.model.Nguoidung;
import com.cuduchoa.ThiTracNghiem.model.NguoidungExample;
import com.cuduchoa.ThiTracNghiem.model.Result;
import com.cuduchoa.ThiTracNghiem.model.ResultExample;
import com.cuduchoa.ThiTracNghiem.model.Subject;
import com.cuduchoa.ThiTracNghiem.model.SubjectExample;
import com.cuduchoa.ThiTracNghiem.model.Test;
import com.cuduchoa.ThiTracNghiem.model.TestExample;
import com.cuduchoa.ThiTracNghiem.service.SendMailService;


@Controller
public class LoginPageController {
	@Autowired
	NguoidungMapper nguoidungMapper;
	
	@Autowired
	ClassMapper classMapper;
	
	@Autowired
	Class_detailMapper classDetailMapper;
	
	@Autowired
	SendMailService mail;
	
	@Autowired
	SubjectMapper subjectMapper;
	
	@Autowired
	TestMapper testMapper;
	
	@Autowired
	ResultMapper resultMapper;
	
	@GetMapping("/")
	public ModelAndView lazy() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("redirect:/login");
		return modelAndView;
	}

	@GetMapping("/login")
	public ModelAndView login(@CookieValue(value="userId", defaultValue = "ngu") String userId , @CookieValue(value="password", defaultValue = "ngu") String password, ModelMap modelMap) {
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
					java.sql.Date date = new java.sql.Date(millis); //lấy ngày hiện tạo
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
	
	@PostMapping("/home")
	public ModelAndView login(@ModelAttribute("userId") String userId, @ModelAttribute("password") String password, HttpServletResponse response, ModelMap modelMap) throws ParseException {
		ModelAndView modelAndView = new ModelAndView();
		NguoidungExample nguoiDungExample = new NguoidungExample();
		nguoiDungExample.createCriteria().andNguoidungIdEqualTo(userId).andNguoidungPasswordEqualTo(password).andNguoidungStateEqualTo(true); //kiểm tra tk mk
		List<Nguoidung> listNguoiDung = nguoidungMapper.selectByExample(nguoiDungExample); 
		if (listNguoiDung.size() > 0) { //nếu có 
			Nguoidung nguoiDung = nguoidungMapper.selectByPrimaryKey(userId);
			modelMap.addAttribute("userName",nguoiDung.getNguoidungFullName()); //lấy họ tên
			modelMap.addAttribute("userRole", nguoiDung.getNguoidungRole()); //lấy chức vụ
			SubjectExample subjectExample = new SubjectExample();
			List<Subject> listSubject = subjectMapper.selectByExample(subjectExample);
			modelMap.addAttribute("listSubject", listSubject); //lấy ds môn học
			if (listNguoiDung.get(0).getNguoidungRole().equals("student") == true) {
				Class_detailExample classDetailExample = new Class_detailExample();
				classDetailExample.createCriteria().andNguoidungIdEqualTo(userId);
				List<Class_detailKey> listClassDetail = classDetailMapper.selectByExample(classDetailExample); //lấy ds lớp mà người này tham gia
				String className = null;
				if (listClassDetail.size() > 0) {
					for (int i = 0; i < listClassDetail.size(); i ++) {
						Class classz = classMapper.selectByPrimaryKey(listClassDetail.get(i).getClassId());
						if(classz.getClassState().equals(true)) { //kt trạng thái còn hoạt động hay k
							className = classz.getClassName(); //lấy tên lớp
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
					for (int i = 0; i < listTest.size(); i ++) {
						ResultExample resultExample = new ResultExample();
						resultExample.createCriteria().andNguoidungIdEqualTo(userId).andTestIdEqualTo(listTest.get(i).getTestId()); //kiểm tra xem người này đã làm bài này chưa
						List<Result> listResult = resultMapper.selectByExample(resultExample);
						if(listResult.isEmpty()) { 
							listTest1.add(listTest.get(i)); //lấy ds cái bài kiểm tra mà nười này chưa làm
						}
					}
					modelMap.addAttribute("listTest", listTest1);
				}
				modelAndView.setViewName("student-homepage");
			}
			else { //nếu chưa
				modelAndView.setViewName("redirect:/account-list?userRole=student&order=null");
			}
			Cookie cookie = new Cookie("userId", userId); //thêm vào cookie
			cookie.setMaxAge(3 * 60 * 60);
			response.addCookie(cookie);
			Cookie cookie1 = new Cookie("password", password);
			cookie1.setMaxAge(3 * 60 * 60);
			response.addCookie(cookie1);
		} 
		else {
			modelAndView.setViewName("redirect:/login?fail");
		}
		return modelAndView;
	}
	
	@GetMapping("/send-mail")
	public ModelAndView sendMail()
	{
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("send-mail");
		return modelAndView;
	}
	
	static String verifyCode;
	@PostMapping("/send-mail")
	public ModelAndView sendMail(@ModelAttribute("userId") String userId, @ModelAttribute("gmail") String gmail) {
		ModelAndView modelAndView = new ModelAndView();
		NguoidungExample nguoiDungExample = new NguoidungExample();
		nguoiDungExample.createCriteria().andNguoidungIdEqualTo(userId).andNguoidungEmailEqualTo(gmail).andNguoidungRoleEqualTo("teacher").andNguoidungStateEqualTo(true);
		List<Nguoidung> listNguoiDung = nguoidungMapper.selectByExample(nguoiDungExample); //kt xem người dùng này có phải giáo viê hay k
		if (listNguoiDung.size() > 0) {
			Random rand = new Random();
			int intRandom = rand.nextInt(100000, 999999); 
			verifyCode = String.valueOf(intRandom);
			mail.sendMail(gmail,userId, verifyCode); //gửi mail
		}
		else {
			modelAndView.setViewName("redirect:/send-mail?fail");
		}
		return modelAndView;
	}
	
	@GetMapping("/change-password")
	public ModelAndView changePassword(@CookieValue(value="userId", defaultValue = "ngu") String userId ,ModelMap modelMap) {
		ModelAndView modelAndView = new ModelAndView();
		if (userId.equals("ngu") == true) { 
			userId = id; 
		}
		Nguoidung nguoiDung = nguoidungMapper.selectByPrimaryKey(userId);
		modelMap.addAttribute("userId", nguoiDung.getNguoidungId());
		return modelAndView;
	}
	
	static String id;
	@PostMapping("/change-password")
	public ModelAndView changePassword(@ModelAttribute("userId") String userId, @ModelAttribute("code") String code) {
		ModelAndView modelAndView = new ModelAndView();
		if (verifyCode.equals(code)) { //kt mã xác nhận
			id = userId;
		}
		else {
			modelAndView.setViewName("redirect:/send-mail?fail");
		}
		return modelAndView;
	}
	
	@PostMapping("/login")
	public ModelAndView loginPage(@ModelAttribute("userId") String userId,@ModelAttribute("password") String password,@ModelAttribute("checkPassword") String checkPassword) {
		ModelAndView modelAndView = new ModelAndView();
		if (password.equals(checkPassword)) { //kiểm tra mk và confirm
			Nguoidung nguoiDung = nguoidungMapper.selectByPrimaryKey(userId);
			nguoiDung.setNguoidungPassword(password);
			nguoidungMapper.updateByPrimaryKey(nguoiDung); //cập nhật lại mk
			modelAndView.setViewName("login");
		}
		else {
			modelAndView.setViewName("redirect:/change-password?fail");
		}
		return modelAndView;
	}
	
	@GetMapping("/logout")
	public ModelAndView logout(HttpServletResponse response)
	{
		Cookie cookie = new Cookie("userId", null);
		cookie.setMaxAge(0);
		response.addCookie(cookie);
		Cookie cookie1 = new Cookie("password", null);
		cookie1.setMaxAge(0);
		response.addCookie(cookie1);
		ModelAndView modelAndView = new ModelAndView("login");
		return modelAndView; 
	}
	
}
