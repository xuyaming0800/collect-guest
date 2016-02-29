package com.dataup.ccc.web.controller.myitems;


import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dataup.ccc.api.entity.ArgsEntity;
import com.dataup.ccc.api.entity.ResultEntity;
import com.dataup.ccc.api.exception.ExceptionCode;
import com.dataup.ccc.api.service.ApiToolsService;
import com.dataup.ccc.api.service.DwDataService;
import com.dataup.ccc.web.constant.Config;
import com.dataup.ccc.web.constant.CustomConstant;

/**
 * 
 * @ClassName: ReportController
 * @Description: 我的项目-数据显示-地图
 * @author zhanqiao.huang
 * @date 2015年9月15日 下午3:30:49
 */
@Controller
@RequestMapping("/myitems/map")
public class MapController {

	private Logger logger = LogManager.getLogger(this.getClass());
	
	@Autowired
	private ApiToolsService cCApiToolsService;
	@Autowired
	private DwDataService dwDataService;

	@RequestMapping("dataShow")
	public String datashow(HttpServletRequest request,String system_type) {
		return "myitems/data_show";
	}
	
	/**
	 * @Description: 数据展示查询
	 * @author xusheng.liu
	 * @date 2015年9月20日 上午1:43:12 
	 * @version V1.0 
	 * @param status
	 * @param system
	 * @return
	 */
	@RequestMapping("/map_task/query")
	public @ResponseBody ResultEntity queryTaskByStatusAndSystem(
			ArgsEntity argsEntity) {
		logger.entry(argsEntity);
		logger.info("进入queryTaskByStatusAndSystem方法");
		ResultEntity result = new ResultEntity();
		argsEntity.setFunType(CustomConstant.QUERY_TYPE_DETAIL);
		try {
			if(argsEntity.getSystemId()!=null){
				return dwDataService.query(Config.dw_myiterm_map_show,argsEntity);
			}else{
				result.setSuccess(false);
				result.setCode(ExceptionCode.MISS_REQUEST_PARAMS + "");
				result.setDesc(cCApiToolsService.getExceptionMessage(result
						.getCode()));				
			}
			return result;
		}catch(Exception e){
			result.setSuccess(false);
			result.setCode(ExceptionCode.SYSTEM_ERROR + "");
			result.setDesc(cCApiToolsService.getExceptionMessage(result
					.getCode()));
			return result;
		}
	}
	
	@RequestMapping("loadProvincial")
	public @ResponseBody ResultEntity loadProvincial(
			@RequestParam("system_type") String system_type) {
		logger.info("传入参数system_type："+system_type);
		logger.info("进入loadProvincial方法");
		ResultEntity result = new ResultEntity();
		try {
			if(system_type!=null){
				return dwDataService.loadProvincial(Config.dw_myiterm_map_loadprovincial_city,system_type);
			}else{
				result.setSuccess(false);
				result.setCode(ExceptionCode.MISS_REQUEST_PARAMS + "");
				result.setDesc(cCApiToolsService.getExceptionMessage(result.getCode()));				
			}
			return result;
		}catch(Exception e){
			result.setSuccess(false);
			result.setCode(ExceptionCode.SYSTEM_ERROR + "");
			result.setDesc(cCApiToolsService.getExceptionMessage(result.getCode()));
			return result;
		}
	}
	
	@RequestMapping("loadCity")
	public @ResponseBody ResultEntity loadCity(
			@RequestParam("system_type") String system_type,
			@RequestParam("provincial") String provincial) {
		logger.info("传入参数system_type："+system_type);
		logger.info("传入参数provincial："+provincial);
		logger.info("进入loadCity方法");
		ResultEntity result = new ResultEntity();
		try {
			if(system_type!=null){
				return dwDataService.loadCity(Config.dw_myiterm_map_loadprovincial_city,system_type,provincial);
			}else{
				result.setSuccess(false);
				result.setCode(ExceptionCode.MISS_REQUEST_PARAMS + "");
				result.setDesc(cCApiToolsService.getExceptionMessage(result.getCode()));				
			}
			return result;
		}catch(Exception e){
			result.setSuccess(false);
			result.setCode(ExceptionCode.SYSTEM_ERROR + "");
			result.setDesc(cCApiToolsService.getExceptionMessage(result.getCode()));
			return result;
		}
	}
}
