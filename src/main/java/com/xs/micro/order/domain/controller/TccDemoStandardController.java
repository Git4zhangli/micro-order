package com.xs.micro.order.domain.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.xs.micro.order.domain.pojo.dto.ResponseEntity;
import com.xs.micro.order.domain.pojo.dto.SuccessResponseEntity;
import com.xs.micro.order.domain.tcc.IAcctIncrease;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/demo")
public class TccDemoStandardController {
	@Autowired
	private IAcctIncrease iAcctIncrease;
	
	@RequestMapping(value = "/tcc/increase", method = RequestMethod.POST)
	@ApiOperation("tcc被调用方接口，增加账户余额")
	@ApiImplicitParams({ 
		@ApiImplicitParam(name = "acctId", value = "账户号", dataType = "String", required = true, paramType = "query"),
		@ApiImplicitParam(name = "increaseAmt", value = "增加的金额", dataType = "Double", required = true, allowableValues = "range[1, infinity]", paramType = "query")
	})
	public ResponseEntity<Boolean> increaseAmt(String acctId, BigDecimal increaseAmt) {

		iAcctIncrease.increase(acctId, increaseAmt);

		return new SuccessResponseEntity<Boolean>(true);
	}
}
