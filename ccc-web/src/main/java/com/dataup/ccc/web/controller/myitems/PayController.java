package com.dataup.ccc.web.controller.myitems;


import javax.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dataup.ccc.api.entity.ArgsEntity;
import com.dataup.ccc.api.entity.ResultEntity;
import com.dataup.ccc.api.exception.ExceptionCode;
import com.dataup.ccc.api.service.ApiToolsService;
import com.dataup.ccc.api.service.DwDataService;
import com.dataup.ccc.web.constant.Config;

/**
 * @Title: PayController.java
 * @Package com.dataup.ccc.web.controller.myitems
 * @Description: 我的项目-费用支出
 * @author xusheng.liu
 * @date 2015年10月19日 下午3:44:41
 * @version V1.0
 */
@Controller
@RequestMapping("/myitems/pay")
public class PayController {

	private Logger logger = LogManager.getLogger(this.getClass());
	
	@Autowired
	private ApiToolsService cCApiToolsService;
	@Autowired
	private DwDataService dwDataService;

	@RequestMapping("fareQuery")
	public String datashow(HttpServletRequest request,String system_type) {
		return "myitems/fare_query";
	}
	
	/**
	 * @Description: 费用支出查询
	 * @author xusheng.liu
	 * @date 2015年9月20日 上午1:43:12 
	 * @version V1.0 
	 * @param status
	 * @param system
	 * @return
	 */
	@RequestMapping("detailQuery")
	public @ResponseBody ResultEntity queryTaskByStatusAndSystem(
			ArgsEntity argsEntity) {
		logger.entry(argsEntity);
		logger.info("进入queryTaskByStatusAndSystem方法");
		ResultEntity result = new ResultEntity();
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
}
