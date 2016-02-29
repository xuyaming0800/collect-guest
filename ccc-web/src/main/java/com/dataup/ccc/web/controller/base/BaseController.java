package com.dataup.ccc.web.controller.base;


import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.dataup.ccc.api.entity.ProjectInfoEntity;
import com.dataup.ccc.api.entity.ResultEntity;
//import com.dataup.ccc.api.service.MailService;
import com.dataup.ccc.api.service.base.BaseService;
import com.dataup.ccc.api.util.SysConstant;
import com.dataup.ccc.web.base.security.entity.MapabcBossUser;
import com.dataup.ccc.web.base.security.service.UsersService;
import com.dataup.ccc.web.constant.Config;

/**
 * @Title: BaseController.java
 * @Package com.dataup.ccc.web.controller.base
 * @Description: 客户中心-主数据来源
 * @author xusheng.liu
 * @date 2015年9月21日 下午9:24:18
 * @version V1.0
 */
@Controller
@RequestMapping("/base")
public class BaseController {

	private final Logger logger = LogManager.getLogger(this.getClass());

	@Autowired
	UsersService usersService;
	@Autowired
	BaseService baseService;

	/**
	 * @Description: 查询当前客户的项目
	 * @author xusheng.liu
	 * @date 2015年9月21日 下午9:26:08
	 * @version V1.0
	 * @param enterprise
	 * @return
	 */
	@RequestMapping("queryProject")
	public @ResponseBody ResultEntity queryProject(@RequestParam("customId") String customId,@RequestParam("customName") String customName, HttpServletRequest request) {
		try {
			logger.info("进入查询用户项目信息方法queryProject");
			ResultEntity result = new ResultEntity();
			if (StringUtils.isNotEmpty(customId)) {
				ProjectInfoEntity entity = new ProjectInfoEntity();
				entity.setCustomId(customId);
				ResultEntity resultEntity = baseService.queryProject(Config.cc_myitems_iteminfo_show_url,customId);
				if(resultEntity.isSuccess()){
					resultEntity.setCode(SysConstant.SUCCESS_CODE);
					resultEntity.setDesc("成功");
				}
				Map<String, Object> map = usersService
						.queryInfoByUserName(customName);
				MapabcBossUser user = JSON.parseObject(JSON.toJSONString(map),
						MapabcBossUser.class);
				request.getSession().setAttribute("changeCustomInfo", user);
				return resultEntity;
			} 
			return logger.exit(result);
		} catch (Exception e) {
			ResultEntity result = new ResultEntity();
			result.setDesc("查询项目信息失败");
			logger.error("查询项目信息失败", e);
			return result;
		}
	}

}
