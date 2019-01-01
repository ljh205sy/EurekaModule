package com.vrv.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.vrv.common.filter.HttpBasicAuthorizeFilter;

@Configuration
public class WebMVCConfig extends WebMvcConfigurerAdapter {

	@Bean
	public FilterRegistrationBean registrationFilterBean() {
		FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		HttpBasicAuthorizeFilter httpFilter = new HttpBasicAuthorizeFilter();
		registrationBean.setFilter(httpFilter);
		List<String> urlPatterns = new ArrayList<String>();
		urlPatterns.add("/*");
		registrationBean.setUrlPatterns(urlPatterns);
		return registrationBean;
	}

}
