package com.pc.wirecard.constant;

/**
 * @author gino.q
 * @date June 1, 2020
 *
 */
public final class PoiConstants {

	private PoiConstants() {}
	
	public final class Font {
		public static final short SIZE_9 = 9;
		public static final short SIZE_11 = 11;
		public static final String NAME = "Calibri";
	}
	
	public final class DataFormat {		
		public static final String GENERAL = "General";
		public static final String TEXT = "@";
		public static final String NUMBER = "#,##0.00";
		public static final String CURRENCY = "_(* #,##0.00_);_(* (#,##0.00);_(* \"-\"??_);_(@_)";
		public static final String DATE = "mm/dd/yyyy";
		public static final String PERCENTAGE = "#,##0.00%";
	} 	
}
