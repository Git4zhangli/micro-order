package com.xs.micro.order.domain.tcc.impl;

import java.math.BigDecimal;

import org.bytesoft.compensable.Compensable;
import org.bytesoft.compensable.CompensableCancel;
import org.bytesoft.compensable.CompensableConfirm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.xs.micro.order.domain.pojo.model.TbAccountOne;
import com.xs.micro.order.domain.service.ITbAccountOne;
import com.xs.micro.order.domain.tcc.IAcctIncrease;

@Compensable(interfaceClass = IAcctIncrease.class, simplified = true)
@Service
public class IAcctIncreaseImpl implements IAcctIncrease {

	private Logger LOGGER = LoggerFactory.getLogger(IAcctIncreaseImpl.class);
	
	@Autowired
	private ITbAccountOne iTbAccountOne;

	/**
	 * 第一步：增加余额，资金锁定到冻结金额
	 */
	@Override
	@Transactional
	public void increase(String acctId, BigDecimal increaseAmt) {
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
	public void confirm(String acctId, BigDecimal increaseAmt) {
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
	public void cancel(String acctId, BigDecimal increaseAmt) {
		// 账户查询
		TbAccountOne account = iTbAccountOne.queryByAcctId(acctId);

		BigDecimal postFrozen = account.getFrozen().subtract(increaseAmt);// 冻结金额减少
		iTbAccountOne.fundChange(acctId, account.getAmount(), postFrozen, account.getVersion());

		LOGGER.info("账户{}取消增加余额，冻结金额减少{}", acctId, increaseAmt, increaseAmt);
	}
}
