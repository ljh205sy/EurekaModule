package com.vrv.auth;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.vrv.EurekaProvider.vo.AuthQuery;
import com.vrv.common.entity.ResponseData;

/**
 * 定时刷新token
 *
 **/
@Component
public class TokenScheduledTask {
	private static Logger logger = LoggerFactory.getLogger(TokenScheduledTask.class);

	public final static long ONE_Minute = 60 * 1000 * 60;
	public final static String CRON_TIME = "0 0/2 * * * ?";

	@Autowired
	private AuthRemoteClient authRemoteClient;

	/**
	 * 刷新Token
	 */
	// @Scheduled(fixedDelay = ONE_Minute)
	@Scheduled(cron = "0 0/2 * * * ?")
	public void reloadApiToken() {
		String token = this.getToken();
		while (StringUtils.isBlank(token)) {
			try {
				Thread.sleep(1000);
				token = this.getToken();
			} catch (InterruptedException e) {
				logger.error("", e);
			}
		}
		Thread current = Thread.currentThread();
		logger.info("定时任务1:" + current.getId());
		logger.info("ScheduledTest.executeFileDownLoadTask 定时任务1:" + current.getId() + ",name:" + current.getName());
		System.setProperty("provider.auth.token", token);
		logger.info("定时任务刷新token:" + token);
	}

	public String getToken() {
		AuthQuery query = new AuthQuery();
		query.setAccessKey("accessKey");
		query.setSecretKey("123");
		ResponseData response = authRemoteClient.auth(query);
		String data = response.getData() == null ? "" : response.getData().toString();
		logger.info(data);
		return data;
	}
}
