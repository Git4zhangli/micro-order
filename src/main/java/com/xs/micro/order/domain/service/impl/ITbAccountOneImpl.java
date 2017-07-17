package com.xs.micro.order.domain.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.xs.micro.order.domain.dao.TbAccountOneMapper;
import com.xs.micro.order.domain.em.Sex;
import com.xs.micro.order.domain.pojo.model.TbAccountOne;
import com.xs.micro.order.domain.service.ITbAccountOne;

@Service
public class ITbAccountOneImpl implements ITbAccountOne {
	
	@Autowired
	private TbAccountOneMapper tbAccountOneMapper;

	@Override
	public TbAccountOne queryByAcctId(String acctId) {
		return tbAccountOneMapper.queryByAcctId(acctId);
	}

	@Override
	public List<TbAccountOne> queryByAll() {
		return tbAccountOneMapper.queryByAll();
	}

	@Override
	public TbAccountOne insert(String acctId, Sex sex) {
		TbAccountOne model = new TbAccountOne();
		model.setAcctId(acctId);
		model.setAmount(BigDecimal.ZERO);
		model.setFrozen(BigDecimal.ZERO);
		model.setSex(sex);
		tbAccountOneMapper.insert(model);
		return model;
	}

	@Override
	public void fundChange(String acctId, BigDecimal amount, BigDecimal frozen, int version) {
		Assert.noNullElements(new Object[] { acctId, amount, frozen }, "参数错误");
		Assert.isTrue(amount.compareTo(BigDecimal.ZERO) >= 0, "账户余额不足");
		Assert.isTrue(frozen.compareTo(BigDecimal.ZERO) >= 0, "账户冻结金额不足");
		Assert.isTrue(version >= 0 , "参数错误");
		
		int result = tbAccountOneMapper.fundChange(acctId, amount, frozen, version);
		Assert.isTrue(result > 0, "账户资金变更失败");
	}
}
