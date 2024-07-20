package com.web.app.framework.utlis.properties;

import org.aeonbits.owner.Config;

@Config.Sources(value = {"file:${user.dir}/src/test/resources/Config.properties"})
public interface ConfigPropertiesHandler extends Config {
	
	@Key(value = "waf.browser.name")
	String getBrowserName();
	
	@Key(value = "waf.headless.browser")
	boolean isBrowserHeadless();
	
	@Key(value = "waf.webdriver.implicit.wait.seconds")
	long getImplicitWaitTime();
	
	@Key(value = "waf.webdriver.explicit.wait.seconds")
	long getExplicitWaitTime();
	
	@Key(value = "waf.test.application.url")
	String getAutUrl();

}