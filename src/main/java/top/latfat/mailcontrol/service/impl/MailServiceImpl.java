package top.latfat.mailcontrol.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import top.latfat.mailcontrol.service.MailService;

@Service
public class MailServiceImpl implements MailService {
	
	private static final Logger logger = LoggerFactory.getLogger(MailServiceImpl.class);
	
	@Override
	@Scheduled(fixedRate = 2*1000)
	public void timerLoadMail() {
		logger.debug("执行定时任务！");
	}
}
