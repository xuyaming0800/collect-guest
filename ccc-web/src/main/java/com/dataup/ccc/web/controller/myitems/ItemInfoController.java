package com.dataup.ccc.web.controller.myitems;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.dataup.ccc.api.entity.ResultEntity;
import com.dataup.ccc.api.service.ApiToolsService;
import com.dataup.ccc.api.service.ItemInfoService;
import com.dataup.ccc.api.service.MailService;
import com.dataup.ccc.web.base.security.entity.MapabcBossUser;
import com.dataup.ccc.web.base.security.service.UsersService;
import com.dataup.ccc.web.constant.Config;

/**
 * 
 * @ClassName: ItemInfoController
 * @Description: 项目基础信息
 * @author zhanqiao.huang
 * @date 2015年9月28日 上午10:18:38
 */
@Controller
@RequestMapping("/myitems/baseinfo")
public class ItemInfoController {

	private Logger logger = LogManager.getLogger(this.getClass());

	@Autowired
	private ApiToolsService cCApiToolsService;
	@Autowired
	private ItemInfoService itemInfoService;
	@Autowired
	private MailService mailService;
	@Autowired
	private UsersService usersService;

	@RequestMapping("itemInfo")
	public String goToItemInfo() {
		return "myitems/item_info";
	}

	@RequestMapping("queryItemInfo")
	public @ResponseBody ResultEntity queryItemInfo(
			@RequestParam("ownerId") String ownerId) {
		logger.info("传入参数：" + ownerId);
		logger.info("执行queryItemInfo方法-start");
		ResultEntity itemInfo = new ResultEntity();
		ResultEntity itemFee = new ResultEntity();
		ResultEntity resultEntity = new ResultEntity();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			itemInfo = itemInfoService.queryItemInfo(
					Config.cc_myitems_iteminfo_show_url, ownerId);
			itemFee = itemInfoService.queryItemFeeInfo(
					Config.cc_finance_myitems_feeinfo_show_url, ownerId);
			map.put("itemInfo", itemInfo);
			map.put("itemFee", itemFee);
			resultEntity.setInfo(map);
			resultEntity.setSuccess(true);
			return resultEntity;
		} catch (Exception e) {
			resultEntity.setSuccess(false);
			resultEntity.setDesc("系统异常");
			logger.info("queryItemInfo方法执行失败");
			e.printStackTrace();
		}
		logger.info("执行queryItemInfo方法-end");
		return logger.exit(resultEntity);
	}

	@RequestMapping("sendMail")
	public @ResponseBody ResultEntity sendMail(
			@RequestParam("userName") String userName,
			@RequestParam("hidCxt") String hidCxt,
			@RequestParam("hidProject") String hidProject,
			@RequestParam("button") String button) {
		logger.info("传入参数：" + userName);
		logger.info("传入参数:项目名称==="+hidProject);
		logger.info("执行sendMail方法-start");
		ResultEntity resultEntity = new ResultEntity();
		try {
			Map<String, Object> map = usersService
					.queryInfoByUserName(userName);
			MapabcBossUser user = JSON.parseObject(JSON.toJSONString(map),
					MapabcBossUser.class);
			if ("btnStart".equals(button))
				mailService.sendEmail(user.getMail(), hidProject+"项目申请启动邮件", hidCxt, 1);
			if ("btnStop".equals(button))
				mailService.sendEmail(user.getMail(), hidProject+"项目申请暂停邮件", hidCxt, 1);
			resultEntity.setSuccess(true);
			return resultEntity;
		} catch (Exception e) {
			resultEntity.setSuccess(false);
			resultEntity.setDesc("系统异常");
			logger.info("sendMail方法执行失败");
			e.printStackTrace();
		}
		logger.info("执行sendMail方法-end");
		return logger.exit(resultEntity);
	}

}