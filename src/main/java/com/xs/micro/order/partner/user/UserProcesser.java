/**
 * 
 */
package com.xs.micro.order.partner.user;

import java.util.Map;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.xs.micro.order.partner.user.pojo.LoginVo;

/**
 * 用户中心服务调用入口
 * @author zhangli
 * @date 2017年5月27日
 */
@FeignClient(name = "micro-user", fallback = UserProcesserFallBack.class)
public interface UserProcesser {

	@RequestMapping(method = RequestMethod.POST, value = "/user/login")
	Map<String, Object> login(@RequestBody LoginVo loginVo);
}