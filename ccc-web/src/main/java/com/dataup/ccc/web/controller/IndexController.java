package com.dataup.ccc.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/index")
public class IndexController {

	
	
	
	/**
	 * 登录页
	 * 
	 * @return
	 */
	@RequestMapping("/login")
	public String loginPage() {
		return "login";
	}
	/**
	 * 主页
	 * 
	 * @return
	 */
	@RequestMapping("/main")
	public String indexPage() {
		return "index/main";
	}
	/**
	 * 首页
	 * 
	 * @return
	 */
	@RequestMapping("/firstPage")
	public String firstPage() {
		return "index/index";
	}
	/**
	 * 验证失败页面
	 * 
	 * @return
	 */
	@RequestMapping("/caserror")
	public String casErrorPage() {
		return "index/caserror";
	}
}
