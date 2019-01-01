package com.vrv.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.vrv.auth.FeignConfiguration;
import com.vrv.vo.User;

/**
 * User-Service 是服务的提供者在 服务注册中心注册的服务名称
 * 
 * @author wh1107066
 */
@FeignClient(value = "user-provider", configuration = FeignConfiguration.class, fallback = FeignClientFallbackUserService.class)
public interface UserService {

	// 相当于controller中的mapping映射
	@RequestMapping(value = "/hello", method = RequestMethod.GET)
	public String hello();

	@RequestMapping(value = "/hello1", method = RequestMethod.GET)
	public String hello(@RequestParam("name") String name);

	@RequestMapping(value = "/hello2", method = RequestMethod.GET)
	public User hello(@RequestHeader("name") String name, @RequestHeader("age") Integer age);

	@RequestMapping(value = "/hello3", method = RequestMethod.POST)
	public String hello(@RequestBody User user);

}
