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

import com.cuduchoa.ThiTracNghiem.mapper.NguoidungMapper;
import com.cuduchoa.ThiTracNghiem.mapper.QuestionMapper;
import com.cuduchoa.ThiTracNghiem.mapper.SubjectMapper;
import com.cuduchoa.ThiTracNghiem.model.Class;
import com.cuduchoa.ThiTracNghiem.model.ClassExample;
import com.cuduchoa.ThiTracNghiem.model.Nguoidung;
import com.cuduchoa.ThiTracNghiem.model.Question;
import com.cuduchoa.ThiTracNghiem.model.QuestionExample;
import com.cuduchoa.ThiTracNghiem.model.Subject;
import com.cuduchoa.ThiTracNghiem.model.SubjectExample;

@Controller
public class QuestionManagementController {
	@Autowired
	NguoidungMapper nguoidungMapper;
	
	@Autowired
	SubjectMapper subjectMapper;
	
	@Autowired
	QuestionMapper questionMapper;
	
	@GetMapping("/question-list")
	public ModelAndView questionList(@RequestParam("subjectId") String subjectId,@CookieValue(value="userId", defaultValue = "ngu") String userId , ModelMap modelMap) {
		ModelAndView modelAndView = new ModelAndView();
		Nguoidung nguoiDung = nguoidungMapper.selectByPrimaryKey(userId); //tìm người dùng theo mã
		SubjectExample subjectExample = new SubjectExample();
		List<Subject> listSubject = subjectMapper.selectByExample(subjectExample);
		modelMap.addAttribute("listSubject", listSubject); //lấy ds môn học
		long id1 = Long.parseLong(subjectId);
		Subject subject = subjectMapper.selectByPrimaryKey(id1);
		modelMap.addAttribute("subjectName", subject.getSubjectName());
		if (nguoiDung.getNguoidungRole().equals("student") == false) {
			modelMap.addAttribute("userName", nguoiDung.getNguoidungFullName());
			QuestionExample questionExample = new QuestionExample();
			questionExample.createCriteria().andSubjectIdEqualTo(id1);
			List<Question> listQuestion = questionMapper.selectByExample(questionExample);
			modelMap.addAttribute("listQuestion", listQuestion);
			modelMap.addAttribute("Role", nguoiDung.getNguoidungRole());
			modelAndView.setViewName("question-list");
		}
		else {
			modelAndView.setViewName("redirect:/home");
		}
		return modelAndView;
	}
	
	@GetMapping("/create-question")
	public ModelAndView createQuestion(@CookieValue(value="userId", defaultValue = "ngu") String userId , ModelMap modelMap) {
		ModelAndView modelAndView = new ModelAndView();
		Nguoidung nguoiDung = nguoidungMapper.selectByPrimaryKey(userId);
		SubjectExample subjectExample = new SubjectExample();
		List<Subject> listSubject = subjectMapper.selectByExample(subjectExample);
		modelMap.addAttribute("listSubject", listSubject); //lấy ds môn học
		if (nguoiDung.getNguoidungRole().equals("student") == false) {
			modelMap.addAttribute("userName", nguoiDung.getNguoidungFullName());
			modelMap.addAttribute("userRole", nguoiDung.getNguoidungRole());
			modelAndView.setViewName("create-question");
		}
		else {
			modelAndView.setViewName("redirect:/home");
		}
		return modelAndView;
	}
	
	@PostMapping("/create-question")
	public ModelAndView createQuestion(
			@ModelAttribute("questionContent") String questionContent
			,@ModelAttribute("answerOne") String answerOne
			,@ModelAttribute("answerTwo") String answerTwo
			,@ModelAttribute("answerThree") String answerThree
			,@ModelAttribute("answerFour") String answerFour
			,@ModelAttribute("answerCorrect") String answerCorrect
			,@RequestParam("questionGrade") String questionGrade
			,@ModelAttribute("questionTerm") String questionTerm
			,@ModelAttribute("subjectId") Long subjectId
			,@CookieValue(value="userId", defaultValue = "ngu") String userId, ModelMap modelMap)
	{
        ModelAndView modelAndView = new ModelAndView();
        Question question = new Question();
        question.setQuestionContent(questionContent);
        question.setAnswerOne(answerOne);
        question.setAnswerTwo(answerTwo);
        if(answerThree.equals("") == false) {
        	question.setAnswerThree(answerThree);
        }
        if(answerFour.equals("") == false) {
        	question.setAnswerFour(answerFour);
        }
	    if (answerCorrect.equals("answerOne")) {
	    	question.setAnswerCorrect(answerOne);
	    }
	    else if (answerCorrect.equals("answerTwo")) {
	    	question.setAnswerCorrect(answerTwo);
	    }
	    else if (answerCorrect.equals("answerThree")) {
	    	question.setAnswerCorrect(answerThree);
	    }
	    else if (answerCorrect.equals("answerFour")) {
	    	question.setAnswerCorrect(answerFour);
	    }
	    question.setQuestionGrade(questionGrade);
	    question.setQuestionTerm(questionTerm);
	    question.setSubjectId(subjectId);
	    question.setCreateby(userId);
	    question.setQuestionState(false);
	    questionMapper.insert(question);
	    modelAndView.setViewName("redirect:/question-list?subjectId="+subjectId.toString());
		return modelAndView;
	}
	
