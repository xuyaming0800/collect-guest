package com.dataup.ccc.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.dataup.ccc.api.entity.Enterprise;
import com.dataup.ccc.api.entity.ResultEntity;
import com.dataup.ccc.api.exception.BusinessRunException;
import com.dataup.ccc.api.exception.ExceptionCode;
import com.dataup.ccc.api.service.ApiToolsService;
import com.dataup.ccc.api.service.EnterpriseService;
import com.dataup.ccc.api.service.MailService;
import com.dataup.ccc.api.util.OpenApiConstant;
import com.dataup.ccc.web.base.security.entity.AjaxEntity;
import com.dataup.ccc.web.base.security.entity.MapabcBossUser;
import com.dataup.ccc.web.base.security.service.UsersService;
import com.dataup.ccc.web.constant.Config;
import com.dataup.ccc.web.entity.mycenter.CustomInfoEntity;
import com.dataup.ccc.web.entity.mycenter.FileInfo;
import com.dataup.ccc.web.util.MyCenterUtils;

/**
 * 
 * @ClassName: MyCenterController
 * @Description: 个人中心
 * @author zhanqiao.huang
 * @date 2015年9月18日 上午9:32:16
 */
@Controller
@RequestMapping("/mycenter")
public class MyCenterController {

	private Logger logger = LogManager.getLogger(this.getClass());
	@Autowired
	private ApiToolsService cCApiToolsService;
	@Autowired
	private UsersService usersService;
	@Autowired
	MailService mailService;
	@Autowired
	EnterpriseService enterpriseService;

	/**
	 * 业务系统管理主页
	 * 
	 * @return
	 */
	@RequestMapping("/main")
	public String indexPage() {
		return "downcenter";
	}

	/**
	 * 
	 * @Title: modifyPwd
	 * @Description: 修改密码
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	@RequestMapping("goModifyPass")
	public String goModifyPass() {
		return "mycenter/modifypwd";
	}

	/**
	 * 
	 * @Title: verifyOldMail
	 * @Description: 验证原有邮箱
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	@RequestMapping("verifyOldMail")
	public String verifyOldMail() {
		return "mycenter/verifyOldMail";
	}

	@RequestMapping("/doModifyPass")
	public @ResponseBody ResultEntity doModifyPass(
			@RequestBody CustomInfoEntity customInfoEntity) {
		logger.info("进入doModifyPass方法,保存start");
		ResultEntity result = new ResultEntity();
		try {
			String check = MyCenterUtils.checkNull(
					new String[] { customInfoEntity.getUserName(),
							customInfoEntity.getOldPass(),
							customInfoEntity.getNewPass() }, new String[] {
							"userName", "oldPass", "newPass" });
			if (!check.equals("")) {
				logger.error("校验失败：" + check);
				result.setSuccess(false);
				result.setCode(ExceptionCode.MISS_REQUEST_PARAMS + "");
				result.setDesc(cCApiToolsService.getExceptionMessage(result
						.getCode()));
				return result;
			}
			ResultEntity r = usersService.modifyPassword(
					customInfoEntity.getUserName(),
					customInfoEntity.getOldPass(),
					customInfoEntity.getNewPass());
			if (r.isSuccess()) {
				result.setSuccess(true);
				result.setCode(r.getCode() + "");
				result.setDesc(r.getDesc());
			} else {
				result.setSuccess(false);
				result.setCode(r.getCode() + "");
				result.setDesc(r.getDesc());
			}
			logger.info("进入doModifyPass方法,修改end");
		} catch (BusinessRunException e) {
			result.setSuccess(false);
			result.setCode(ExceptionCode.SYSTEM_ERROR + "");
			result.setDesc(cCApiToolsService.getExceptionMessage(result
					.getCode()));
			logger.error("进入doModifyPass方法,系统内容异常", e);
			return result;
		} catch (Exception e) {
			result.setSuccess(false);
			result.setCode(ExceptionCode.SYSTEM_ERROR + "");
			result.setDesc(cCApiToolsService.getExceptionMessage(result
					.getCode()));
			logger.error("进入doModifyPass方法,修改异常", e);
		}
		logger.info("完成doModifyPass方法操作");
		return result;
	}

	/**
	 * @Description: 异步发送邮件
	 * @author xusheng.liu
	 * @date 2015年9月18日 下午11:45:08
	 * @version V1.0
	 * @param request
	 * @param email
	 * @param username
	 * @return
	 */
	@RequestMapping("/sendEmailByAjax")
	public @ResponseBody ResultEntity sendEmailByAjax(
			HttpServletRequest request, @RequestParam("email") String email,
			@RequestParam("username") String username) {
		logger.info("进入发送邮件方法sendEmailByAjax");
		logger.info("入参email:" + email + ";username:" + username);
		ResultEntity result = new ResultEntity();
		try {
			if (MyCenterUtils.checkEmailByRegex(email)
					|| StringUtils.isNotBlank(email)
					|| StringUtils.isNotBlank(username)) {
				mailService.sendMailCode(email, OpenApiConstant.MAIL_VALIDCODE);
				result.setSuccess(true);
				result.setDesc("发送邮件成功");
				logger.info("发送邮件成功");
			} else {
				logger.info("入参错误");
				result.setSuccess(false);
				result.setDesc("参数错误");
			}
			return result;
		} catch (Exception e) {
			result.setSuccess(false);
			result.setDesc("发送邮件失败,系统错误");
			logger.error("邮件发送失败", e);
			return result;
		}
	}

