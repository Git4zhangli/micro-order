package com.xs.micro.order.domain.controller;

import java.math.BigDecimal;

import org.bytesoft.compensable.Compensable;
import org.bytesoft.compensable.CompensableCancel;
import org.bytesoft.compensable.CompensableConfirm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.xs.micro.order.domain.pojo.model.TbAccountOne;
import com.xs.micro.order.domain.service.ITbAccountOne;
import com.xs.micro.order.domain.tcc.ITccIncrease;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;

@Compensable(interfaceClass = ITccIncrease.class, simplified = true)
@RestController
@RequestMapping(value = "/tcc/simplified")
public class TccDemoController implements ITccIncrease {
	
	private Logger LOGGER = LoggerFactory.getLogger(TccDemoController.class);
	
	@Autowired
	private ITbAccountOne iTbAccountOne;

	@ResponseBody
	@RequestMapping(value = "/increase", method = RequestMethod.POST)
	@ApiImplicitParams({ 
		@ApiImplicitParam(name = "acctId", value = "账户号", dataType = "String", required = true, paramType = "query"),
		@ApiImplicitParam(name = "increaseAmt", value = "增加的金额", dataType = "Double", required = true, allowableValues = "range[1, infinity]", paramType = "query")
	})
	@Transactional
	@Override
	public void increase(@RequestParam String acctId, @RequestParam BigDecimal increaseAmt) {
		// 参数判断
		Assert.noNullElements(new Object[] { acctId, increaseAmt }, "参数错误");
		Assert.isTrue(increaseAmt.compareTo(BigDecimal.ZERO) > 0, "参数错误");

		// 账户查询
		TbAccountOne account = iTbAccountOne.queryByAcctId(acctId);
		Assert.notNull(account, "参数错误");

		// 变更后的冻结金额
		BigDecimal postFrozen = account.getFrozen().add(increaseAmt);
		iTbAccountOne.fundChange(acctId, account.getAmount(), postFrozen, account.getVersion());

		LOGGER.info("账户{}增加余额，冻结金额增加{}", acctId, increaseAmt);
	}
	
	/**
	 * 第二步：确认增加余额，冻结金额到账户
	 */
	@CompensableConfirm
	@Transactional
	public void confirmIncrease(String acctId, BigDecimal increaseAmt) {
		// 账户查询
		TbAccountOne account = iTbAccountOne.queryByAcctId(acctId);
		
		BigDecimal postAmount = account.getAmount().add(increaseAmt);// 余额增加
		BigDecimal postFrozen = account.getFrozen().subtract(increaseAmt);// 冻结金额减少
		iTbAccountOne.fundChange(acctId, postAmount, postFrozen, account.getVersion());
		
		LOGGER.info("账户{}确认增加余额，余额增加{}，冻结金额减少{}", acctId, increaseAmt, increaseAmt);
	}

	/**
	 * 第二步：取消增加余额，冻结金额回滚
	 */
	@CompensableCancel
	@Transactional
	public void cancelIncrease(String acctId, BigDecimal increaseAmt) {
		// 账户查询
		TbAccountOne account = iTbAccountOne.queryByAcctId(acctId);

		BigDecimal postFrozen = account.getFrozen().subtract(increaseAmt);// 冻结金额减少
		iTbAccountOne.fundChange(acctId, account.getAmount(), postFrozen, account.getVersion());

		LOGGER.info("账户{}取消增加余额，冻结金额减少{}", acctId, increaseAmt, increaseAmt);
	}
}
