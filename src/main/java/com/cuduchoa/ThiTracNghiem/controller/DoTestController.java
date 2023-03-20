package com.cuduchoa.ThiTracNghiem.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.cuduchoa.ThiTracNghiem.mapper.ClassMapper;
import com.cuduchoa.ThiTracNghiem.mapper.Class_detailMapper;
import com.cuduchoa.ThiTracNghiem.mapper.NguoidungMapper;
import com.cuduchoa.ThiTracNghiem.mapper.QuestionMapper;
import com.cuduchoa.ThiTracNghiem.mapper.ResultMapper;
import com.cuduchoa.ThiTracNghiem.mapper.TestMapper;
import com.cuduchoa.ThiTracNghiem.mapper.Test_detailMapper;
import com.cuduchoa.ThiTracNghiem.model.Class;
import com.cuduchoa.ThiTracNghiem.model.Class_detailKey;
import com.cuduchoa.ThiTracNghiem.model.Class_detailExample;
import com.cuduchoa.ThiTracNghiem.model.Nguoidung;
import com.cuduchoa.ThiTracNghiem.model.Question;
import com.cuduchoa.ThiTracNghiem.model.Result;
import com.cuduchoa.ThiTracNghiem.model.ResultExample;
import com.cuduchoa.ThiTracNghiem.model.Subject;
import com.cuduchoa.ThiTracNghiem.model.SubjectExample;
import com.cuduchoa.ThiTracNghiem.model.Test;
import com.cuduchoa.ThiTracNghiem.model.Test_detailKey;
import com.cuduchoa.ThiTracNghiem.model.Test_detailExample;

@Controller
public class DoTestController {
	
	@Autowired
	NguoidungMapper nguoiDungMapper;
	
	@Autowired
	TestMapper testMapper;
	
	@Autowired
	QuestionMapper questionMapper;
	
	@Autowired
	Test_detailMapper testDetailMapper;
	
	@Autowired
	ClassMapper classMapper;
	
	@Autowired
	Class_detailMapper classDetailMapper; 
	
	@Autowired
	ResultMapper resultlMapper; 
	
	static List<Question> listQuestion1 = new ArrayList<Question>();
	static Long thisTest;
	
	@GetMapping("/do-test")
	public ModelAndView doTest(@CookieValue(value="userId", defaultValue = "ngu") String userId,@RequestParam("testId") Long testId, ModelMap modelMap) throws ParseException
	{
		ModelAndView modelAndView = new ModelAndView("do-test");
		Nguoidung nguoiDung = nguoiDungMapper.selectByPrimaryKey(userId); //tìm người dùng theo mã
		modelMap.addAttribute("userId", nguoiDung.getNguoidungId());
		modelMap.addAttribute("userName", nguoiDung.getNguoidungFullName());
		Class_detailExample classDetailExample = new Class_detailExample();
		classDetailExample.createCriteria().andNguoidungIdEqualTo(userId);
		List<Class_detailKey> listClassDetail = classDetailMapper.selectByExample(classDetailExample); //tìm lớp mà người này đã và đang học
		if (listClassDetail.size() > 0) {
			for (int i = 0; i < listClassDetail.size(); i ++) {
				Class classz = classMapper.selectByPrimaryKey(listClassDetail.get(i).getClassId());
				if(classz.getClassState().equals(true)) {
					modelMap.addAttribute("className", classz.getClassName()); 
					break;
				}
			}
		}
		thisTest = testId;
		Test test = testMapper.selectByPrimaryKey(testId); //tìm bkt theo mã
		modelMap.addAttribute("testTimeLimit", test.getTestTimeLimit()); //gán thời gian làm bài vào biến
		SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
		/*Date time1 = formatter.parse("07:30");
		Date time2 = formatter.parse("09:00");
		Date time3 = formatter.parse("13:30");
		Date time4 = formatter.parse("15:00");
		Date time5 = null;
		if (test.getTestTimeStart().equals(time1)) {
			time5 = formatter.parse("07:45");
		}
		else if (test.getTestTimeStart().equals(time2)) {
			time5 = formatter.parse("09:15");
		}
		else if (test.getTestTimeStart().equals(time3)) {
			time5 = formatter.parse("13:45");
		}
		else if (test.getTestTimeStart().equals(time4)) {
			time5 = formatter.parse("15:15");
		}*/
		Date time6 = formatter.parse("24:00");
		Date lt = formatter.parse(java.time.LocalTime.now().toString());
		//if (lt.after(test.getTestTimeStart()) && lt.before(time6)) {
			List<Question> listQuestion = new ArrayList<Question>(); //tạo ds câu hỏi
			Test_detailExample testDetailExample = new Test_detailExample(); 
			testDetailExample.createCriteria().andTestIdEqualTo(testId);
			List<Test_detailKey> listTestDetail = testDetailMapper.selectByExample(testDetailExample); //tìm các câu hỏi của bkt này
			if (listTestDetail.size() > 0) {
				for (int i = 0; i < listTestDetail.size(); i ++) {
					Question question = questionMapper.selectByPrimaryKey(listTestDetail.get(i).getQuestionId());
					if(question.equals(null) == false) {
						listQuestion.add(question); //thêm câu hỏi vào ds
					}
				}
			}
			if (listQuestion.size() > 0 && test.getTestQuantity().equals(listQuestion.size())) {
				Random random = new Random();
				int x;
				for (int i = 0; i < listQuestion.size(); i ++) {
					x = random.nextInt(0, listQuestion.size()); //random câu hỏi
					if(listQuestion1.contains(listQuestion.get(x)) == false) {
						listQuestion1.add(listQuestion.get(x)); //thêm câu hỏi vào ds
					}
					else {
						i --;
					}
				}
				if(test.getTestQuantity().equals(listQuestion1.size())) {
					modelMap.addAttribute("listQuestion", listQuestion1);
				}
			}
			modelAndView.setViewName("do-test");
		//}
		//else {
		//	modelAndView.setViewName("redirect:/home?fail");
		//}
		return modelAndView;
	}
	
