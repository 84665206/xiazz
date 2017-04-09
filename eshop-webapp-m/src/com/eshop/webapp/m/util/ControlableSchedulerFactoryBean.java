package com.eshop.webapp.m.util;

import org.springframework.scheduling.quartz.SchedulerFactoryBean;

/****
 * 
 * @author ZhichengHan
 *
 */
public class ControlableSchedulerFactoryBean extends SchedulerFactoryBean {
	
	private boolean allowedRun = false;

	public boolean isAllowedRun() {
		return allowedRun;
	}

	public void setAllowedRun(boolean allowedRun) {
		this.allowedRun = allowedRun;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		if(this.allowedRun){
			super.afterPropertiesSet();
		}
	}
	
	
	
}
