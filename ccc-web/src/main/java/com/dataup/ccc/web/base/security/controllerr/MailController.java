package com.dataup.ccc.web.base.security.controllerr;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dataup.ccc.api.exception.ExceptionCode;
import com.dataup.ccc.api.exception.support.BaseSupportException;
import com.dataup.ccc.api.service.ApiToolsService;
import com.dataup.ccc.api.service.MailService;
import com.dataup.ccc.api.util.SysConstant;
import com.dataup.ccc.web.base.security.entity.AjaxEntity;
import com.dataup.ccc.web.base.security.entity.MailEntity;


@Controller
@RequestMapping("/mail")
public class MailController {
	private  final Logger logger = LogManager.getLogger(this.getClass());
	
	@Autowired
	private MailService mailService;
	
	@Autowired
	private ApiToolsService openApiToolsService;
	
	
	/**
	 * 发送邮件验证码
	 * @param request
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping("/sendMailCode")
	public @ResponseBody AjaxEntity sendMailCode(@RequestBody MailEntity mail) {
		AjaxEntity<Object []> ajaxEntity = new AjaxEntity<Object []>();
		try {
			logger.entry(mail);
			String mailAddress=mail.getUsername();
			String validtype=mail.getValidtype();
			String check = checkNull(new Object[]{mailAddress,validtype}, 
					new String[]{"username", "validtype"});
			//验证参数是否为空
			if (!check.equals("")) {
				ajaxEntity.setCode(ExceptionCode.MISS_REQUEST_PARAMS + "");
				ajaxEntity.setDesc(openApiToolsService.getExceptionMessage(ajaxEntity.getCode()));
				return ajaxEntity;
			}
			Map<String, String> resultMap = mailService.sendMailCode(mailAddress, validtype);
			Object [] info = new Object[]{resultMap};
			ajaxEntity.setSuccess(true);
			ajaxEntity.setCode(SysConstant.SUCCESS_CODE);
			ajaxEntity.setDesc("成功");
			ajaxEntity.setInfo(info);
			return ajaxEntity;
			
		}catch(BaseSupportException b) {
			ajaxEntity.setSuccess(false);
			ajaxEntity.setCode(b.getErrorCode());
			ajaxEntity.setDesc(b.getMessage());
			logger.error("发送邮件验证码失败", b);
			return ajaxEntity;
		}catch (Exception e) {
			ajaxEntity.setSuccess(false);
			ajaxEntity.setCode(ExceptionCode.SYSTEM_INNER_ERROR + "");
			String desc = openApiToolsService.getExceptionMessage(ExceptionCode.SYSTEM_INNER_ERROR + "");
			ajaxEntity.setDesc(desc);
			logger.error("发送邮件验证码失败", e);
			return ajaxEntity;
		}
	}
	
	
	/**
	 * 验证邮件验证码
	 * @param request
	 * @return
	 */
	@RequestMapping("/validateMailCode")
	public @ResponseBody AjaxEntity<Object []> validateMailCode(@RequestBody MailEntity mailDTO) {
		AjaxEntity<Object []> ajaxEntity = new AjaxEntity<Object []>();
		try {
			logger.entry(mailDTO);
			
			String mail = mailDTO.getMail();
			String validcode = mailDTO.getValidcode();
			String validtype = mailDTO.getValidtype();
			
			String check = checkNull(new Object[]{mail, validcode, validtype}, 
					new String[]{"mail", "validcode", "validtype"});
			//验证参数是否为空
			if(!check.equals("")){
				ajaxEntity.setCode(ExceptionCode.MISS_REQUIRED_PARAMS+"");
				ajaxEntity.setDesc(openApiToolsService.getExceptionMessage(ajaxEntity.getCode()));
				return ajaxEntity;
			}
			
			mailService.validateMailCode(mail, validcode, validtype);
			
			ajaxEntity.setSuccess(true);
			ajaxEntity.setCode(SysConstant.SUCCESS_CODE);
			ajaxEntity.setDesc("成功");
			return ajaxEntity;
		}catch(BaseSupportException b) {
			ajaxEntity.setSuccess(false);
			ajaxEntity.setCode(b.getErrorCode());
			ajaxEntity.setDesc(b.getMessage());
			logger.error("验证邮件验证码失败", b);
			return ajaxEntity;
		}catch (Exception e) {
			ajaxEntity.setSuccess(false);
			ajaxEntity.setCode(ExceptionCode.SYSTEM_INNER_ERROR + "");
			String desc = openApiToolsService.getExceptionMessage(ExceptionCode.SYSTEM_INNER_ERROR + "");
			ajaxEntity.setDesc(desc);
			logger.error("验证邮件验证码失败", e);
			return ajaxEntity;
		}
		
	}
	
	/**
	 *  通过邮箱对用户密码进行重置，
	 * @param request
	 * @return
	 */
	@RequestMapping("/sendModifyPwdMail")
	public @ResponseBody AjaxEntity<Object []>  sendModifyPwdMail(@RequestBody MailEntity mailDTO) {
		AjaxEntity<Object []> ajaxEntity = new AjaxEntity<Object []>();
		try {
			logger.entry(mailDTO);
			String username =mailDTO.getUsername();
			String url = mailDTO.getUrl();
			String mail = mailDTO.getMail();
			String check = checkNull(new Object[]{username, url, mail}, new String[]{"username", "url", "mail"});
			//验证参数是否为空
			if(!check.equals("")){
				ajaxEntity.setCode(ExceptionCode.MISS_REQUIRED_PARAMS+"");
				ajaxEntity.setDesc(openApiToolsService.getExceptionMessage(ajaxEntity.getCode()));
				return ajaxEntity;
			}
			mailService.sendModifyPwdMail(username, url, mail);
			ajaxEntity.setSuccess(true);
			ajaxEntity.setCode(SysConstant.SUCCESS_CODE);
			ajaxEntity.setDesc("成功");
			return ajaxEntity;
		}catch(BaseSupportException b) {
			ajaxEntity.setSuccess(false);
			ajaxEntity.setCode(b.getErrorCode());
			ajaxEntity.setDesc(b.getMessage());
			logger.error("通过邮箱对用户密码进行重置，失败", b);
			return ajaxEntity;
		}catch (Exception e) {
			ajaxEntity.setSuccess(false);
			ajaxEntity.setCode(ExceptionCode.SYSTEM_INNER_ERROR + "");
			String desc = openApiToolsService.getExceptionMessage(ExceptionCode.SYSTEM_INNER_ERROR + "");
			ajaxEntity.setDesc(desc);
			logger.error("通过邮箱对用户密码进行重置，失败", e);
			return ajaxEntity;
		}
	}
	
	@RequestMapping("/reset")
	public String goResetPass(HttpServletRequest request) {
		String username=request.getParameter("username");
		String validcode=request.getParameter("validcode");
		try {
			mailService.activateEmail(username, validcode);
		} catch (Exception e) {
			if(e instanceof BaseSupportException){
				BaseSupportException base=(BaseSupportException)e;
				request.setAttribute("error", base.getMessage());
			}else{
				request.setAttribute("error", "系统异常");
			}
			return "error";
		}
		return "forgetpwd/reset";
	}
	
	private String checkNull(Object[] values,String[] desc){
		if(values==null||desc==null){
			return "校验的值和要校验的数据不符";
		}else if(values.length!=desc.length){
			return "校验的值和要校验的数据不符";
		}else{
			for(int i=0;i<values.length;i++){
				if(values[i]==null||values[i].equals("")){
					return desc[i]+"不能为空";
				}
			}
		}
		return "";
	}
}
