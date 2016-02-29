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
 * @Title: TopUpController.java
 * @Package com.dataup.ccc.web.controller.myitems
 * @Description: 我的项目-充值记录
 * @author xusheng.liu
 * @date 2015年10月21日 上午11:49:09
 * @version V1.0
 */
@Controller
@RequestMapping("/myitems/recharge")
public class RechargeController {

	private Logger logger = LogManager.getLogger(this.getClass());
	
	@Autowired
	private ApiToolsService cCApiToolsService;//异常
	@Autowired
	private DwDataService dwDataService;

	@RequestMapping("recharge")
	public String recharge(HttpServletRequest request,String system_type) {
		return "myitems/recharge";
	}
	
	/**
	 * @Description: 充值查询
	 * @author xusheng.liu
	 * @date 2015年10月21日 下午2:16:16 
	 * @version V1.0 
	 * @param argsEntity
	 * @return
	 */
	@RequestMapping("queryRecharge")
	public @ResponseBody ResultEntity queryRecharge(
			ArgsEntity argsEntity) {
		logger.entry(argsEntity);
		logger.info("进入queryRecharge方法");
		ResultEntity result = new ResultEntity();
		try {
			if(argsEntity.getSystemId()!=null){
				return dwDataService.queryRecharge(Config.dw_myiterm_recharge,argsEntity);
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
