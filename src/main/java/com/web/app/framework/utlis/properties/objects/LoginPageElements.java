package com.web.app.framework.utlis.properties.objects;

import org.aeonbits.owner.Config;

@Config.Sources(value = {"file:${user.dir}/src/test/resources/ObjectsRepositories/LoginPage.properties"})
public interface LoginPageElements extends Config {
	
	@Key(value = "username.field")
	String username();
	
	@Key(value = "password.field")
	String password();
	
	@Key(value = "login.button")
	String loginButton();

}