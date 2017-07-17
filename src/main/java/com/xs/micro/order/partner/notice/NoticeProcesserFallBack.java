package com.xs.micro.order.partner.notice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class NoticeProcesserFallBack implements NoticeProcesser {
	private static final Logger LOGGER = LoggerFactory.getLogger(NoticeProcesserFallBack.class);
	
	@Override
	public boolean noticeTest(String mobile, String content) {
		LOGGER.error("通知中心调用失败");
		return false;
	}
}
