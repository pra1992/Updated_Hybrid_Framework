package com.web.app.framework.utlis.properties.objects;

import org.aeonbits.owner.Config;

@Config.Sources(value = {"file:${user.dir}/src/test/resources/ObjectsRepositories/LoginPage.properties"})
public interface LoginPageElements extends Config {
	
	@Key(value = "sf.lp.username.field")
	String getUsernameObject();

}