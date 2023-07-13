package com.programmerbeginner.catalog.service.impl;

import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.programmerbeginner.catalog.config.ApplicationProperties;
import com.programmerbeginner.catalog.config.CloudProperties;
import com.programmerbeginner.catalog.service.GreetingService;

@Service
public class GreetingServiceImpl implements GreetingService{
	
	private ApplicationProperties applicationProperties;
	
	private CloudProperties cloudProperties;
	
	

	public GreetingServiceImpl(ApplicationProperties applicationProperties,CloudProperties cloudProperties) {
		super();
		this.applicationProperties = applicationProperties;
		this.cloudProperties = cloudProperties;
	}


	
	@Override
	public String sayGreeting() {
		System.out.println(cloudProperties.getApiKey());
		TimeZone timeZone = TimeZone.getTimeZone(applicationProperties.getTimeZone());
		return applicationProperties.getWelcomeText() + " our timezone : " + timeZone.getDisplayName()+
				", our currency : "+applicationProperties.getCurrency();
	}
	
	
	

}
