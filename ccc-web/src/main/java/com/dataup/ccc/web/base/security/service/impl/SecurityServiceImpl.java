package com.dataup.ccc.web.base.security.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.dataup.ccc.api.service.DwDataService;
import com.dataup.ccc.web.base.security.entity.LoginDTO;
import com.dataup.ccc.web.base.security.entity.MapabcBossUser;
import com.dataup.ccc.web.base.security.service.SecurityService;
import com.dataup.ccc.web.base.security.service.UsersService;
import com.dataup.ccc.web.constant.CustomConstant;

/**
 * 
 * @ClassName: SecurityServiceImpl
 * @Description: 用户登录
 * @author zhanqiao.huang
 * @date 2015年9月18日 下午4:09:19
 */
@Service
public class SecurityServiceImpl implements SecurityService {
	private Logger log = LogManager.getLogger(getClass());

	@Autowired
	private UsersService usersService = null;
	@Autowired
	private DwDataService dwDataService = null;

	private Subject getSubject() {
		return SecurityUtils.getSubject();
	}

	/**
	 * 登录
	 */
	public MapabcBossUser login(LoginDTO loginDTO) throws Exception {
		// 创建用户名和密码的令牌
		UsernamePasswordToken token = new UsernamePasswordToken(
				loginDTO.getUsername(), loginDTO.getPassword());
		// subject理解成权限对象。类似user
		Subject subject = this.getSubject();
		MapabcBossUser user = null;
		if (!subject.isAuthenticated()) {// 如果没有登录
			try {
				subject.login(token);// 进行登录操作
			} catch (UnknownAccountException e) {// 用户名没有找到。
				log.error("用户名没有找到", e);
				throw new UnknownAccountException("用户名没有找到");
			} catch (IncorrectCredentialsException e) {// 用户名密码不匹配。
				log.error("用户名密码不匹配", e);
				throw new IncorrectCredentialsException("用户名密码不匹配");
			} catch (AuthenticationException e) {// 其他的登录错误
				log.error("登录失败", e);
				throw new AuthenticationException("登录失败");
			}
			log.info("验证是否成功登录的方法。");
			if (subject.isAuthenticated()) {
				log.info("成功登录。");
				Session session = subject.getSession();// 获取session
				log.info("准备sesion 客户信息。");
				String custom = loginDTO.getUsername();
				Map<String, Object> map = usersService
						.queryInfoByUserName(custom);
				user = JSON.parseObject(JSON.toJSONString(map),
						MapabcBossUser.class);
				if (CustomConstant.USER_EMAIL_ACTIVE_STATUS_OK.equals(user
						.getEmailsverify())) {
					log.info("已经激活。");
					log.info("设置。sesion 客户信息");
					session.setAttribute("customInfo", user);// 客户信息
					log.info("end");
				}
			}
		} else {
			log.info("成功登录。");
			Session session = subject.getSession();// 获取session
			log.info("准备sesion 客户信息。");
			Map<String, Object> map = usersService.queryInfoByUserName(loginDTO
					.getUsername());
			user = JSON.parseObject(JSON.toJSONString(map),
					MapabcBossUser.class);
			if ("Y".equals(user.getEmailsverify())) {
				log.info("已经激活。");
				log.info("设置。sesion 客户信息");
				session.setAttribute("customInfo", user);// 客户信息
				log.info("end");
			}
		}
		return user;
	}

	/**
	 * 登出
	 */
	public void logout() throws Exception {
		this.getSubject().logout();
	}

}
