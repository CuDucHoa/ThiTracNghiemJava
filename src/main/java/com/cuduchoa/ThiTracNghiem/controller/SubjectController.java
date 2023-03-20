package com.cuduchoa.ThiTracNghiem.controller;

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
import org.springframework.web.servlet.ModelAndView;

import com.cuduchoa.ThiTracNghiem.mapper.NguoidungMapper;
import com.cuduchoa.ThiTracNghiem.mapper.SubjectMapper;
import com.cuduchoa.ThiTracNghiem.model.Nguoidung;
import com.cuduchoa.ThiTracNghiem.model.Subject;
import com.cuduchoa.ThiTracNghiem.model.SubjectExample;

@Controller
public class SubjectController {
	@Autowired
	SubjectMapper subjectMapper;
	
	@Autowired
	NguoidungMapper nguoidungMapper;
	
	@GetMapping("/subject-list")
	public ModelAndView subjectList(@CookieValue(value="userId", defaultValue = "ngu") String userId , ModelMap modelMap) {
		ModelAndView modelAndView = new ModelAndView();
		Nguoidung nguoiDung = nguoidungMapper.selectByPrimaryKey(userId); //tìm người dùng theo mã
		SubjectExample subjectExample = new SubjectExample();
		List<Subject> listSubject = subjectMapper.selectByExample(subjectExample);
		modelMap.addAttribute("listSubject", listSubject); //lấy ds môn học
		if (nguoiDung.getNguoidungRole().equals("student") == false) {
			modelMap.addAttribute("userName", nguoiDung.getNguoidungFullName());
			modelMap.addAttribute("userRole", nguoiDung.getNguoidungRole());
			modelAndView.setViewName("subject-list");
		}
		else {
			modelAndView.setViewName("redirect:/home");
		}
		return modelAndView;
	}
	
	@GetMapping("/create-subject")
	public ModelAndView createSubject(@CookieValue(value="userId", defaultValue = "ngu") String userId , ModelMap modelMap) {
		ModelAndView modelAndView = new ModelAndView();
		Nguoidung nguoiDung = nguoidungMapper.selectByPrimaryKey(userId); //tìm người dùng theo mã
		SubjectExample subjectExample = new SubjectExample();
		List<Subject> listSubject = subjectMapper.selectByExample(subjectExample);
		modelMap.addAttribute("listSubject", listSubject); //lấy ds môn học
		if (nguoiDung.getNguoidungRole().equals("student") == false) {
			modelMap.addAttribute("userName", nguoiDung.getNguoidungFullName());
			modelMap.addAttribute("userRole", nguoiDung.getNguoidungRole());
			modelAndView.setViewName("create-subject");
		}
		else {
			modelAndView.setViewName("redirect:/home");
		}
		return modelAndView;
	}
	
	@PostMapping("/create-subject")
	public ModelAndView createSubject(@CookieValue(value="userId", defaultValue = "ngu") String userId 
			,@ModelAttribute("subjectName") String subjectName
			,@ModelAttribute("subjectState") String subjectState
			, ModelMap modelMap) {
		ModelAndView modelAndView = new ModelAndView();
		Nguoidung nguoiDung = nguoidungMapper.selectByPrimaryKey(userId); //tìm người dùng theo mã
		SubjectExample subjectExample = new SubjectExample();
		List<Subject> listSubject = subjectMapper.selectByExample(subjectExample);
		modelMap.addAttribute("listSubject", listSubject); //lấy ds môn học
		if (nguoiDung.getNguoidungRole().equals("student") == false) {
			modelMap.addAttribute("userName", nguoiDung.getNguoidungFullName());
			modelMap.addAttribute("userRole", nguoiDung.getNguoidungRole());
			Subject subject = new Subject();
			subject.setSubjectName(subjectName);
			if(subjectState.equals("true")) {
				subject.setSubjectState(true);
			}
			else if(subjectState.equals("false")) {
				subject.setSubjectState(false);
			}
			subjectMapper.insert(subject);
			modelAndView.setViewName("redirect:/subject-list");
		}
		else {
			modelAndView.setViewName("redirect:/home");
		}
		return modelAndView;
	}
	
	@GetMapping("/change-subject-information")
	public ModelAndView changeSubjectInformation(@CookieValue(value="userId", defaultValue = "ngu") String userId,@RequestParam("subjectId") Long subjectId , ModelMap modelMap) {
		ModelAndView modelAndView = new ModelAndView();
		Nguoidung nguoiDung = nguoidungMapper.selectByPrimaryKey(userId); //tìm người dùng theo mã
		SubjectExample subjectExample = new SubjectExample();
		List<Subject> listSubject = subjectMapper.selectByExample(subjectExample);
		modelMap.addAttribute("listSubject", listSubject);	//lấy ds môn học
		if (nguoiDung.getNguoidungRole().equals("student") == false) {
			modelMap.addAttribute("userName", nguoiDung.getNguoidungFullName());
			modelMap.addAttribute("userRole", nguoiDung.getNguoidungRole());
			Subject subject = subjectMapper.selectByPrimaryKey(subjectId);
			modelMap.addAttribute("subject", subject); //tìm tt môn học mà người dùng chọn
			modelAndView.setViewName("change-subject-information");
		}
		else {
			modelAndView.setViewName("redirect:/home");
		}
		return modelAndView;
	}
	
	@PostMapping("/change-subject-information")
	public ModelAndView changeSubjectInformation(@CookieValue(value="userId", defaultValue = "ngu") String userId 
			,@ModelAttribute("subjectId") Long subjectId
			,@ModelAttribute("subjectName") String subjectName
			,@ModelAttribute("subjectState") String subjectState
			, ModelMap modelMap) {
		ModelAndView modelAndView = new ModelAndView();
		Nguoidung nguoiDung = nguoidungMapper.selectByPrimaryKey(userId); //tìm người dùng theo mã
		SubjectExample subjectExample = new SubjectExample();
		List<Subject> listSubject = subjectMapper.selectByExample(subjectExample);
		modelMap.addAttribute("listSubject", listSubject);	//lấy ds môn học
		if (nguoiDung.getNguoidungRole().equals("student") == false) {
			modelMap.addAttribute("userName", nguoiDung.getNguoidungFullName());
			modelMap.addAttribute("userRole", nguoiDung.getNguoidungRole());
			Subject subject = subjectMapper.selectByPrimaryKey(subjectId);
			subject.setSubjectName(subjectName);
			if(subjectState.equals("true")) {
				subject.setSubjectState(true);
			}
			else if(subjectState.equals("false")) {
				subject.setSubjectState(false);
			}
			subjectMapper.updateByPrimaryKey(subject);
			modelAndView.setViewName("redirect:/subject-list");
		}
		else {
			modelAndView.setViewName("redirect:/home");
		}
		return modelAndView;
	}
}