	static List<Boolean> listBoolean = new ArrayList<Boolean>();
	@PostMapping("/do-test")
	public ModelAndView doTest(@CookieValue(value="userId", defaultValue = "ngu") String userId,@RequestParam Map<String, Object> params, ModelMap modelMap) throws ParseException
	{
		ModelAndView modelAndView = new ModelAndView();
		listBoolean.clear();
		Nguoidung nguoiDung = nguoiDungMapper.selectByPrimaryKey(userId); //tìm người dùng theo mã
		modelMap.addAttribute("userId", nguoiDung.getNguoidungId());
		int rightAnswer = 0;
		for (int i = 0; i < listQuestion1.size(); i ++) {
			String s = String.valueOf(listQuestion1.get(i).getQuestionId());
			String a = (String) params.get(s);
			if (listQuestion1.get(i).getAnswerCorrect().equals(a) == true) {
				rightAnswer = rightAnswer + 1;
				listBoolean.add(true);
			}
			else {
				listBoolean.add(false);
			}
		}
		float score =(float) (rightAnswer * 10 / listQuestion1.size());
		SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
		Date lt = formatter.parse(java.time.LocalTime.now().toString());
		Result result = new Result();
		result.setTestId(thisTest);
		result.setNguoidungId(userId);
		result.setTestFinishTime(lt);
		result.setTestState(true);
		result.setUsersScore(score);
		resultlMapper.insert(result);
		modelAndView.setViewName("redirect:/result");
		return modelAndView;
	}
	
	@GetMapping("/result")
	public ModelAndView result(@CookieValue(value="userId", defaultValue = "ngu") String userId, ModelMap modelMap) throws ParseException
	{
		ModelAndView modelAndView = new ModelAndView();
		Nguoidung nguoiDung = nguoiDungMapper.selectByPrimaryKey(userId);
		modelMap.addAttribute("userId", nguoiDung.getNguoidungId()); //tìm người dùng theo mã
		modelMap.addAttribute("userName", nguoiDung.getNguoidungFullName());
		modelMap.addAttribute("listBoolean", listBoolean); //lấy ds đúng sai
		Class_detailExample classDetailExample = new Class_detailExample();
		classDetailExample.createCriteria().andNguoidungIdEqualTo(userId);
		List<Class_detailKey> listClassDetail = classDetailMapper.selectByExample(classDetailExample); //lấy tất cả lớp học mà người này tham gia
		if (listClassDetail.size() > 0) {
			for (int i = 0; i < listClassDetail.size(); i ++) {
				Class classz = classMapper.selectByPrimaryKey(listClassDetail.get(i).getClassId());
				if(classz.getClassState().equals(true)) {
					modelMap.addAttribute("className", classz.getClassName());
					break;
				}
			}
		}
		Test test = testMapper.selectByPrimaryKey(thisTest);
		modelMap.addAttribute("test", test);
		ResultExample resultExample = new ResultExample();
		resultExample.createCriteria().andNguoidungIdEqualTo(userId).andTestIdEqualTo(thisTest);
		List<Result> listResult = resultlMapper.selectByExample(resultExample);
		if (listResult.size() > 0) {
			modelMap.addAttribute("result", listResult.get(0));
		}
		modelAndView.setViewName("result");
		return modelAndView;
	}
	
	@GetMapping("/result-list")
	public ModelAndView resultList(@CookieValue(value="userId", defaultValue = "ngu") String userId, ModelMap modelMap) throws ParseException
	{
		ModelAndView modelAndView = new ModelAndView();
		Nguoidung nguoiDung = nguoiDungMapper.selectByPrimaryKey(userId); //tìm người dùng theo mã
		modelMap.addAttribute("userId", nguoiDung.getNguoidungId());
		modelMap.addAttribute("userName", nguoiDung.getNguoidungFullName());
		Class_detailExample classDetailExample = new Class_detailExample();
		classDetailExample.createCriteria().andNguoidungIdEqualTo(userId);
		List<Class_detailKey> listClassDetail = classDetailMapper.selectByExample(classDetailExample);
		if (listClassDetail.size() > 0) {
			for (int i = 0; i < listClassDetail.size(); i ++) {
				Class classz = classMapper.selectByPrimaryKey(listClassDetail.get(i).getClassId());
				if(classz.getClassState().equals(true)) {
					modelMap.addAttribute("className", classz.getClassName());
					break;
				}
			}
		}
		List<Map<String, Object>> listResult = resultlMapper.getUserResult(userId);
		modelMap.addAttribute("listResult", listResult);
		modelAndView.setViewName("result-list");
		return modelAndView;
	}
}
