package com.dataup.ccc.core.util;

public class EnumConstant {

	public enum COLLECT_CLASS_ENTRANCE_STATUS {
		// 0 省市区检索 1周边检索 2 省市数量查找
		NotEntrance(0), Entrance(1);

		private int code;

		public int getCode() {
			return code;
		}

		private COLLECT_CLASS_ENTRANCE_STATUS(int code) {
			this.code = code;
		}

	}
}
