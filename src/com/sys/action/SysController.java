package com.sys.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.sys.service.SysService;

@Controller
public class SysController {
	
	@Autowired
	private SysService sysService;
	
	@RequestMapping(value="/test")
	public String test(){
		String url = sysService.test();
		return url;
	}
	
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public String login(){
		return "login";
	}
	
	@RequestMapping(value="/loginsuccess",method=RequestMethod.GET)
	public ModelAndView loginsuccess(){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("jsp/sys/main/main");
		return mv;
	}
	
	@RequestMapping(value="/toWidgets",method=RequestMethod.GET)
	public String toWidgets(){
		return "jsp/sys/main/widgets";
	}
	
	@RequestMapping(value="/error/denied")
	public String denied(){
		return "jsp/sys/error/403";
	}
	
}
