package com.cuduchoa.ThiTracNghiem.controller;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

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

import com.cuduchoa.ThiTracNghiem.mapper.NguoidungMapper;
import com.cuduchoa.ThiTracNghiem.mapper.QuestionMapper;
import com.cuduchoa.ThiTracNghiem.mapper.SubjectMapper;
import com.cuduchoa.ThiTracNghiem.mapper.TestMapper;
import com.cuduchoa.ThiTracNghiem.mapper.Test_detailMapper;
import com.cuduchoa.ThiTracNghiem.model.Class;
import com.cuduchoa.ThiTracNghiem.model.ClassExample;
import com.cuduchoa.ThiTracNghiem.model.Nguoidung;
import com.cuduchoa.ThiTracNghiem.model.Question;
import com.cuduchoa.ThiTracNghiem.model.QuestionExample;
import com.cuduchoa.ThiTracNghiem.model.Subject;
import com.cuduchoa.ThiTracNghiem.model.SubjectExample;
import com.cuduchoa.ThiTracNghiem.model.Test;
import com.cuduchoa.ThiTracNghiem.model.TestExample;
import com.cuduchoa.ThiTracNghiem.model.Test_detailKey;
import com.cuduchoa.ThiTracNghiem.model.Test_detailExample;

@Controller
public class TestManagementController {
	@Autowired
	NguoidungMapper nguoidungMapper;
	
	@Autowired
	SubjectMapper subjectMapper;
	
	@Autowired
	TestMapper testMapper;
	
	@Autowired
	QuestionMapper questionMapper;
	
	@Autowired
	Test_detailMapper testDetailMapper;
	
	@GetMapping("/test-list")
	public ModelAndView testList(@CookieValue(value="userId", defaultValue = "ngu") String userId,@RequestParam("order") String order , ModelMap modelMap) {
		ModelAndView modelAndView = new ModelAndView();
		Nguoidung nguoiDung = nguoidungMapper.selectByPrimaryKey(userId); //tìm người dùng theo mã
		SubjectExample subjectExample = new SubjectExample();
		List<Subject> listSubject = subjectMapper.selectByExample(subjectExample);
		modelMap.addAttribute("listSubject", listSubject); //lấy ds môn học
		if (nguoiDung.getNguoidungRole().equals("student") == false) {
			modelMap.addAttribute("userName", nguoiDung.getNguoidungFullName());
			List<Map<String,Object>> listTest = null; 
			if(order.equals("null") == true) {
				listTest = testMapper.getAllTest();
			}
			else if(order.equals("subjectName") == true) {
				listTest = testMapper.getAllTestOrderBySubjectName();
			}
			else if(order.equals("testTerm") == true) {
				listTest = testMapper.getAllTestOrderByTestTerm();
			}
			else if(order.equals("testGrade") == true) {
				listTest = testMapper.getAllTestOrderByTestGrade();
			}
			else if(order.equals("testDate") == true) {
				listTest = testMapper.getAllTestOrderByTestDate();
			}
			else if(order.equals("testState") == true) {
				listTest = testMapper.getAllTestOrderByTestState();
			}
			modelMap.addAttribute("Role", nguoiDung.getNguoidungRole());
			modelMap.addAttribute("listTest", listTest);
			modelAndView.setViewName("test-list");
		}
		else {
			modelAndView.setViewName("redirect:/home");
		}
		return modelAndView;
	}
	
	@GetMapping("/create-test")
	public ModelAndView createTest(@CookieValue(value="userId", defaultValue = "ngu") String userId , ModelMap modelMap) {
		ModelAndView modelAndView = new ModelAndView();
		Nguoidung nguoiDung = nguoidungMapper.selectByPrimaryKey(userId); //tìm người dùng theo id
		if (nguoiDung.getNguoidungRole().equals("student") == false) {
			modelMap.addAttribute("userName", nguoiDung.getNguoidungFullName());
			modelMap.addAttribute("userRole", nguoiDung.getNguoidungRole());
			SubjectExample subjectExample = new SubjectExample();
			List<Subject> listSubject = subjectMapper.selectByExample(subjectExample);
			modelMap.addAttribute("listSubject", listSubject); //lấy ds môn học
			modelAndView.setViewName("create-test");
		}
		else {
			modelAndView.setViewName("redirect:/home");
		}
		return modelAndView;
	}
	
