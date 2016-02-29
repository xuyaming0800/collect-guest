package com.dataup.ccc.web.base.security.controllerr;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import com.alibaba.fastjson.JSON;
import com.dataup.ccc.api.entity.ResultEntity;
import com.dataup.ccc.api.exception.BusinessRunException;
import com.dataup.ccc.api.exception.ExceptionCode;
import com.dataup.ccc.api.service.ApiToolsService;
import com.dataup.ccc.web.base.security.entity.AjaxEntity;
import com.dataup.ccc.web.base.security.entity.LoginDTO;
import com.dataup.ccc.web.base.security.entity.MapabcBossUser;
import com.dataup.ccc.web.base.security.service.SecurityService;
import com.dataup.ccc.web.base.security.service.UsersService;
import com.dataup.ccc.web.constant.CustomConstant;
import com.dataup.ccc.web.util.Md5Utils;

/**
 * 
 * @ClassName: SecurityController
 * @Description: 用户登录、退出、校验用户是否注册
 * @author zhanqiao.huang
 * @date 2015年9月10日 下午5:11:50
 */
@Controller
@RequestMapping("/security")
public class SecurityController {
	private Logger logger = LogManager.getLogger(getClass());
	private long expires = 24 * 60 * 60; // 1天
	@Autowired
	private SecurityService securityService = null;
	@Autowired
	private ApiToolsService apiToolsService = null;
	@Autowired
	private UsersService usersService = null;

