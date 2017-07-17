package com.xs.micro.order.domain.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xs.micro.order.domain.pojo.dto.ResponseEntity;
import com.xs.micro.order.domain.pojo.dto.SuccessResponseEntity;
import com.xs.micro.order.partner.notice.NoticeProcesser;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/demo")
public class FeignDemoController {
	
	@Autowired
	private NoticeProcesser noticeProcesser;
	
	@RequestMapping(value = "/feign", method = RequestMethod.GET)
	@ApiOperation("feign+断路器测试(通知中心)")
	@ApiImplicitParams({ 
		@ApiImplicitParam(name = "mobile", value = "手机号", dataType = "String", required = true, paramType = "query"),
		@ApiImplicitParam(name = "content", value = "通知内容", dataType = "String", required = true, paramType = "query")
	})
	public ResponseEntity<Boolean> testFeign(@RequestParam String mobile, @RequestParam String content) {
		boolean result = noticeProcesser.noticeTest(mobile, content);
		return new SuccessResponseEntity<Boolean>(result);
	}
}
