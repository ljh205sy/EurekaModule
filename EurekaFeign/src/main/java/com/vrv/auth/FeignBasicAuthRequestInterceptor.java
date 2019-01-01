package com.vrv.auth;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vrv.support.RibbonFilterContextHolder;

import feign.RequestInterceptor;

/**
 * Feign请求拦截器
 * 
 **/
public class FeignBasicAuthRequestInterceptor implements RequestInterceptor {

	private Logger logger = LoggerFactory.getLogger(getClass());

	public FeignBasicAuthRequestInterceptor() {

	}

	@Override
	public void apply(feign.RequestTemplate template) {
		String token = System.getProperty("provider.auth.token");
		template.header("Authorization", token);
		logger.info(String.format("feign invoke token: %s" , token));
		Map<String, String> attributes = RibbonFilterContextHolder.getCurrentContext().getAttributes();
		for (String key : attributes.keySet()) {
			String value = attributes.get(key);
			System.out.println("feign :" + key + "\t" + value);
			logger.info("feign :" + key + "\t" + value);
			template.header(key, value);
		}
	}

}
