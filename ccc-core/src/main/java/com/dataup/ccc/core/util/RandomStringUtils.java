package com.dataup.ccc.core.util;

import java.util.Random;

/**
 * 生成随机字符
 * @author chunsheng.zhang
 *
 */
public class RandomStringUtils {
	/**
	 * 
	 * @Methods Name 随机生成字符，含大写、小写、数字
	 * @Create In 2013-10-11 By hongliang.gao
	 * @param length
	 *            生成随机数的长度
	 * @param intArr
	 *            生成随机字符的类型：0：大写字符，1：小写字符，2或者其他：数字；如果为空全包括
	 * @return String 随机字符串
	 */
	public static String getRandomString(int length, int[] intArr) {

		StringBuffer randStrBuf = new StringBuffer();
		Random random = new Random();
		int len = intArr.length;
		len = len <= 0 ? 2 : len;
		for (int i = 0; i < length; i++) {

			int index = random.nextInt(len);
			int indexValue = 0;
			if (intArr.length == 0) {
				indexValue = index;
			} else {
				indexValue = intArr[index];
			}
			String randChar = "";
			switch (indexValue) {
			case 0:// 大写字符
				randChar = String
						.valueOf((char) Math.round(Math.random() * 25 + 65));
				break;
			case 1:// 小写字符
				randChar = String
						.valueOf((char) Math.round(Math.random() * 25 + 97));
				break;
			default:// 数字
				randChar = String.valueOf(Math.round(Math.random() * 9));
				break;
			}
			randStrBuf.append(randChar);
		}
		return randStrBuf.toString();
	}
}
