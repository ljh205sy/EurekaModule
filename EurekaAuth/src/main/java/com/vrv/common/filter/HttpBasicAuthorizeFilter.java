package com.vrv.common.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.util.StringUtils;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.vrv.common.entity.ResponseCode;
import com.vrv.common.entity.ResponseData;
import com.vrv.utils.JWTUtils;
import com.vrv.utils.JsonUtils;

/**
 * API 调用权限控制
 *
 */
public class HttpBasicAuthorizeFilter implements Filter {
	private Logger logger = LoggerFactory.getLogger(getClass());
	JWTUtils jwtUtils = JWTUtils.getInstance();

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		ServletContext context = filterConfig.getServletContext();  
        ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(context);  
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest)request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;  
        httpResponse.setCharacterEncoding("UTF-8");    
        httpResponse.setContentType("application/json; charset=utf-8"); 
        String auth = httpRequest.getHeader("Authorization");
        logger.info("HttpBasicAuthorizeFilter, 【Authorization】:" + auth);
		//健康检查控制
		String uri = httpRequest.getRequestURI();
		if (uri.equals("/autoconfig") || uri.equals("/configprops") || uri.equals("/beans") || uri.equals("/dump")
				|| uri.equals("/env") || uri.equals("/health") || uri.equals("/info") || uri.equals("/mappings")
				|| uri.equals("/metrics") || uri.equals("/shutdown") || uri.equals("/trace")) {
			if(httpRequest.getQueryString() == null || !httpRequest.getQueryString().equals("token=goojia123456")){
				PrintWriter print = httpResponse.getWriter();
				print.write(JsonUtils.toJson(ResponseData.fail("非法请求【缺少token信息】", ResponseCode.NO_AUTH_CODE.getCode())));
				return;
			}
			chain.doFilter(request, response);
		} else {
			//验证TOKEN
			logger.info("jwt检查是否携带Authorization信息！！");
			if (!StringUtils.hasText(auth)) {
				PrintWriter print = httpResponse.getWriter();
				print.write(JsonUtils.toJson(ResponseData.fail("非法请求【Authorization信息不正确】", ResponseCode.NO_AUTH_CODE.getCode())));
				return;
			}
			JWTUtils.JWTResult jwt = jwtUtils.checkToken(auth);
			logger.info("jwt检查token的有效性！！");
			if (!jwt.isStatus()) {
				PrintWriter print = httpResponse.getWriter();
				print.write(JsonUtils.toJson(ResponseData.fail(jwt.getMsg(), jwt.getCode())));
				return;
			}
			chain.doFilter(httpRequest, response);
		}

	}
	
	@Override
	public void destroy() {
		
	}

}
