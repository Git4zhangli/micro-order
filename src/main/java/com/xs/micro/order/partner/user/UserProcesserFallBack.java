package com.xs.micro.order.partner.user;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.xs.micro.order.partner.user.pojo.LoginVo;

@Component
public class UserProcesserFallBack implements UserProcesser {
	private static final Logger LOGGER = LoggerFactory.getLogger(UserProcesserFallBack.class);

	@Override
	public Map<String, Object> login(LoginVo loginVo) {
		LOGGER.error("通知中心调用失败");
		
		Map<String, Object> retMap = new HashMap<String, Object>();
		retMap.put("error", "接口调用错误，请稍后重试！");
		return retMap;
	}
}
