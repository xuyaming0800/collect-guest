package com.dataup.ccc.web.controller.myitems;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.geo.util.CoordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dataup.ccc.api.entity.ResultEntity;
import com.dataup.ccc.api.service.DwDataService;
import com.dataup.ccc.web.constant.Config;

/**
 * 
 * @ClassName: ExportController
 * @Description: 我的项目-数据导出（成果）
 * @author zhanqiao.huang
 * @date 2015年9月15日 下午3:29:37
 */
@Controller
@RequestMapping("/myitems/exports")
public class ExportController {
	@Autowired
	private DwDataService dwDataService;
	private Logger logger = LogManager.getLogger(this.getClass());

	@RequestMapping("result")
	public String result() {
		return "myitems/result";
	}

	// @RequestParam("system_type")
	@RequestMapping("doExp")
	public @ResponseBody ResultEntity doExp(String beginTime, String endTime,
			String systemId ,HttpServletResponse response) {
		try {
			logger.info("-->进入导出成果方法doExp");
			logger.info("-->入参:beginTime=" + beginTime + ";endTime=" + endTime
					+ ";systemId=" + systemId );
			//1.设置文件ContentType类型，这样设置，会自动判断下载文件类型  
	        response.setContentType("multipart/form-data");  
	        //2.设置文件头：最后一个参数是设置下载文件名(假如我们叫a.pdf)  
	        response.setHeader("Content-Disposition", "attachment;fileName="+"exportResult.csv");  
	        ServletOutputStream out = response.getOutputStream();  
			ResultEntity projectInfo = dwDataService.doExp(
					Config.exportResult, beginTime,endTime,systemId);
			exportFile((List<Map<?, ?>>)projectInfo.getInfo(),(List<String>)projectInfo.getOtherInfo(),out);
			return projectInfo;
		} catch (Exception e) {
			ResultEntity result = new ResultEntity();
			result.setDesc("查询项目信息失败");
			logger.error("查询项目信息失败", e);
			return result;
		}
	}
	
	public void exportFile(List<Map<?, ?>> oList, List<String> layerList,ServletOutputStream out) {
		logger.info("-->进入导出方法exportFile");
		logger.info("-->入参:layerList="+layerList+";out="+out);
		OutputStreamWriter writer = null;
		BufferedWriter bw = null;
		try {
			writer = new OutputStreamWriter(out, "UTF-8");
			bw = new BufferedWriter(writer);
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < oList.size(); i++) {
				if (i == 0) {
					sb.append("序号");
					sb.append(",");
					sb.append("任务名");
					sb.append(",");
					sb.append("任务类型");
					sb.append(",");
					sb.append("省份");
					sb.append(",");
					sb.append("城市");
					sb.append(",");
					sb.append("采集时间");
					sb.append(",");
					sb.append("子任务类型");
					sb.append(",");
					sb.append("子任务名称");
					sb.append(",");
					sb.append("子任务ID");
					sb.append(",");
					/*sb.append("项目id");
					sb.append(",");*/
					sb.append("百度坐标");
					sb.append(",");
					sb.append("GPS坐标");
					sb.append(",");
					sb.append("图片路径");
					sb.append(",");
					int count = 1;
					for (String properName : layerList) {
						sb.append(properName);
						if (count != layerList.size()) {
							sb.append(",");
						}
						count++;
					}
					bw.write(sb.toString());
					bw.newLine();
				}
				sb.setLength(0);
				Map<?, ?> overlay = oList.get(i);
				sb.append("\"");
				sb.append(i+1);
				sb.append("\"");
				sb.append(",");
				sb.append("\"");
				sb.append(overlay.get("collect_task_name"));
				sb.append("\"");
				sb.append(",");
				sb.append("\"");
				sb.append(overlay.get("task_class_name"));
				sb.append("\"");
				sb.append(",");
				sb.append("\"");
				sb.append(overlay.get("province"));
				sb.append("\"");
				sb.append(",");
				sb.append("\"");
				sb.append(overlay.get("city"));
				sb.append("\"");
				sb.append(",");
				sb.append("\"");
				DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				sb.append(format.format(new Date(Long.parseLong(overlay.get("submit_time").toString()))));
				sb.append("\"");
				sb.append(",");
				sb.append("\"");
				sb.append(overlay.get("childTaskType"));
				sb.append("\"");
				sb.append(",");
				sb.append("\"");
				sb.append(overlay.get("childTaskName"));
				sb.append("\"");
				sb.append(",");
				sb.append("\"");
				sb.append(overlay.get("childTaskId"));
				/*sb.append("\"");
				sb.append(",");
				sb.append("\"");
				sb.append(overlay.get("system_type"));*/
				sb.append("\"");
				sb.append(",");
				sb.append("\"");
				sb.append(overlay.get("coordinates_baidu")==null?'-':overlay.get("coordinates_baidu"));
				sb.append("\"");
				sb.append(",");
				sb.append("\"");
				List<Double> baidu = (List<Double>) overlay.get("coordinates_baidu");
				if(baidu!=null && baidu.size()>0){
					double[] gps_xy = new double[2];
					CoordUtils.bd2gps(baidu.get(0),baidu.get(1), gps_xy);
					sb.append("["+gps_xy[0]+","+gps_xy[1]+"]");
					sb.append("\"");
					sb.append(",");
					sb.append("\"");
				}
				sb.append(overlay.get("imageUrlString"));
				sb.append("\"");
				sb.append(",");
				Map<String, String> propMap = makePropMap((List<Map<String, String>>) overlay
						.get("props"));
				int count = 1;
				for (String entry : layerList) {
					String propValue = propMap.get(entry);
					sb.append("\"");
					sb.append(propValue == null ? "-" : propValue);
					sb.append("\"");
					if (count != layerList.size()) {
						sb.append(",");
					}
					count++;
				}
				bw.write(sb.toString());
				bw.newLine();
			}
			logger.info("-->写结束");
			bw.flush();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				bw.close();
				writer.close();
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public Map<String, String> makePropMap(List<Map<String, String>> props) {
		Map<String, String> propMap = new HashMap<String, String>();
		if (props != null && props.size() > 0) {
			for (Map<String, String> prop : props) {
				propMap.put(prop.get("prop_name"), prop.get("prop_value"));
			}
		}
		return propMap;
	}
}
