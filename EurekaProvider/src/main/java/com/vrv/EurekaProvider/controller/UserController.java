package com.vrv.EurekaProvider.controller;

import java.util.Iterator;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vrv.EurekaProvider.vo.User;

@RestController
public class UserController {
	private Logger logger = Logger.getLogger(getClass());
	@Autowired
	private DiscoveryClient discoveryClient;

	@SuppressWarnings("deprecation")
	@RequestMapping(value = "/hello", method = RequestMethod.GET)
	public String index() throws Exception {
		ServiceInstance instance = discoveryClient.getLocalServiceInstance();
		// int sleepTime = new Random().nextInt(3000);
		// logger.info("sleepTime:" + sleepTime);
		// Thread.sleep(sleepTime);
		logger.info("结束调用方法：/hello, host:" + instance.getHost() + ", service_id:" + instance.getServiceId());
		// 模拟fallback hystrix
//		throw new RuntimeException("111");
		return "hello bendi11111111111111111111";
	}

	@RequestMapping(value = "/hello1", method = RequestMethod.GET)
	public String hello(@RequestParam("name") String name) {
		logger.info("-------------hello1-------------");
		ServiceInstance instance = discoveryClient.getLocalServiceInstance();
		logger.info("/hello1, name:" + name + "host:" + instance.getHost() + ", service_id:" + instance.getServiceId());
		return "hello1, " + name;
	}

	@RequestMapping(value = "/hello2", method = RequestMethod.GET)
	public User hello(@RequestHeader("name") String name, @RequestHeader("age") Integer age) {
		ServiceInstance instance = discoveryClient.getLocalServiceInstance();
		logger.info("/hello2, name:" + name + ", age " + age + ", host:" + instance.getHost() + ", service_id:" + instance.getServiceId());
		return new User(name, age);
	}

	@RequestMapping(value = "/hello3", method = RequestMethod.POST)
	public String hello(@RequestBody(required = false) User user) {
		ServiceInstance instance = discoveryClient.getLocalServiceInstance();
		return "/hello3, tostirg()" + user.toString() + ", " + user.getName() + ", " + user.getAge();
	}

	// ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	@SuppressWarnings("deprecation")
	@RequestMapping(value = "/getName/{id}", method = RequestMethod.GET)
	public String getName(@PathVariable String id) {
		ServiceInstance instance = discoveryClient.getLocalServiceInstance();
		logger.info("hostName：" + instance.getHost() + "， service_id:" + instance.getServiceId());
		return "param id value: " + id + ", name: ljh, age:30";
	}

	@SuppressWarnings("deprecation")
	@RequestMapping(value = "/getParamName", method = RequestMethod.GET)
	public String getParamName(@RequestParam("name") String name) {
		ServiceInstance instance = discoveryClient.getLocalServiceInstance();
		logger.info("parameter：" + name + "， service_id:" + instance.getServiceId());
		return "get请求param : " + name;
	}

	@SuppressWarnings("deprecation")
	@RequestMapping(value = "/postName", method = RequestMethod.POST)
	public String postName(@RequestBody String parameter) {
		ServiceInstance instance = discoveryClient.getLocalServiceInstance();
		logger.info("post请求参数：" + parameter + ", " + "， service_id:" + instance.getServiceId());
		return "post id:" + parameter;
	}

	@SuppressWarnings("deprecation")
	@RequestMapping(value = "/postMapName", method = RequestMethod.POST)
	public String postMapName(@RequestBody Map<String, String> map) {
		ServiceInstance instance = discoveryClient.getLocalServiceInstance();
		Iterator<String> it = map.keySet().iterator();
		for (; it.hasNext();) {
			String key = (String) it.next();
			Object value = map.get(key);
			logger.info("key:" + key + ", " + "， value:" + (String) value);
		}
		logger.info("post请求参数：" + map + ", " + "， service_id:" + instance.getServiceId());
		return "post id:" + map;
	}

	@SuppressWarnings("deprecation")
	@RequestMapping(value = "/deleteName/{id}", method = RequestMethod.DELETE)
	public void deleteName(@PathVariable String id) {
		ServiceInstance instance = discoveryClient.getLocalServiceInstance();
		logger.info("删除参数为：" + id + "， service_id:" + instance.getServiceId());
	}

}