	/**
	 * 登录
	 * 
	 * @param loginName
	 * @param password
	 * @param strRememberMe
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping("/doLogin")
	public @ResponseBody AjaxEntity doLogin(@RequestBody LoginDTO loginDTO,
			HttpServletResponse response, HttpServletRequest request)
			throws Exception {
		logger.info("进入doLogin方法,登录start");
		logger.entry("doLogin入参：" + loginDTO);
		AjaxEntity ajaxEntity = new AjaxEntity();
		String check = checkNull(new String[] { loginDTO.getUsername(),
				loginDTO.getPassword() },
				new String[] { "username", "password" });
		try {
			if (!check.equals("")) {
				logger.error("校验失败：" + check);
				ajaxEntity.setCode(ExceptionCode.MISS_REQUEST_PARAMS + "");
				ajaxEntity.setDesc(apiToolsService
						.getExceptionMessage(ajaxEntity.getCode()));
				return ajaxEntity;
			}
			MapabcBossUser user = securityService.login(loginDTO);
			if (user != null) {
				logger.info("判断邮箱是否激活。");
				if (!CustomConstant.USER_EMAIL_ACTIVE_STATUS_OK.equals(user.getEmailsverify())) {
					logger.info("您账号还没有激活邮箱。");
					ajaxEntity.setSuccess(false);
					ajaxEntity.setErrors("此账号邮箱未激活。");
					ajaxEntity.setCode(CustomConstant.USER_EMAIL_ACTIVE_STATUS_FALL_CODE);
				}else{
					if (loginDTO.getIsRemember()) {
						String name = user.getName();
						String pass = user.getPwd();
						// 声明cookie
						Cookie autoCookie = null;
						// 获取所有cookie
						Cookie cookies[] = request.getCookies();
						// 遍历cookie
						for (Cookie cookie : cookies) {
							// 判断是否存在自动登陆记录
							if ("autologin".equals(cookie.getName())) {
								autoCookie = cookie; // 赋值
								// 当cookie存在的时候,我需要重新设置值
								long time = (System.currentTimeMillis() + expires * 1000);
								// cookie拼接的value值,(可以根据自己的想法设计)
								String newValue = name + ":" + time + ":"
										+ Md5Utils.md5Value(name + ":" + pass + ":" + time);
								// 设置值
								autoCookie.setValue(newValue);
							} else {
								// 不存在创建
								// name+":"+time+":"+md5(name:pass:time)
								long time = System.currentTimeMillis() + expires
										* 1000;
								// cookie拼接的value值,(可以根据自己的想法设计)
								String cookieValue = name + ":" + time + ":"
										+ Md5Utils.md5Value(name + ":" + pass + ":" + time);
								// 创建cookie
								autoCookie = new Cookie("autologin", cookieValue);
							}
						}
						autoCookie.setMaxAge((int) expires);
						autoCookie.setPath("/ccc-web");
						// 添加cookie
						response.addCookie(autoCookie);
					}
					//ccc-super-admin
					if("ccc-super-admin".equals(user.getName())){
						List list =usersService.queryCustomsByType("3");
						List<MapabcBossUser> customList=new ArrayList<MapabcBossUser>();
						for (int i = 0; i < list.size(); i++) {
							MapabcBossUser custom = JSON.parseObject(JSON.toJSONString(list.get(i)),MapabcBossUser.class);
							if("ccc-super-admin".equals(custom.getName()))
								continue;
							customList.add(custom);
						}
						request.getSession().setAttribute("customList", customList);// 客户信息
					} 
					ajaxEntity.setSuccess(true);
				}
			}
			logger.info("进入doLogin方法,登录end");
		} catch (UnknownAccountException e) {// 用户名没有找到。
			ajaxEntity.setErrors(e.getMessage());
			logger.error("进入doLogin方法,用户名没有找到", e);
		} catch (IncorrectCredentialsException e) {// 用户名密码不匹配。
			ajaxEntity.setErrors(e.getMessage());
			logger.error("进入doLogin方法,用户名密码不匹配", e);
		} catch (AuthenticationException e) {// 其他的登录错误
			ajaxEntity.setErrors(e.getMessage());
			logger.error("进入doLogin方法,其他的登录错误", e);
		}
		return ajaxEntity;
	}

	@SuppressWarnings("rawtypes")
	@RequestMapping("/doAccountIsExists")
	public @ResponseBody AjaxEntity doAccountIsExists(
			@RequestParam("account") String account) throws Exception {
		AjaxEntity<Object[]> ajaxEntity = new AjaxEntity<Object[]>();
		String check = checkNull(new String[] { account },
				new String[] { "username" });
		try {
			if (!check.equals("")) {
				ajaxEntity.setCode(ExceptionCode.MISS_REQUEST_PARAMS + "");
				ajaxEntity.setDesc(apiToolsService
						.getExceptionMessage(ajaxEntity.getCode()));
				return ajaxEntity;
			}
			Map<String, Object> m = usersService.queryInfoByUserName(account);
			String str = m.toString().trim();
			if ("{}".equals(str)) {
				ajaxEntity.setSuccess(false);
				ajaxEntity.setErrors("此账号不存在！");
			} else {
				String email = (String) m.get("mail");
				if (StringUtils.isNotBlank(email)) {
					Object[] info = new Object[] { m };
					ajaxEntity.setInfo(info);
					ajaxEntity.setSuccess(true);
				} else {
					ajaxEntity.setSuccess(false);
					ajaxEntity.setErrors("此账号没有绑定邮箱！");
				}
			}
		} catch (BusinessRunException e) {//
			ajaxEntity.setErrors(e.getMessage());
		}
		return ajaxEntity;
	}

	@SuppressWarnings("rawtypes")
	@RequestMapping("/doResetPass")
	public @ResponseBody AjaxEntity doResetPass(@RequestBody LoginDTO loginDTO)
			throws Exception {
		AjaxEntity ajaxEntity = new AjaxEntity();
		String username = loginDTO.getUsername();
		String password = loginDTO.getPassword();
		String check = checkNull(new String[] { username, password },
				new String[] { "username", "password" });
		if (!check.equals("")) {
			ajaxEntity.setCode(ExceptionCode.MISS_REQUEST_PARAMS + "");
			ajaxEntity.setDesc(apiToolsService.getExceptionMessage(ajaxEntity
					.getCode()));
			return ajaxEntity;
		}
		ResultEntity entity = usersService.resetPassword(username, password);
		if ("E0".equals(entity.getCode())) {
			ajaxEntity.setSuccess(true);
		} else {
			ajaxEntity.setSuccess(false);
		}
		ajaxEntity.setCode(entity.getCode());
		ajaxEntity.setDesc(entity.getDesc());
		return ajaxEntity;
	}

	@RequestMapping("/login")
	public String login() throws Exception {
		if (SecurityUtils.getSubject().isAuthenticated()) {
			return "index/main";
		}
		return "login";
	}

	@RequestMapping("/logout")
	public String logout(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		 securityService.logout();
		 return "login";
	}

	@RequestMapping("/main")
	public String main() throws Exception {
		return "index/main";
	}

	@RequestMapping("/unauthorized")
	public String unauthorized() throws Exception {
		return "security/unauthorized";
	}

	@RequestMapping("/unauthenticated")
	public String unauthenticated() throws Exception {
		return "security/unauthenticated";
	}

	private String checkNull(String[] values, String[] desc) {
		if (values == null || desc == null) {
			return "校验的值和要校验的数据不符";
		} else if (values.length != desc.length) {
			return "校验的值和要校验的数据不符";
		} else {
			for (int i = 0; i < values.length; i++) {
				if (values[i] == null || values[i].equals("")) {
					return desc[i] + "不能为空";
				}
			}
		}
		return "";
	}

}
