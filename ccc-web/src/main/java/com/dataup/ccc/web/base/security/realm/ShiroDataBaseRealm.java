package com.dataup.ccc.web.base.security.realm;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.dataup.ccc.web.base.security.service.UsersService;


public class ShiroDataBaseRealm extends AuthorizingRealm {

	@Autowired
	private UsersService usersService = null;

	/**
	 * 获取当前用户的角色和权限信息并进行缓存
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) throws AuthenticationException{
		if (principals == null) {
			throw new AuthorizationException("Principal对象不能为空");
		}
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

		// 因为非正常退出，即没有显式调用 SecurityUtils.getSubject().logout()
		// (可能是关闭浏览器，或超时)，但此时缓存依旧存在(principals)，所以会自己跑到授权方法里。
		if (!SecurityUtils.getSubject().isAuthenticated()) {
			doClearCache(principals);
			SecurityUtils.getSubject().logout();
			return null;
		}
		Object uObject = principals.fromRealm(getName()).iterator().next();
		String username = (String) uObject;
		try {
			info.addRoles(usersService.queryRolesByUserName(username));
			info.addStringPermissions(usersService.queryPermissionsByUserName(username));
		} catch (Exception e) {
		}
		return info;
	}

	/**
	 * 通过用户名获取密码，由于shiro负责校验
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		// token中储存着输入的用户名
		UsernamePasswordToken upToken = (UsernamePasswordToken) token;
		// 获得用户名
		String username = upToken.getUsername();
		String password=null;
		try {
			password = (String)usersService.queryInfoByUserName(username).get("pwd");
			return new SimpleAuthenticationInfo(username, password, getName());
		} catch (Exception e) {
			e.printStackTrace();
		}// 获得用户对应的密码
		return null;
	}

}
