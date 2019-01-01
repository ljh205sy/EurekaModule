package com.vrv.service;

import org.springframework.stereotype.Component;

import com.vrv.vo.User;

@Component
public class FeignClientFallbackUserService implements UserService {

	@Override
	public String hello() {
		return "fallback...";
	}

	@Override
	public String hello(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User hello(String name, Integer age) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String hello(User user) {
		// TODO Auto-generated method stub
		return null;
	}

}
