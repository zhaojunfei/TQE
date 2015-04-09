package com.tqe.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tqe.po.Batches;
import com.tqe.service.BatchesServiceImpl;


@Controller
@RequestMapping("/admin")
public class BatchesController extends BaseController {
	
	@Autowired
	private BatchesServiceImpl batchesService;
	
	@RequestMapping("/batches")
	public String batches(Model model){
		List<Batches> list = batchesService.findAll();
		model.addAttribute("batchesList",list);
		return "batches/batches";
	}
	
	@RequestMapping("/batches/add")
	public String addbatches(){
		return "batches/addBatches";
	}
	
	
	
	@RequestMapping("/batches/show/{id}")
	public String addbatches(@PathVariable int id,Model model){
		model.addAttribute("batches",batchesService.getById(id));
		return "batches/showBatches";
	}
	
	@RequestMapping("/batches/save")
	public String savebatches(@ModelAttribute()Batches batches){
		batchesService.save(batches);
		
		return "redirect:/admin/batches";
	}
	
	@RequestMapping("/batches/update")
	public void updatebatches(@ModelAttribute()Batches batches,Model model,HttpServletResponse response) throws IOException{
		response.setCharacterEncoding("utf-8");
		if(batches.getBeginDate()==null || batches.getEndDate()==null || batches.getBeginDate().getTime()>=batches.getEndDate().getTime()){
			return;
		}
		Date latest = batchesService.getLatestDate(batches.getId());
		if(batches.getBeginDate().getTime() <= latest.getTime()){
			response.getWriter().println("设定日期失败! 您设置的评教批次的起始日期必须大于最新批次的截止日期:"+new SimpleDateFormat("yyyy-MM-dd").format(latest)+" 请重新设置开始日期或者截止日期");
			return;
		}
		Batches b = batchesService.getById(batches.getId());
		if(b!=null){
			b.setBeginDate(batches.getBeginDate());
			b.setEndDate(batches.getEndDate());
		}
		response.getWriter().println("修改日期成功！");
		batchesService.update(b);
	}
	
	
	/**
	 * 
	 * 设置评教批次的默认评价表
	 * @param bid 评教批次Id
	 * @param eid 评教表Id
	 * @param type student:学生 teacher:教师 lead:领导
	 * @return
	 */
	@RequestMapping("/batches/setEval/{type}/{bid}/{eid}")
	public String defaultEval(@PathVariable Integer bid,@PathVariable Integer eid,@PathVariable String type,Model model){
		
		if(bid==null || eid==null){
			return "redirect:/admin/batches";
		}
		Batches b = batchesService.getById(bid);
			
		if(StringUtils.hasText(type) && b!=null){		//如果有内容
			if(type.equals("student")){
				
				b.setStuEvalId(eid);
				batchesService.update(b);
				return "redirect:/admin/batches/show/"+bid;
			}
			if(type.equals("teacher")){
				b.setTeaEvalId(eid);
				batchesService.update(b);
				
				return "redirect:/admin/batches/show/"+bid;
			}
			if(type.equals("lead")){
				b.setLeadEvalId(eid);
				batchesService.update(b);
				return "redirect:/admin/batches/show/"+bid;
			}
			
		}
		model.addAttribute("msg", "路径参数不正确:/batches/setEval/{type}/{bid}/{eid}\n");
		return "error";
		
			
	}

}
