/**
 * 
 */
package com.xs.micro.order.partner.notice;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 通知中心服务调用入口
 * @author zhangli
 * @date 2017年6月27日
 */
@FeignClient(name = "micro-notice", fallback = NoticeProcesserFallBack.class)
public interface NoticeProcesser {
	
	@RequestMapping(value = "/test", method = RequestMethod.POST)
	boolean noticeTest(@RequestParam("mobile") String mobile, @RequestParam("content") String content);
}