	/**
	 * @Description: 在修改邮箱阶段，验证验证码
	 * @author xusheng.liu
	 * @date 2015年9月20日 下午8:59:43
	 * @version V1.0
	 * @param email
	 * @param username
	 * @return
	 */
	@RequestMapping("/checkCodeUpdateEmail")
	public @ResponseBody ResultEntity checkCodeUpdateEmail(
			@RequestParam("email") String email,
			@RequestParam("code") String code) {
		logger.info("进入验证验证码方法checkCodeUpdateEmail");
		logger.info("入参email:" + email + ";code:" + code);
		ResultEntity result = new ResultEntity();
		try {
			if (MyCenterUtils.checkEmailByRegex(email)
					|| StringUtils.isNotBlank(email)
					|| StringUtils.isNotBlank(code)) {
				mailService.validateMailCode(email, code,
						OpenApiConstant.MAIL_VALIDCODE);
				result.setSuccess(true);
				result.setDesc("验证通过");
				logger.info("验证通过");
			} else {
				logger.info("入参错误");
				result.setSuccess(false);
				result.setDesc("参数错误");
			}
			return result;
		} catch (Exception e) {
			result.setSuccess(false);
			result.setDesc(e.getMessage());
			logger.error("验证失败", e);
			return result;
		}
	}

	/**
	 * @Description: 修改邮箱
	 * @author xusheng.liu
	 * @date 2015年9月21日 上午11:34:08
	 * @version V1.0
	 * @param email
	 * @param username
	 * @return
	 */
	@RequestMapping("/doModifyEmail")
	public @ResponseBody ResultEntity doModifyEmail(HttpServletRequest request,
			@RequestParam("email") String email,
			@RequestParam("username") String username) {
		logger.info("进入修改邮箱方法doModifyEmail");
		logger.info("入参email:" + email + ";username:" + username);
		ResultEntity result = new ResultEntity();
		try {
			if (StringUtils.isNotBlank(email)
					|| StringUtils.isNotBlank(username)) {// 参数不为空
				if (MyCenterUtils.checkEmailByRegex(email)) {// 邮箱格式正确
					/**
					 * 调用boss修改邮箱接口，校验邮箱是否重复/格式，成功则修改用户激活状态，邮箱地址；发送激活邮件来激活邮箱。
					 */
					result = this.usersService.modifyEmail(email, username);
					logger.info("修改邮箱返回信息：" + result);
					if (result.isSuccess()) {// 修改成功，发送激活码邮件
						String basePath = MyCenterUtils.getContext(request);
						mailService.sendMailActivateCode(username, basePath
								+ Config.ccc_in_email_url_activate_email,
								email, OpenApiConstant.MAIL_ACTIVATECODE);
						logger.info("激活码信息发送成功");
					}
					return result;
				} else {
					logger.info("邮箱格式错误");
					result.setSuccess(false);
					result.setDesc("邮箱格式错误");
				}
			} else {
				logger.info("入参为空");
				result.setSuccess(false);
				result.setDesc("参数为空");
			}
			return result;
		} catch (Exception e) {
			result.setSuccess(false);
			result.setDesc(e.getMessage());
			logger.error("验证失败", e);
			return result;
		}
	}

