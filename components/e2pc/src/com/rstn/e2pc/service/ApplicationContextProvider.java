package com.rstn.e2pc.service;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;


public class ApplicationContextProvider implements ApplicationContextAware {

	private static ApplicationContext applicationContext;
	
	static ApplicationContext getAppContext() {
		return applicationContext;
	}
	
	@Override
	public void setApplicationContext(ApplicationContext context)
			throws BeansException {
		applicationContext = context;
	}
}
