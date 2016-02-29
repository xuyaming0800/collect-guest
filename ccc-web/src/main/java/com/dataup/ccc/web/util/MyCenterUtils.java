package com.dataup.ccc.web.util;

import java.io.File;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.dataup.ccc.web.constant.Config;
import com.dataup.ccc.web.entity.mycenter.FileInfo;
import com.dataup.ccc.web.exception.FileTypeNotAllowException;

/**
 * @Title: MyCenterUtils.java
 * @Package com.dataup.ccc.web.util
 * @Description: 客户中心工具类
 * @author xusheng.liu
 * @date 2015年9月19日 下午6:05:29
 * @version V1.0
 */
public class MyCenterUtils {
	private Logger logger = LogManager.getLogger(this.getClass());
	/**
	 * @Description: 获取项目跟路径
	 * @author xusheng.liu
	 * @date 2015年9月19日 下午6:05:51
	 * @version V1.0
	 * @param request
	 * @return
	 */
	public static String getContext(HttpServletRequest request) {
		String path = request.getContextPath();
		String basePath = request.getScheme() + "://" + request.getServerName()
				+ ":" + request.getServerPort() + path + "/";
		return basePath;
	}

	/**
	 * @Description: 验证邮箱格式
	 * @author xusheng.liu
	 * @date 2015年9月19日 下午5:49:09
	 * @version V1.0
	 * @param email
	 * @return
	 */
	public static boolean checkEmailByRegex(String email) {
		boolean flag = false;
		try {
			String check = "^([a-zA-Z0-9_\\.\\-])+\\@(([a-zA-Z0-9\\-])+\\.)+([a-zA-Z0-9]{1,8})+$";
			Pattern regex = Pattern.compile(check);
			Matcher matcher = regex.matcher(email);
			flag = matcher.matches();
		} catch (Exception e) {
			flag = false;
		}
		return flag;
	}

	public static String checkNull(String[] values, String[] desc) {
		if (values == null || desc == null) {
			return "校验的值和要校验的数据不符";
		} else if (values.length != desc.length) {
			return "校验的值和要校验的数据不符";
		} else {
			for (int i = 0; i < values.length; i++) {
				if (values[i] == null || values[i].equals("")) {
					return desc[i] + "不能为空";
				}
			}
		}
		return "";
	}

	public static String Encrypt(String strSrc) {
		MessageDigest md = null;
		String strDes = null;
		byte[] bt = strSrc.getBytes();
		try {
			md = MessageDigest.getInstance("SHA-1");
			md.update(bt);
			strDes = bytes2Hex(md.digest()); // to HexString
		} catch (NoSuchAlgorithmException e) {
			System.out.println("Invalid algorithm.");
			return null;
		}
		return strDes;
	}

	public static String bytes2Hex(byte[] bts) {
		String des = "";
		String tmp = null;
		for (int i = 0; i < bts.length; i++) {
			tmp = (Integer.toHexString(bts[i] & 0xFF));
			if (tmp.length() == 1) {
				des += "0";
			}
			des += tmp;
		}
		return des;
	}

	/**
	 * 
	 * @param file 上传的文件
	 * @param modulename 模块名称
	 * @param extAllowed 支持的文件类型列表
	 * @return
	 * @throws IOException 
	 */
	public FileInfo uploadFile(MultipartFile file,String modulename,String[] extAllowed) throws IOException {
		logger.info("-->进入uploadFile上传方法");
		logger.info("---->入参：file"+file+"；modulename："+modulename+"；id："+"extAllowed："+extAllowed);
		if(file!=null && file.getSize()>0){
			double fileLen = file.getBytes().length;
			double size = fileLen/1024.0;
			String fileName = file.getOriginalFilename();
			String fileExt = fileName.substring(fileName.lastIndexOf(".")+1).toLowerCase();
			if(extAllowed!=null && extAllowed.length>0 && !Arrays.asList(extAllowed).contains(fileExt)){
				logger.info("-->图片格式不正确，抛出异常");
				throw new FileTypeNotAllowException(extAllowed,"fileExt not in allowed list:"+extAllowed.toString());
			}
			String baseDir = Config.base_logo_path;//File.separator
			String subDir = "/" + modulename + "/" + DateUtils.getYear() 
					+ "/" + DateUtils.getMonth() + "/" + DateUtils.getDay() + "/";
			String dir = baseDir + subDir;
			logger.info("---->文件夹根路径："+baseDir);
			logger.info("---->文件夹子路径："+subDir);
			logger.info("---->文件夹全路径："+dir);
			File imgfile = new File(dir);
			if(!imgfile.exists()){
				logger.info("---->文件夹不存在，创建");
				imgfile.mkdirs();
			}
			String imgpath = subDir + DateUtils.getDate("yyyyMMddhhmmss") + createRandom(4) + "." +fileExt;
			logger.info("---->文件路径："+imgpath);
			StringBuffer sbRealPaths = new StringBuffer();  
			sbRealPaths.append(baseDir).append(imgpath);  
			//写入文件  
			File fileTo = new File(sbRealPaths.toString());  
			try {
				((CommonsMultipartFile) file).getFileItem().write(fileTo);
				logger.info("-->文件上传成功");
			} catch (Exception e) {
				logger.error("error:", e);
				return null;
			}
			FileInfo fileinfo = new FileInfo();
			fileinfo.setName(fileName);
			fileinfo.setPath(imgpath);
			fileinfo.setSize(size);
			logger.info("——>返回对象fileinfo："+fileinfo);
			return fileinfo;
		} else {
			return null;
		}
	}

	/**
	 * @Description: 生成一个n位随机数
	 * @author xusheng.liu
	 * @date 2015年11月9日 下午6:26:17 
	 * @version V1.0 
	 * @param min
	 * @param max
	 * @return
	 */
	private String createRandom(int n) {
		Random random = null;
		StringBuffer sb = null;
		if(n > 0){
			sb = new StringBuffer();
			random = new Random();
			for (int i = 0; i < n; i++) {
				sb.append(random.nextInt(10));
			}
		}
		return sb.toString();
	}
}
