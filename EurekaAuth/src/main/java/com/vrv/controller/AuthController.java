package com.vrv.controller;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vrv.EurekaProvider.vo.AuthQuery;
import com.vrv.common.entity.ResponseData;
import com.vrv.utils.JWTUtils;

@RestController
@RequestMapping("/oauth")
public class AuthController {

	@PostMapping("/token")
	public ResponseData getAuthToken(@RequestBody AuthQuery query) {
		if (StringUtils.isBlank(query.getAccessKey()) || StringUtils.isBlank(query.getSecretKey())) {
			return ResponseData.failByParam("accessKey or secretKey is empty!!!");
		}
		// User user = authService.auth(query);

		if ("liujinhui".equals(query.getAccessKey())) {
			return ResponseData.failByParam("认证失败!!");
		}
		JWTUtils jwt = JWTUtils.getInstance();
		return ResponseData.ok(jwt.getToken(query.getAccessKey()));
	}
	
	@GetMapping("/test")
	public ResponseData getTestToken() {
		JWTUtils jwt = JWTUtils.getInstance();
		return ResponseData.ok(jwt.getToken("111"));
	} 
	
}
