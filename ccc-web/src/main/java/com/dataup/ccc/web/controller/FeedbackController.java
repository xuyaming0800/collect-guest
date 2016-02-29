package com.dataup.ccc.web.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dataup.ccc.api.entity.FeedbackEntity;
import com.dataup.ccc.api.entity.ResultEntity;
import com.dataup.ccc.api.exception.BusinessCode;
import com.dataup.ccc.api.exception.BusinessRunException;
import com.dataup.ccc.api.exception.ExceptionCode;
import com.dataup.ccc.api.service.ApiToolsService;
import com.dataup.ccc.api.service.FeedbackService;

/**
 * 
 * @ClassName: FeedbackController
 * @Description: 信息反馈
 * @author zhanqiao.huang
 * @date 2015年9月17日 下午2:56:10
 */
@Controller
@RequestMapping("/feedback")
public class FeedbackController {
	
	private Logger logger = LogManager.getLogger(this.getClass());
	@Autowired
	private ApiToolsService cCApiToolsService;
	@Autowired
	private FeedbackService feedbackService;
	/**
	 * 业务系统管理主页
	 * 
	 * @return
	 */
	@RequestMapping("/main")
	public String indexPage() {
		return "feedback";
	}
	
	/**
	 * 
	 * @author wenpeng.jin
	 * @date 2015年8月20日
	 * @description 保存html内容
	 * @param detailConfigEntity
	 * @return
	 */
	@RequestMapping("/saveFeedbackInfo")
	public @ResponseBody ResultEntity saveFeedbackInfo(@RequestBody FeedbackEntity feedbackEntity){
		logger.info("进入saveFeedbackInfo方法,保存start");
		ResultEntity result = new ResultEntity();
		try {
			String check=checkNull(new String[]{feedbackEntity.getContext(),feedbackEntity.getCustomId()},new String[]{"context","customeId"});
			if(!check.equals("")){
				logger.error("校验失败："+check);
				result.setSuccess(false);
				result.setCode(ExceptionCode.MISS_REQUEST_PARAMS+"");
				result.setDesc(cCApiToolsService.getExceptionMessage(result.getCode()));
				return result;
			}
			feedbackService.saveFeedbackInfo(feedbackEntity);
			result.setSuccess(true);
			result.setCode(BusinessCode.SAVE_SUCC+"");
			result.setDesc(cCApiToolsService.getMessage(result.getCode()));
			logger.info("进入saveFeedbackInfo方法,保存end");
		} catch(BusinessRunException e){
			result.setSuccess(false);
			result.setCode(ExceptionCode.SYSTEM_ERROR+"");
			result.setDesc(cCApiToolsService.getExceptionMessage(result.getCode()));
			logger.error("进入saveFeedbackInfo方法,系统内容异常",e);
			return result;
		}catch(Exception e){
			result.setSuccess(false);
			result.setCode(ExceptionCode.SYSTEM_ERROR+"");
			result.setDesc(cCApiToolsService.getExceptionMessage(result.getCode()));
			logger.error("进入saveFeedbackInfo方法,保存异常",e);
		}
		logger.info("完成saveFeedbackInfo方法操作");
		return result;
	}
	
	private String checkNull(String[] values,String[] desc){
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
