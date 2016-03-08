package com.moxingwang.springmvc.common.constant;


public class EnumConstant {

	public enum ItemTypeEnum {
		NUMBER("N"),
		TEXT("T"),
		ONLY("O"),
		MULTI("M"),
		READ("R");

		private String value;

		public String getValue() {
			return this.value;
		}
		private ItemTypeEnum(String value) {
			this.value = value;
		}
	}





}
