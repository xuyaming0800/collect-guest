package com.dataup.ccc.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * @ClassName: DownCenterController
 * @Description: 下载中心
 * @author zhanqiao.huang
 * @date 2015年9月17日 下午2:56:42
 */
@Controller
@RequestMapping("/downcenter")
public class DownCenterController {

	/**
	 * 业务系统管理主页
	 * 
	 * @return
	 */
	@RequestMapping("/main")
	public String indexPage() {
		return "downcenter";
	}

}