	@PostMapping("/create-test")
	public ModelAndView createTest(
			@ModelAttribute("subjectId") Long subjectId
			,@ModelAttribute("testGrade") String testGrade
			,@ModelAttribute("testTerm") String testTerm
			,@ModelAttribute("testDate") String testDate
			,@ModelAttribute("testTimeStart") String testTimeStart
			,@ModelAttribute("testTimeLimit") String testTimeLimit
			,@ModelAttribute("testQuantity") String testQuantity
			,@CookieValue(value="userId", defaultValue = "ngu") String userId, ModelMap modelMap) throws ParseException
	{
        ModelAndView modelAndView = new ModelAndView();
			SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");
			Date date1 = formatter1.parse(testDate); //chuyển kiểu String sang date
			Date lt = formatter1.parse(LocalDate.now().toString()); //lấy ngày hiện tại
			if( date1.after(lt) == true) { //so sánh
				Test test = new Test();
				test.setSubjectId(subjectId);
				test.setTestGrade(testGrade);
				test.setTestTerm(testTerm);
				test.setTestDate(date1);
				SimpleDateFormat formatter = new SimpleDateFormat("hh:mm");
				Date date = formatter.parse(testTimeStart); //chuyển kiểu String sang time
				test.setTestTimeStart(date);
				int timeLimit = Integer.parseInt(testTimeLimit);
				test.setTestTimeLimit(timeLimit);
				int quantity = Integer.parseInt(testQuantity);
				test.setTestQuantity(quantity);
				test.setTestState(false); //khi tạo bkt mới thì trạng thái luôn là false
				QuestionExample questionExample = new QuestionExample();
				questionExample.createCriteria().andQuestionGradeEqualTo(testGrade).andQuestionTermEqualTo(testTerm).andSubjectIdEqualTo(subjectId).andQuestionStateEqualTo(true);
				List<Question> listQuestion = questionMapper.selectByExample(questionExample); //lấy tất cả câu hỏi thoả đk
				if(listQuestion.size() >= quantity) { //so sánh 
					testMapper.insert(test);
					TestExample testExample = new TestExample();
					List<Test> listTest = testMapper.selectByExample(testExample);
					int a = listTest.size() - 1; //lấy vị trí bkt vừa tạo
					Random random = new Random();
					int x;
					for (int i = 0; i < quantity; i ++) {
						x = random.nextInt(0, listQuestion.size()); //random câu hỏi
						Test_detailExample testDetailExample = new Test_detailExample();
						testDetailExample.createCriteria().andQuestionIdEqualTo(listQuestion.get(x).getQuestionId()).andTestIdEqualTo(listTest.get(a).getTestId());
						List<Test_detailKey> listTestDetail = testDetailMapper.selectByExample(testDetailExample); //tìm xem đã có chưa
						if (listTestDetail.size() <= 0) {
							Test_detailKey testDetail = new Test_detailKey();
							testDetail.setQuestionId(listQuestion.get(x).getQuestionId());
							testDetail.setTestId(listTest.get(a).getTestId());
							testDetailMapper.insert(testDetail); //chưa có thì thêm
						}
						else {
							i --; 
						}
					}
					modelAndView.setViewName("redirect:/test-list?order=null");
				}
				else {
					modelAndView.setViewName("redirect:/create-test?notEnough");
				}
				
			}
			else {
				modelAndView.setViewName("redirect:/create-test?fail");
			}
			
		return modelAndView;
	}
	
	@GetMapping("/test-detail")
	public ModelAndView changeTestInformation(@CookieValue(value="userId", defaultValue = "null") String userId,@RequestParam("testId") Long testId,ModelMap modelMap)
	{
		ModelAndView modelAndView = new ModelAndView();
		Nguoidung nguoiDung = nguoidungMapper.selectByPrimaryKey(userId); //tìm người dùng theo mã
		if(nguoiDung.getNguoidungRole().equals("student") == false) 
		{
			Test test = testMapper.selectByPrimaryKey(testId); //tìm bkt mà ngươi dùng
			modelMap.addAttribute("testId", test.getTestId());
			modelMap.addAttribute("testGrade",test.getTestGrade());
			String pattern = "yyyy-MM-dd";
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
			String date = simpleDateFormat.format(test.getTestDate());
			modelMap.addAttribute("testDate", date);
			String pattern1 = "HH:mm";
			SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat(pattern1);
			String date1 = simpleDateFormat1.format(test.getTestTimeStart());
			modelMap.addAttribute("testTimeStart", date1);
			modelMap.addAttribute("testTerm", test.getTestTerm());
			modelMap.addAttribute("testTimeLimit", test.getTestTimeLimit());
			modelMap.addAttribute("testState", test.getTestState());
			modelMap.addAttribute("testQuantity", test.getTestQuantity());
			modelMap.addAttribute("subjectId", test.getSubjectId());
			Subject subject= subjectMapper.selectByPrimaryKey(test.getSubjectId()); 
			modelMap.addAttribute("subjectName", subject.getSubjectName());
			modelMap.addAttribute("userName", nguoiDung.getNguoidungFullName());
			modelMap.addAttribute("userId", nguoiDung.getNguoidungId());
			modelMap.addAttribute("userRole", nguoiDung.getNguoidungRole());
			modelAndView.setViewName("test-detail");
			SubjectExample subjectExample = new SubjectExample();
			List<Subject> listSubject = subjectMapper.selectByExample(subjectExample);
			modelMap.addAttribute("listSubject", listSubject); //lấy ds môn học
			List<Question> listQuestion = new ArrayList<Question>();
			Test_detailExample testDetailExample = new Test_detailExample();
			testDetailExample.createCriteria().andTestIdEqualTo(testId);
			List<Test_detailKey> listTestDetail = testDetailMapper.selectByExample(testDetailExample);
			if (listTestDetail.size() > 0) {
				for (int i = 0; i < listTestDetail.size(); i ++) {
					Question question = questionMapper.selectByPrimaryKey(listTestDetail.get(i).getQuestionId());
					if(question.equals(null) == false) {
						listQuestion.add(question);  //lấy ds câu hỏi
					}
				}
			}
			if (listQuestion.size() > 0) {
				modelMap.addAttribute("listQuestion", listQuestion);
			}
		}
		else {
			modelAndView.setViewName("home");
		}
		return modelAndView;
	}
	
	@PostMapping("/test-detail")
	public ModelAndView changeTestInformation(
			@ModelAttribute("testId") String testId
			,@ModelAttribute("testState") String testState
			,@CookieValue(value="userId", defaultValue = "ngu") String userId, ModelMap modelMap) throws IOException, ParseException
	{
			ModelAndView modelAndView = new ModelAndView();
			long id1 = Long.parseLong(testId);
			Test test = testMapper.selectByPrimaryKey(id1);
			if (testState.equals("true")) {
				test.setTestState(true);
			}
			else if (testState.equals("false")) {
				test.setTestState(false);
			}
			testMapper.updateByPrimaryKey(test);
			modelAndView.setViewName("redirect:/test-list?order=null");
			return modelAndView;
	}
}
