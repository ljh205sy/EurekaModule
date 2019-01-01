package com.vrv.auth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @author wh1107066 容器启动的时候执行，初始化数据
 */
@Component
public class CommandExecute implements CommandLineRunner {
	private Logger logger = LoggerFactory.getLogger(CommandExecute.class);

	@Autowired
	private TokenScheduledTask tokenScheduledTask;

	@Override
	public void run(String... strings) throws Exception {
		logger.info("CommandExecute run<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
//		String token = tokenScheduledTask.getToken();
//		System.setProperty("provider.auth.token", token);

	}
}
