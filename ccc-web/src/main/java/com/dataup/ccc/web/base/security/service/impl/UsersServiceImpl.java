package com.dataup.ccc.web.base.security.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.dataup.ccc.api.entity.ResultEntity;
import com.dataup.ccc.api.util.HttpClientUtil;
import com.dataup.ccc.api.util.PropConstants;
import com.dataup.ccc.web.base.security.service.UsersService;
import com.dataup.ccc.web.constant.Config;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service("usersService")
public class UsersServiceImpl implements UsersService {

	/**
	 * 根据用户名获取用户信息
	 * 
	 * @param username
	 * @return
	 */
	@Override
	public Map<String, Object> queryInfoByUserName(String username)
			throws Exception {
		try {
			String json = HttpClientUtil.get(userinfo_url + "&username="
					+ username, null);
			ObjectMapper objectMapper = new ObjectMapper();
			ResultEntity resultEntity = objectMapper.readValue(json,ResultEntity.class);
			Map<String, Object> userInfo = (Map<String, Object>) resultEntity.getInfo();
			return userInfo;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * 根据用户查询角色信息
	 * 
	 * @param username
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<String> queryRolesByUserName(String username) throws Exception {
		try {
			String json = HttpClientUtil.get(
					role_url + "&username=" + username, null);
			ObjectMapper objectMapper = new ObjectMapper();
			ResultEntity resultEntity = objectMapper.readValue(json,
					ResultEntity.class);
			List info = (List) resultEntity.getInfo();
			return info;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	
	@Override
	public List<String> queryCustomsByType(String type) throws Exception {
		try {
			String json = HttpClientUtil.get(
					custom_url + "&userType=" + type+"&accountType="+type, null);
			ObjectMapper objectMapper = new ObjectMapper();
			ResultEntity resultEntity = objectMapper.readValue(json,
					ResultEntity.class);
			List info = (List) resultEntity.getInfo();
			return info;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	/**
	 * 根据用户信息查询权限信息
	 * 
	 * @param username
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<String> queryPermissionsByUserName(String username)
			throws Exception {
		try {
			String json = HttpClientUtil.get(permission_url + "&username="
					+ username, null);
			ObjectMapper objectMapper = new ObjectMapper();

			ResultEntity resultEntity = objectMapper.readValue(json,
					ResultEntity.class);
			List info = (List) resultEntity.getInfo();
			return info;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	@Override
	public ResultEntity resetPassword(String username, String password) throws Exception {
		try {
			String json = HttpClientUtil.get(resetpass_url + "&username=" + username+"&password="+password, null);
			ObjectMapper objectMapper = new ObjectMapper();
			ResultEntity resultEntity = objectMapper.readValue(json,ResultEntity.class);
			return resultEntity;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		
	}
	
	/**
	 * @Description: 验证用户的邮箱是否存在，（已激活中）
	 * @author xudsheng.liu
	 * @date 2015年9月14日 上午10:08:27 
	 * @version V1.0 
	 * @param email
	 * @return
	 * @throws Exception
	 */
	public boolean emailExist(String email) throws Exception{
		try {
			String json = HttpClientUtil.get(Config.check_email_is_exist_url + "&email=" + email, null);
			return Boolean.parseBoolean(json);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	/**
	 * 
	 * @Title: isActivateUser 
	 * @Description: 判断用户是否激活
	 * @param @param userName
	 * @param @return
	 * @param @throws Exception 设定文件 
	 * @return boolean 返回类型 
	 * @throws
	 */
	public boolean isActivateUser(String userName) throws Exception{
		try {
			String json = HttpClientUtil.get(Config.check_email_is_exist_url + "&username=" + userName, null);
			return Boolean.parseBoolean(json);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	@Override
	public boolean userNameExist(String userName) throws Exception {
		try {
			String json = HttpClientUtil.get(Config.check_username_is_exist_url + "&username=" + userName, null);
			return Boolean.parseBoolean(json);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	@Override
	public boolean activateEmail(String username ) throws Exception {
		try {
			String json = HttpClientUtil.get(Config.update_email_sverify + "&username=" + username, null);
			return Boolean.parseBoolean(json);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	@Override
	public ResultEntity modifyPassword(String username,String oldPass,String newPass)
			throws Exception {
		try {
			String json = HttpClientUtil.get(modifypass_url + "&username=" + username+"&oldPass="+oldPass+"&newPass="+newPass, null);
			ObjectMapper objectMapper = new ObjectMapper();
			ResultEntity resultEntity = objectMapper.readValue(json,ResultEntity.class);
			return resultEntity;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	@Override
	public ResultEntity modifyEmail(String email, String username) throws Exception{
		try {
			String json = HttpClientUtil.get(modifyemail_url + "&username=" + username+"&email="+email, null);
			ObjectMapper objectMapper = new ObjectMapper();
			ResultEntity resultEntity = objectMapper.readValue(json,ResultEntity.class);
			return resultEntity;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	@Value("${" + PropConstants.GET_CCC_UESRIFO_URL + "}")
	private String userinfo_url;

	@Value("${" + PropConstants.GET_CCC_ROLE_URL + "}")
	private String role_url;

	@Value("${" + PropConstants.GET_CCC_PERMISSION_URL + "}")
	private String permission_url;
	
	
	@Value("${" + PropConstants.CCC_RESET_PASSWORD + "}")
	private String resetpass_url;
	
	@Value("${" + PropConstants.CCC_MODIFY_PASSWORD + "}")
	private String modifypass_url;
	
	@Value("${" + PropConstants.CCC_MODIFY_EMAIL + "}")
	private String modifyemail_url;
	
	@Value("${" + PropConstants.GET_CCC_CUSTOM_URL + "}")
	private String custom_url;


}
