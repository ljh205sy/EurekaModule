package com.vrv.auth;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.vrv.EurekaProvider.vo.AuthQuery;
import com.vrv.common.entity.ResponseData;

@FeignClient(value = "server-auth", path = "/oauth", configuration = FeignConfiguration.class, fallback = AuthRemoteClientHystrix.class)
public interface AuthRemoteClient {

	/**
	 * 调用认证,获取token
	 */
	@PostMapping("/token")
	ResponseData auth(@RequestBody AuthQuery query);

}
