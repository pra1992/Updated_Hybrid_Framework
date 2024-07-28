package com.web.app.framework.utlis.properties.objects;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.Key;


@Config.Sources(value = {"file:${user.dir}/src/test/resources/ObjectsRepositories/DashboardPage.properties"})
public interface DashboardPageElements extends Config {


	@Key(value = "options.tab")
	String options();
}
