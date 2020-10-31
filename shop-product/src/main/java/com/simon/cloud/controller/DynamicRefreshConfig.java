package com.simon.cloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author simon
 * @date 2020/10/15 10:26 下午
 */
@RestController
@RefreshScope
public class DynamicRefreshConfig {

    @Value("${config.appName}")
    String appName;

    @Value("${config.env}")
    String env;

	@Autowired
	ConfigurableApplicationContext configurableApplicationContext;

	@RequestMapping("text/config/1")
	public String method1() {
		return configurableApplicationContext.getEnvironment().getProperty("config.appName");
	}


	@RequestMapping("text/config/2")
	public String method2(){
	   return appName;
    }

    @RequestMapping("text/config/3")
    public String method3(){
	    return env;
    }
}
