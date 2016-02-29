package com.dataup.ccc.api.exception;

public class BusinessCode {
	
	/**
	 * 保存成功
	 */
	public static long SAVE_SUCC = 900001L;
	/**
	 * 更新成功
	 */
	public static long UPDATE_SUCC = 900002L;
	/**
	 * 删除成功
	 */
	public static long DELETE_SUCC = 900003L;
	/**
	 *激活成功
	 */
	public static long ACTIVATE_SUCC = 900004L;
	/**
	 * 废弃成功
	 */
	public static long SCRAP_SUCC = 900005L;
	
	/**
	 * 存在新版本
	 */
	public static long EXIST_MAX_VERSION_NO = 800000L;
	
	/**
	 * 不存在最新版本号 当前版本已是最新版本
	 */
	public static long NO_EXIST_MAX_VERSION_NO = 800001L;
	/**
	 * 黑白名单存在项目
	 */
	public static long EXIST_PROJECT = 800002L;
	/**
	 *  黑白名单不存在项目
	 */
	public static long NO_EXIST_PROJECT = 800003L;
	
	
	/**
	 * redis 异常
	 */
	public static long REDIS_EXCEPTION = 800004L;
	/**
	 * redis 锁
	 */
	public static long LOCK_BY＿REDIS = 800005L;
	
	
	
	
}
