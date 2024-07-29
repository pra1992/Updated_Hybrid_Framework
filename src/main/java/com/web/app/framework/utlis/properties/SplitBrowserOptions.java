package com.web.app.framework.utlis.properties;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SplitBrowserOptions {
	
	public static void splitString(String Input) {
     String[] X =  Input.split("-");
       List<String> A = new ArrayList<String>();
       for (String temp : X) {
		  if(temp.isEmpty()) {
			  A.remove(temp);
		  }
			  else {
				  A.add(temp);
			  }
		  }
//       System.out.println(A);
//       System.out.println(A.get(0) + "and " + A.get(1) + "and" + A.size());
		System.out.println(Arrays.asList(Input.split(",")));

	}
	

	public static void main(String[] args) {
		SplitBrowserOptions.splitString("--start-maximized,--disable-notifications,--incognito");

	}

}
