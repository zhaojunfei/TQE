package com.tqe.controller;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tqe.po.Course;

@Controller()
@RequestMapping("/admin")
public class TeacherController extends BaseController{

	
	@RequestMapping("/tea")
	public String tea(Model model){
		System.out.println("tea");
		return "teacher/teacher";
	}
	
	/**
	 * 显示教师列表主页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/teacher",method={RequestMethod.GET})
	public String teacher(Model model){
		System.out.println("hello");
		addSercherResource(model);
		//model.addAttribute("teacherList", teacherService.findAll());
		return "teacher/teacher";
	}
	
	/**
	 * 关键字 查询教师列表
	 * @param model
	 * @param did
	 * @param tname
	 * @param tid
	 * @return
	 */
	@RequestMapping(value="/teacher",method={RequestMethod.POST})
	public String teacher1(Model model,String did,String tname,String tid){
		addSercherResource(model);
		HashMap<String,String> condition = new HashMap<String,String>();
		condition.put("did", did);
		condition.put("tname", tname);
		condition.put("tid", tid);
		model.addAttribute("condition", condition);
		model.addAttribute("teacherList", teacherService.findByCondition(condition));
		return "teacher/teacher";
	}
	
	/**
	 * 显示教师详情
	 * @param model
	 * @param tid
	 * @return
	 */
	@RequestMapping("/teacher/show/{tid}")
	public String show(Model model,@PathVariable String tid){
		//List<Course> courseList = courseService.findAllByTId(tid);
		return "teacher/showTeacher";
	}
	
	
	
}
