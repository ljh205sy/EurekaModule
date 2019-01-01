package com.vrv.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.vrv.service.UserService;
import com.vrv.vo.User;

@RestController
public class ConsumerController {
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private UserService userService;

	@RequestMapping(value = "/auth-test", method = RequestMethod.GET)
	public String helloConsumer(HttpServletRequest request) {
		String header = request.getHeader("Authorization");
		logger.info("实际业务方法controller:" + header);
		return userService.hello();
	}

	@RequestMapping(value = "/feign-consumer", method = RequestMethod.GET)
	public String helloConsumer2() {
		StringBuilder sb = new StringBuilder();
		sb.append(userService.hello()).append("\r\n<br>");
		sb.append("----------------------------------------\r\n<br>");
		sb.append(userService.hello("liujh")).append("\r\n<br>");
		sb.append("***************************************\r\n<br>");
		sb.append(userService.hello("liujh", 111)).append("\r\n<br>");
		sb.append("=======================\r\n<br>");
		sb.append(userService.hello(new User("liujh-name", 12))).append("\n<br>");
		return sb.toString();
	}

	@RequestMapping(value = "/auth", method = RequestMethod.GET)
	public String auth() {
		StringBuilder sb = new StringBuilder();
		sb.append(userService.hello()).append("\r\n<br>");
		sb.append("=======================\r\n<br>");
		return sb.toString();
	}

}
