package com.xs.micro.order.domain.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xs.micro.order.domain.business.AccountOptBus;
import com.xs.micro.order.domain.em.Sex;
import com.xs.micro.order.domain.pojo.dto.ResponseEntity;
import com.xs.micro.order.domain.pojo.dto.SuccessResponseEntity;
import com.xs.micro.order.domain.pojo.model.TbAccountOne;
import com.xs.micro.order.extension.util.EnumUtil;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/demo")
public class TransactionalDemoController {

	@Autowired
	private AccountOptBus accountOptBus;
	
	@RequestMapping(value = "/commonly", method = RequestMethod.PUT)
	@ApiOperation("普通的事务Demo—注册一个账户")
	@ApiImplicitParams({ 
		@ApiImplicitParam(name = "acctId", value = "注册的账户号", dataType = "String", required = true, paramType = "query"),
		@ApiImplicitParam(name = "sex", value = "性别(1：男，2：女)", dataType = "int", required = true, allowableValues = "1,2", paramType = "query")
	})
	public ResponseEntity<TbAccountOne> testFeign(@RequestParam String acctId, @RequestParam int sex) {
		Sex acctSex = EnumUtil.getEnumByValue(sex, Sex.class);
		Assert.notNull(acctSex, "性别参数错误");
		
		TbAccountOne result = accountOptBus.registAcctount(acctId, acctSex);
		return new SuccessResponseEntity<TbAccountOne>(result);
	}
}
