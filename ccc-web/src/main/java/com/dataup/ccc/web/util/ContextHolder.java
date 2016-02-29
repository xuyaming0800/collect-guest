package com.dataup.ccc.web.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;
@Component 
public class ContextHolder implements ApplicationContextAware {
	private static  ConfigurableApplicationContext applicationContext;
	
	public static ConfigurableApplicationContext getApplicationContext(){
		return applicationContext;
	}
	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		ContextHolder.applicationContext=(ConfigurableApplicationContext)applicationContext;
	}

}
