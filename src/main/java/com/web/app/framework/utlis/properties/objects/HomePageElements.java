package com.web.app.framework.utlis.properties.objects;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.Key;

public interface HomePageElements extends Config {
	@Key(value = "toggle.button")
	String toggle();
	
	@Key(value = "viewAll.button")
	String viewAll();
	
	@Key(value = "serviceconsole.button")
	String serviceconsole();
	
	@Key(value ="sales.button")
	String sales();
	

}