	@RequestMapping("userInfo")
	public String userInfo() {
		return "mycenter/user_info";
	}

	/**
	 * @Description: 查询企业信息
	 * @author xusheng.liu
	 * @date 2015年9月18日 下午4:43:18
	 * @version V1.0
	 * @param request
	 * @return
	 */
	@SuppressWarnings("unused")
	@RequestMapping("queryEnterprise")
	public @ResponseBody AjaxEntity<Enterprise> queryEnterprise(
			HttpServletRequest request) {
		try {
			logger.info("进入查询企业信息方法queryEnterprise");
			Session session = SecurityUtils.getSubject().getSession();// 获取session
			MapabcBossUser user = (MapabcBossUser) session
					.getAttribute("customInfo");// 用户
			logger.info("当前用户：" + user.getName());
			AjaxEntity<Enterprise> AjaxEntity = new AjaxEntity<Enterprise>();
			Enterprise enterprise = new Enterprise();
			if (user != null) {
				if("ccc-super-admin".equals(user.getName())){
					MapabcBossUser changeUser= (MapabcBossUser) request.getSession().getAttribute(
							"changeCustomInfo");
					enterprise.setUserId(changeUser.getId());
				} else {
					enterprise.setUserId(user.getId());
				}
			}
			Enterprise ent = enterpriseService.queryEnterpriseByCondition(1,
					enterprise);
			AjaxEntity.setInfo(ent);
			AjaxEntity.setSuccess(true);
			AjaxEntity.setDesc("查询成功");
			logger.exit(AjaxEntity);
			return AjaxEntity;
		} catch (Exception e) {
			AjaxEntity<Enterprise> AjaxEntity = new AjaxEntity<Enterprise>();
			AjaxEntity.setSuccess(false);
			AjaxEntity.setDesc("查询企业信息失败");
			logger.error("查询企业信息失败", e);
			return AjaxEntity;
		}
	}

	/**
	 * @Description: 查询企业信息
	 * @author xusheng.liu
	 * @date 2015年9月18日 下午4:43:18
	 * @version V1.0
	 * @param request
	 * @return
	 */
	@RequestMapping("saveEnterprise")
	public @ResponseBody AjaxEntity<Enterprise> saveEnterprise(
			@RequestParam(value = "imgFile", required = false) MultipartFile imgFile,
			Enterprise enterprise) {
		try {
			logger.info("进入保存企业信息方法saveEnterprise");
			logger.entry(enterprise);
			MyCenterUtils mcu = new MyCenterUtils();
			String[] extAllowed = { "png", "jpg", "jpeg" };
			FileInfo info = mcu.uploadFile(imgFile, "logo", extAllowed);
			if (info != null)
				enterprise.setUrl(info.getPath());
			Session session = SecurityUtils.getSubject().getSession();// 获取session
			MapabcBossUser user = (MapabcBossUser) session
					.getAttribute("customInfo");// 用户
			logger.info("当前用户：" + user.getName());
			AjaxEntity<Enterprise> AjaxEntity = new AjaxEntity<Enterprise>();
			enterprise.setUserId(user.getId());
			boolean result = enterpriseService.saveEnterpriseByCondition(
					user.getName(), enterprise);
			if (result) {
				AjaxEntity.setSuccess(true);
				AjaxEntity.setDesc("保存成功");
				logger.info("保存企业信息成功");
			} else {
				AjaxEntity.setSuccess(false);
				AjaxEntity.setDesc("保存失败");
				logger.info("保存企业信息失败");
			}
			logger.exit(AjaxEntity);
			return AjaxEntity;
		} catch (Exception e) {
			AjaxEntity<Enterprise> AjaxEntity = new AjaxEntity<Enterprise>();
			AjaxEntity.setSuccess(false);
			AjaxEntity.setDesc("保存企业信息失败");
			logger.error("保存企业信息失败", e);
			return AjaxEntity;
		}
	}
}
