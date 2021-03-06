package com.vrv.auth;

import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.Logger;
import feign.Request;


@Configuration
public class FeignConfiguration {
	
	private org.slf4j.Logger logger = LoggerFactory.getLogger(getClass());
    /**
     * 日志级别
     */
	@Bean  
    Logger.Level feignLoggerLevel() {  
        return Logger.Level.FULL;  
    }

    /*@Bean
    public Contract feignContract() {
        return new feign.Contract.Default();
    }*/

   /* @Bean
    public BasicAuthRequestInterceptor myBasicAuthRequestInterceptor(){
        return new BasicAuthRequestInterceptor("user","password");
    }*/
    /**
     * 创建Feign请求拦截器，在发送请求前设置认证的token,各个微服务将token设置到环境变量中来达到通用
     * @return
     */
    @Bean
    public FeignBasicAuthRequestInterceptor basicAuthRequestInterceptor() {
    	logger.info(">>>>>>>>>>>>>>>>>>>>携带token");
        return new FeignBasicAuthRequestInterceptor();
    }
    
    @Bean
    public Request.Options options() {
        return new Request.Options(5000, 10000);
    }

    /*@Bean
    public Decoder decoder() {
     return new MyDecoder();
    }

    @Bean
    public Encoder encoder() {
        return new MyEncoder();
    }*/

//    @Bean
//    @Scope("prototype")
//    public Feign.Builder feignBuilder() {
//        return Feign.builder();
//    }
}
