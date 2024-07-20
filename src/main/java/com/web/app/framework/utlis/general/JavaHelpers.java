package com.web.app.framework.utlis.general;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class JavaHelpers {

	public static String getCurrentDate(String pattern) {
		return new SimpleDateFormat(pattern).format(new Date());
	}

	public static void createFileIfNot(String filePath) {
		File file = new File(filePath);
		try {
			file.createNewFile();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	public static String getFolderPathIfNotCreateNew(String folderName) {
		File folder = new File(System.getProperty("user.dir")+ "/" +folderName);
		if(!folder.exists()) {
			folder.mkdir();
			return folder.getAbsolutePath()+"/";
		} else {
			return folder.getAbsolutePath()+"/";
		}
	}

}