	@GetMapping("/change-question-information")
	public ModelAndView changeQuestionInformation(@CookieValue(value="userId", defaultValue = "ngu") String userId,@RequestParam("questionId") String questionId, ModelMap modelMap) {
		ModelAndView modelAndView = new ModelAndView();
		Nguoidung nguoiDung = nguoidungMapper.selectByPrimaryKey(userId); //tìm người dùng theo mã
		SubjectExample subjectExample = new SubjectExample();
		List<Subject> listSubject = subjectMapper.selectByExample(subjectExample);
		modelMap.addAttribute("listSubject", listSubject); //lấy ds môn học
		if (nguoiDung.getNguoidungRole().equals("student") == false) {
			modelMap.addAttribute("userName", nguoiDung.getNguoidungFullName());
			modelMap.addAttribute("userRole", nguoiDung.getNguoidungRole());
			long id1 = Long.parseLong(questionId);
			Question question = questionMapper.selectByPrimaryKey(id1);
			modelMap.addAttribute("questionId", question.getQuestionId());
			modelMap.addAttribute("questionContent", question.getQuestionContent());
			modelMap.addAttribute("answerOne", question.getAnswerOne());
			modelMap.addAttribute("answerTwo", question.getAnswerTwo());
			modelMap.addAttribute("answerThree", question.getAnswerThree());
			modelMap.addAttribute("answerFour", question.getAnswerFour());
			if(question.getAnswerCorrect().equals(question.getAnswerOne())) {
				modelMap.addAttribute("answerCorrect", "answerOne");
			}
			else if(question.getAnswerCorrect().equals(question.getAnswerTwo())) {
				modelMap.addAttribute("answerCorrect", "answerTwo");
			}
			else if(question.getAnswerCorrect().equals(question.getAnswerThree())) {
				modelMap.addAttribute("answerCorrect", "answerThree");
			}
			else if(question.getAnswerCorrect().equals(question.getAnswerFour())) {
				modelMap.addAttribute("answerCorrect", "answerFour");
			}
			modelMap.addAttribute("questionGrade", question.getQuestionGrade());
			modelMap.addAttribute("questionState", question.getQuestionState());
			modelMap.addAttribute("questionTerm", question.getQuestionTerm());
			modelMap.addAttribute("subjectId", question.getSubjectId());
			Subject subject = subjectMapper.selectByPrimaryKey(question.getSubjectId());
			modelMap.addAttribute("subjectName", subject.getSubjectName());
			modelMap.addAttribute("createby", question.getCreateby());
			if(userId.equals(question.getCreateby()) == true) {
				modelMap.addAttribute("isMe",true);
			}
			else {
				modelMap.addAttribute("isMe",false);
			}
			modelAndView.setViewName("change-question-information");
		}
		else {
			modelAndView.setViewName("redirect:/home");
		}
		return modelAndView;
	}
	
	@PostMapping("/change-question-information")
	public ModelAndView changeQuestionInformation(
			@ModelAttribute("questionContent") String questionContent
			,@ModelAttribute("answerOne") String answerOne
			,@ModelAttribute("answerTwo") String answerTwo
			,@ModelAttribute("answerThree") String answerThree
			,@ModelAttribute("answerFour") String answerFour
			,@ModelAttribute("answerCorrect") String answerCorrect
			,@RequestParam("questionGrade") String questionGrade
			,@ModelAttribute("questionTerm") String questionTerm
			,@ModelAttribute("subjectId") Long subjectId
			,@ModelAttribute("questionState") String questionState
			,@ModelAttribute("questionId") String questionId
			,@CookieValue(value="userId", defaultValue = "ngu") String userId, ModelMap modelMap)
	{
        ModelAndView modelAndView = new ModelAndView();
        long id1 = Long.parseLong(questionId);
        Question question = questionMapper.selectByPrimaryKey(id1); //tìm câu hỏi theo id
        question.setQuestionContent(questionContent);
        question.setAnswerOne(answerOne);
        question.setAnswerTwo(answerTwo);
        if(answerThree.equals("") == false) {
        	question.setAnswerThree(answerThree);
        }
        if(answerFour.equals("") == false) {
        	question.setAnswerFour(answerFour);
        }
	    if (answerCorrect.equals("answerOne")) {
	    	question.setAnswerCorrect(answerOne);
	    }
	    else if (answerCorrect.equals("answerTwo")) {
	    	question.setAnswerCorrect(answerTwo);
	    }
	    else if (answerCorrect.equals("answerThree")) {
	    	question.setAnswerCorrect(answerThree);
	    }
	    else if (answerCorrect.equals("answerFour")) {
	    	question.setAnswerCorrect(answerFour);
	    }
	    question.setQuestionGrade(questionGrade);
	    question.setQuestionTerm(questionTerm);
	    question.setSubjectId(subjectId);
	    Nguoidung nguoiDung = nguoidungMapper.selectByPrimaryKey(userId);
	    if(questionState.equals("true") && nguoiDung.getNguoidungRole().equals("admin") == true) {
	    	question.setQuestionState(true);
	    }
	    if(questionState.equals("false") && nguoiDung.getNguoidungRole().equals("admin") == true) {
	    	question.setQuestionState(false);
	    }
	    if(nguoiDung.getNguoidungRole().equals("teacher")) {
	    	question.setQuestionState(false);
	    }
	    questionMapper.updateByPrimaryKey(question);
	    modelAndView.setViewName("redirect:/question-list?subjectId="+subjectId.toString());
		return modelAndView;
	}
}
