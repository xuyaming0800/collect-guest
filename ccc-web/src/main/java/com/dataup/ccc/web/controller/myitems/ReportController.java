package com.dataup.ccc.web.controller.myitems;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dataup.ccc.api.entity.ArgsEntity;
import com.dataup.ccc.api.entity.ResultEntity;
import com.dataup.ccc.api.service.ApiToolsService;
import com.dataup.ccc.api.service.DwDataService;
import com.dataup.ccc.web.constant.Config;
import com.dataup.ccc.web.constant.CustomConstant;
/**
 * 
 * @ClassName: ReportController 
 * @Description: 我的项目-区域统计、分类统计、等
 * @author zhanqiao.huang
 * @date 2015年9月15日 下午3:30:49
 */
@Controller
@RequestMapping("/myitems/report")
public class ReportController {
	
	private Logger logger = LogManager.getLogger(this.getClass());
	
	@Autowired
	private ApiToolsService cCApiToolsService;
	@Autowired
	private DwDataService dwDataService;
	
	
	/**
	 * @Description: 区域统计
	 * @author xusheng.liu
	 * @date 2015年9月15日 下午3:44:24 
	 * @version V1.0 
	 * @return
	 */
	@RequestMapping("areaStatistics")
	public String getAreaSta(HttpServletRequest request,String system_type) {
		return "myitems/report_area";
	}
	
	/**
	 * @Description: 分类统计
	 * @author xusheng.liu
	 * @date 2015年9月15日 下午3:44:24 
	 * @version V1.0 
	 * @return
	 */
	@RequestMapping("classifyStatistics")
	public String classifyStatistics(HttpServletRequest request,String system_type) {
		return "myitems/report_classify";
	}
	
	/**
	 * @Description: 区域统计列表
	 * @author xusheng.liu
	 * @date 2015年9月15日 下午4:59:42 
	 * @version V1.0 
	 * @param pageNo
	 * @param pageSIze
	 * @return
	 */
	@RequestMapping("queryAreaStaList")
	public @ResponseBody ResultEntity queryAreaStaList(ArgsEntity argsEntity) {
		logger.entry(argsEntity);
		logger.info("执行queryAreaStaList方法");
		ResultEntity resultEntity = new ResultEntity();
		argsEntity.setFunType(CustomConstant.QUERY_TYPE_LIST);
		try {
			return dwDataService.query(Config.dw_myiterm_statistics_area_list,argsEntity);
		} catch (Exception e) {
			resultEntity.setSuccess(false);
			resultEntity.setDesc("系统异常");
			logger.info("queryAreaStaList方法执行失败");
			e.printStackTrace();
		}
		return logger.exit(resultEntity);
	}
	
	/**
	 * @Description: 分类统计列表
	 * @author xusheng.liu
	 * @date 2015年9月15日 下午4:59:42 
	 * @version V1.0 
	 * @param pageNo
	 * @param pageSIze
	 * @return
	 */
	@RequestMapping("queryClassifyList")
	public @ResponseBody ResultEntity queryClassifyList(ArgsEntity argsEntity) {
		logger.entry(argsEntity);
		logger.info("执行queryClassifyList方法");
		ResultEntity resultEntity = new ResultEntity();
		argsEntity.setFunType(CustomConstant.QUERY_TYPE_LIST);
		try {
			return dwDataService.query(Config.dw_myiterm_statistics_classify_list,argsEntity);
		} catch (Exception e) {
			resultEntity.setSuccess(false);
			resultEntity.setDesc("系统异常");
			logger.info("queryClassifyList方法执行失败");
			e.printStackTrace();
		}
		return logger.exit(resultEntity);
	}
	
	/**
	 * @Description: 查询统计明细
	 * @author xusheng.liu
	 * @date 2015年9月17日 上午11:23:22 
	 * @version V1.0 
	 * @param pageNo
	 * @param pageSize
	 * @param systemId
	 * @param area
	 * @return
	 */
	@RequestMapping("queryAreaStaDetailList")
	public @ResponseBody ResultEntity queryAreaStaDetailList(ArgsEntity argsEntity) {
		logger.entry(argsEntity);
		logger.info("执行queryDetailList方法");
		ResultEntity resultEntity = new ResultEntity();
		argsEntity.setFunType(argsEntity.getFunType()==null?
				CustomConstant.QUERY_TYPE_DETAIL:argsEntity.getFunType());
		try {
			return dwDataService.query(Config.dw_myiterm_statistics_detail,argsEntity);
		} catch (Exception e) {
			resultEntity.setSuccess(false);
			resultEntity.setDesc("系统异常");
			logger.info("queryDetailList方法执行失败");
			e.printStackTrace();
		}
		return logger.exit(resultEntity);
	}
	
	/**
	 * @Description: 查询项目信息
	 * @author xusheng.liu
	 * @date 2015年9月23日 下午4:07:45
	 * @version V1.0
	 * @return
	 */
	@RequestMapping("queryProjectInfo")
	public @ResponseBody ResultEntity queryProjectInfo(
			@RequestParam("system_type") String systemId,HttpServletRequest request) {
		try {
			logger.info("进入查询用户项目信息方法queryProjectInfo");
			ResultEntity projectInfo = dwDataService.queryProjectInfo(Config.dw_myiterm_project_info,systemId);
			Object info = projectInfo.getInfo();
			HttpSession session = request.getSession();
			//Session session = SecurityUtils.getSubject().getSession();// 获取session
			session.setAttribute("projectInfo", info);
			return projectInfo;
		} catch (Exception e) {
			ResultEntity result = new ResultEntity();
			result.setDesc("查询项目信息失败");
			logger.error("查询项目信息失败", e);
			return result;
		}
	}
	
	/**
	 * @Description: 费用支出查询跳转
	 * @author xusheng.liu
	 * @date 2015年10月19日 下午6:00:10 
	 * @version V1.0 
	 * @param request
	 * @param system_type
	 * @return
	 */
	@RequestMapping("fareQuery")
	public String fareQuery(HttpServletRequest request,String system_type) {
		return "myitems/fare_query";
	}
	
	/**
	 * @Description: 查询支付列表
	 * @author xusheng.liu
	 * @date 2015年10月19日 下午7:06:58 
	 * @version V1.0 
	 * @param argsEntity
	 * @return
	 */
	@RequestMapping("queryPayList")
	public @ResponseBody ResultEntity queryPayList(ArgsEntity argsEntity) {
		logger.entry(argsEntity);
		logger.info("执行queryPayList方法");
		ResultEntity resultEntity = new ResultEntity();
		argsEntity.setFunType(CustomConstant.QUERY_TYPE_PAY_LIST);
		try {
			return dwDataService.query(Config.dw_myiterm_pay_query,argsEntity);
		} catch (Exception e) {
			resultEntity.setSuccess(false);
			resultEntity.setDesc("系统异常");
			logger.info("queryPayList方法执行失败");
			e.printStackTrace();
		}
		return logger.exit(resultEntity);
	}
	
}