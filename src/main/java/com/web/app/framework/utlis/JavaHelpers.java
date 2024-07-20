package com.web.app.framework.utlis;

import java.text.SimpleDateFormat;
import java.util.Date;

public class JavaHelpers {
	
	public static String getCurrentDate(String pattern) {
		return new SimpleDateFormat(pattern).format(new Date());
	}

}