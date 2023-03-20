package com.cuduchoa.ThiTracNghiem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.cuduchoa.ThiTracNghiem.mapper.ClassMapper;
import com.cuduchoa.ThiTracNghiem.mapper.NguoidungMapper;
import com.cuduchoa.ThiTracNghiem.mapper.SubjectMapper;
import com.cuduchoa.ThiTracNghiem.model.Class;
import com.cuduchoa.ThiTracNghiem.model.ClassExample;
import com.cuduchoa.ThiTracNghiem.model.Nguoidung;
import com.cuduchoa.ThiTracNghiem.model.Subject;
import com.cuduchoa.ThiTracNghiem.model.SubjectExample;
@Controller
public class ClassController {
	@Autowired
	ClassMapper classMapper;
	
	@Autowired
	NguoidungMapper nguoidungMapper;
	
	@Autowired
	SubjectMapper subjectMapper;
	
	@GetMapping("/class-list")
	public ModelAndView classList(@CookieValue(value="userId", defaultValue = "ngu") String userId , ModelMap modelMap) {
		ModelAndView modelAndView = new ModelAndView();
		Nguoidung nguoiDung = nguoidungMapper.selectByPrimaryKey(userId); //tìm người dùng theo mã
		SubjectExample subjectExample = new SubjectExample();
		List<Subject> listSubject = subjectMapper.selectByExample(subjectExample);
		modelMap.addAttribute("listSubject", listSubject);	//lấy ds môn học
		if (nguoiDung.getNguoidungRole().equals("student") == false) {
			modelMap.addAttribute("userName", nguoiDung.getNguoidungFullName());
			modelMap.addAttribute("userRole", nguoiDung.getNguoidungRole());
			ClassExample classExample = new ClassExample();
			classExample.createCriteria().andClassNameNotEqualTo("None");
			List<com.cuduchoa.ThiTracNghiem.model.Class> listClass = classMapper.selectByExample(classExample); //lấy ds lớp học
			modelMap.addAttribute("listClass", listClass); 
			modelAndView.setViewName("class-list");
		}
		else {
			modelAndView.setViewName("redirect:/home");
		}
		return modelAndView;
	}
	
	@GetMapping("/create-class")
	public ModelAndView createClass(@CookieValue(value="userId", defaultValue = "ngu") String userId , ModelMap modelMap) {
		ModelAndView modelAndView = new ModelAndView();
		Nguoidung nguoiDung = nguoidungMapper.selectByPrimaryKey(userId); //tìm người dùng theo mã
		SubjectExample subjectExample = new SubjectExample();
		List<Subject> listSubject = subjectMapper.selectByExample(subjectExample);
		modelMap.addAttribute("listSubject", listSubject); //lấy ds môn học
		if (nguoiDung.getNguoidungRole().equals("student") == false) {
			modelMap.addAttribute("userName", nguoiDung.getNguoidungFullName());
			modelMap.addAttribute("userRole", nguoiDung.getNguoidungRole());
			modelAndView.setViewName("create-class");
		}
		else {
			modelAndView.setViewName("redirect:/home");
		}
		return modelAndView;
	}
	
	@PostMapping("/create-class")
	public ModelAndView createClass(@CookieValue(value="userId", defaultValue = "ngu") String userId 
			,@ModelAttribute("className") String className
			,@ModelAttribute("classYear") String classYear
			,@ModelAttribute("classState") String classState
			, ModelMap modelMap) {
		ModelAndView modelAndView = new ModelAndView();
		Nguoidung nguoiDung = nguoidungMapper.selectByPrimaryKey(userId); //tìm người dùng theo mã
		SubjectExample subjectExample = new SubjectExample();
		List<Subject> listSubject = subjectMapper.selectByExample(subjectExample);
		modelMap.addAttribute("listSubject", listSubject); //lấy ds môn học
		if (nguoiDung.getNguoidungRole().equals("student") == false) {
			modelMap.addAttribute("userName", nguoiDung.getNguoidungFullName());
			modelMap.addAttribute("userRole", nguoiDung.getNguoidungRole());
			com.cuduchoa.ThiTracNghiem.model.Class classz = new  Class();
			classz.setClassName(className);
			classz.setClassYear(classYear);
			if(classState.equals("true")) {
				classz.setClassState(true);
			}
			else if(classState.equals("false")) {
				classz.setClassState(false);
			}
			classMapper.insert(classz);
			modelAndView.setViewName("redirect:/class-list");
		}
		else {
			modelAndView.setViewName("redirect:/home");
		}
		return modelAndView;
	}
	
	@GetMapping("/change-class-information")
	public ModelAndView changeClassInformation(@CookieValue(value="userId", defaultValue = "ngu") String userId,@RequestParam("classId") Long classId , ModelMap modelMap) {
		ModelAndView modelAndView = new ModelAndView();
		Nguoidung nguoiDung = nguoidungMapper.selectByPrimaryKey(userId); //tìm người dùng theo mã
		SubjectExample subjectExample = new SubjectExample();
		List<Subject> listSubject = subjectMapper.selectByExample(subjectExample);
		modelMap.addAttribute("listSubject", listSubject); //lấy ds môn học
		if (nguoiDung.getNguoidungRole().equals("student") == false) {
			modelMap.addAttribute("userName", nguoiDung.getNguoidungFullName());
			modelMap.addAttribute("userRole", nguoiDung.getNguoidungRole());
			Class classz = classMapper.selectByPrimaryKey(classId); //tìm lớp học
			modelMap.addAttribute("classz", classz);
			modelAndView.setViewName("change-class-information");
		}
		else {
			modelAndView.setViewName("redirect:/home");
		}
		return modelAndView;
	}
	
	@PostMapping("/change-class-information")
	public ModelAndView changeClassInformation(@CookieValue(value="userId", defaultValue = "ngu") String userId 
			,@ModelAttribute("classId") Long classId
			,@ModelAttribute("className") String className
			,@ModelAttribute("classYear") String classYear
			,@ModelAttribute("classState") String classState
			, ModelMap modelMap) {
		ModelAndView modelAndView = new ModelAndView();
		Nguoidung nguoiDung = nguoidungMapper.selectByPrimaryKey(userId); //tìm người dùng theo mã
		SubjectExample subjectExample = new SubjectExample();
		List<Subject> listSubject = subjectMapper.selectByExample(subjectExample);
		modelMap.addAttribute("listSubject", listSubject); //lấy ds môn học
		if (nguoiDung.getNguoidungRole().equals("student") == false) {
			modelMap.addAttribute("userName", nguoiDung.getNguoidungFullName());
			modelMap.addAttribute("userRole", nguoiDung.getNguoidungRole());
			Class classz = classMapper.selectByPrimaryKey(classId);
			classz.setClassName(className);
			classz.setClassYear(classYear);
			if(classState.equals("true")) {
				classz.setClassState(true);
			}
			else if(classState.equals("false")) {
				classz.setClassState(false);
			}
			classMapper.updateByPrimaryKey(classz);
			modelAndView.setViewName("redirect:/class-list");
		}
		else {
			modelAndView.setViewName("redirect:/home");
		}
		return modelAndView;
	}
}
