package com.dataup.ccc.web.base.security.service;

import java.util.List;
import java.util.Map;

import com.dataup.ccc.api.entity.ResultEntity;

 
/**
 * 
 * @ClassName: UserService 
 * @Description: 用户登录 
 * @author zhanqiao.huang
 * @date 2015年9月7日 下午3:53:29
 */
public interface UsersService {
	/**
	 * 根据用户名获取用户信息
	 * 
	 * @param username
	 * @return
	 */
	public Map<String, Object> queryInfoByUserName(String username)
			throws Exception;

	/**
	 * 根据用户查询角色信息
	 * 
	 * @param username
	 * @return
	 */
	public List<String> queryRolesByUserName(String username) throws Exception;
	
	/**
	 * 
	 * @Title: queryUsersByType 
	 * @Description: 获取某一类型的用户列表 
	 * @param @param type 用户类型
	 * @param @return
	 * @param @throws Exception 设定文件 
	 * @return List<String> 返回类型 
	 * @throws
	 */
	public List<String> queryCustomsByType(String type) throws Exception;

	/**
	 * 根据用户信息查询权限信息
	 * 
	 * @param username
	 * @return
	 */
	public List<String> queryPermissionsByUserName(String username)
			throws Exception;
	
	
	/**
	 * 
	 * @Title: resetPassword 
	 * @Description: 重置密码 
	 * @param @param username
	 * @param @param password
	 * @param @return 设定文件 
	 * @return boolean 返回类型 
	 * @throws
	 */
	public ResultEntity resetPassword(String username,String password) throws Exception ;
	
	/**
	 * 
	 * @Title: modifyPassword 
	 * @Description: 个人中心-修改密码 
	 * @param @param username
	 * @param @param password
	 * @param @return
	 * @param @throws Exception 设定文件 
	 * @return ResultEntity 返回类型 
	 * @throws
	 */
	public ResultEntity modifyPassword(String username,String oldPass,String newPass) throws Exception ;
	
	/**
	 * @Description: 验证激活邮箱是否存在	false:不存在;true:存在;
	 * @author xudsheng.liu
	 * @date 2015年9月14日 上午10:19:11 
	 * @version V1.0 
	 * @param email
	 * @return 
	 * @throws Exception
	 */
	public boolean emailExist(String email) throws Exception ;

	/**
	 * @Description: 验证用户名是否存在	false:不存在;true:存在;
	 * @author xudsheng.liu
	 * @date 2015年9月14日 上午10:29:42 
	 * @version V1.0 
	 * @param userName
	 * @return
	 * @throws Exception
	 */
	public boolean userNameExist(String userName) throws Exception ;

	/**
	 * @Description: 激活邮箱
	 * @author xusheng.liu
	 * @date 2015年9月14日 下午1:35:40 
	 * @version V1.0 
	 * @param username
	 * @return
	 * @throws Exception 
	 */
	public boolean activateEmail(String username ) throws Exception;

	/**
	 * @Description: 个人中心-修改邮箱
	 * @author xusheng.liu
	 * @date 2015年9月21日 上午11:52:48 
	 * @version V1.0 
	 * @param email
	 * @param username
	 */
	public ResultEntity modifyEmail(String email, String username)throws Exception;
	
}
