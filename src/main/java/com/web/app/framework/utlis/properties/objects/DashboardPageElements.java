package com.web.app.framework.utlis.properties.objects;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.Key;



public interface DashboardPageElements extends Config {


	@Key(value = "options.tab")
	String options();
}
