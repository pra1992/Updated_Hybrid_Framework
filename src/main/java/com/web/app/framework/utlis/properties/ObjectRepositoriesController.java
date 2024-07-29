package com.web.app.framework.utlis.properties;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Properties;

public class ObjectRepositoriesController {
	
	public static String getDOMValue(String fileName, String Key) {
		
		Properties properties = new Properties();
		FileInputStream fis;
		try {
			fis = new FileInputStream(new File(System.getProperty("user.dir")+"/src/test/resources/ObjectsRepositories/" + fileName +".properties" ));
			properties.load(fis);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		 catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return properties.getProperty(Key);
		
	}

	
	
}
