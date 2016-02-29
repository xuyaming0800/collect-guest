package com.dataup.ccc.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dataup.ccc.api.entity.ResultEntity;
import com.dataup.ccc.api.service.EnterpriseService;
import com.dataup.ccc.api.service.MailService;
import com.dataup.ccc.api.util.OpenApiConstant;
import com.dataup.ccc.web.base.security.service.UsersService;
import com.dataup.ccc.web.constant.Config;
import com.dataup.ccc.web.util.MyCenterUtils;

@Controller
@RequestMapping("/user")
public class UserController {

	private final Logger logger = LogManager.getLogger(this.getClass());

	@Autowired
	MailService mailService;
	@Autowired
	UsersService usersService;
	@Autowired
	EnterpriseService enterpriseService;

	@RequestMapping("/register")
	public String indexPage() {
		return "regist/register";
	}

	@RequestMapping("/forgetpwd")
	public String forgetMe() {
		return "forgetpwd/confirm";
	}

	@RequestMapping("/checkEmail")
	public @ResponseBody boolean checkEmail(@RequestParam("email") String email) {
		logger.info("验证邮箱地址：" + email);
		if (email != null && !"".equals(email)) {
			try {
				return usersService.emailExist(email);
			} catch (Exception e) {
				logger.error(e);
				e.printStackTrace();
				return false;
			}
		}
		return false;
	}

	@RequestMapping("/checkUserName")
	public @ResponseBody boolean checkUserName(
			@RequestParam("username") String username) {
		logger.info("验证用户名地址：" + username);
		if (username != null && !"".equals(username)) {
			try {
				return usersService.userNameExist(username);
			} catch (Exception e) {
				logger.error(e);
				e.printStackTrace();
				return false;
			}
		}
		return false;
	}

	/**
	 * @Description: 用户激活邮箱功能
	 * @author xudsheng.liu
	 * @date 2015年9月11日 下午4:51:24
	 * @version V1.0
	 * @param request
	 * @param email
	 * @param username
	 * @return
	 */
	@RequestMapping("/registSuccess")
	public String registSuccess(HttpServletRequest request,
			@RequestParam("email") String email,
			@RequestParam("username") String username) {
		// 发送邮件
		String basePath = MyCenterUtils.getContext(request);
		mailService.sendMailActivateCode(username, basePath
				+ Config.ccc_in_email_url_activate_email, email,
				OpenApiConstant.MAIL_ACTIVATECODE);
		return "regist/registSuccess";
	}

	/**
	 * @Description: 重新发送激活邮件
	 * @author xudsheng.liu
	 * @date 2015年9月11日 下午4:51:08
	 * @version V1.0
	 * @param email
	 * @param username
	 * @return
	 */
	@RequestMapping("/sendEmailAgain")
	public @ResponseBody ResultEntity sendEmailAgain(
			HttpServletRequest request, @RequestParam("email") String email,
			@RequestParam("username") String username) {
		logger.info("-->进入方法sendEmailAgain");
		logger.info("---->入参:email="+email+"; username="+username);
		ResultEntity result = new ResultEntity();
		if (email == null || "".equals(email) || username == null
				|| "".equals(username)) {
			result.setSuccess(false);
			result.setDesc("必须参数为空");
			logger.info("-->必须参数为空,激活失败");
			return result;
		}
		try {
			String basePath = MyCenterUtils.getContext(request);
			if (usersService.emailExist(email)) {// 已经激活
				result.setSuccess(false);
				result.setDesc("已经激活");
				logger.info("-->激活失败,已经激活");
				return result;
			}
			mailService.sendMailActivateCode(username, basePath
					+ Config.ccc_in_email_url_activate_email, email,
					OpenApiConstant.MAIL_ACTIVATECODE);
			result.setSuccess(true);
			result.setDesc("激活成功");
			logger.info("-->激活成功");
			return result;
		} catch (Exception e) {
			logger.error(e);
			result.setSuccess(false);
			result.setDesc("激活失败");
			logger.error("进入sendEmailAgain方法，系统内容异常", e);
			return result;
		}
	}

	/**
	 * @Description: 激活邮箱 true 激活成功,false激活失败
	 * @author xudsheng.liu
	 * @date 2015年9月14日 上午11:01:44
	 * @version V1.0
	 * @param email
	 * @param username
	 * @return
	 */
	@RequestMapping("/toActivateEmail")
	public String toActivateEmail(HttpServletRequest request,
			@RequestParam("username") String username,
			@RequestParam("email") String email,
			@RequestParam("activateCode") String activateCode) {
		logger.info("-->进入方法toActivateEmail");
		logger.info("---->用户名：" + username);
		logger.info("---->邮箱：" + email);
		logger.info("---->激活码：" + activateCode);
		if (username != null && !"".equals(username)) {
			try {
				//如果激活成功 - 这里会导致跳转到失败页面，缓存数据和数据库数据没有及时同步，这里先注释掉
				/*Map<String, Object> map = usersService.queryInfoByUserName(username);
				if(map!=null){
					MapabcBossUser user = JSON.parseObject(JSON.toJSONString(map),MapabcBossUser.class);
					if(CustomConstant.USER_EMAIL_ACTIVE_STATUS_OK.equals(user.getEmailsverify())){
						logger.info(username+"已经激活，重复激活跳转失败页面");
						return "regist/activateFall";
					}
				}*/
				mailService.activateEmail(username, activateCode);// 验证,清楚缓存
				usersService.activateEmail(username);// 修改数据
				logger.info("-->激活成功");
				return "regist/activateSuccess";
			} catch (Exception e) {
				logger.error(e);
				e.printStackTrace();
			}
		}
		logger.info("-->激活失败");
		return "regist/activateFall";
	}
}
