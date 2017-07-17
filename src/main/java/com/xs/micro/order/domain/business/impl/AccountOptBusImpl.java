package com.xs.micro.order.domain.business.impl;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.xs.micro.order.domain.business.AccountOptBus;
import com.xs.micro.order.domain.dao.RegistLogMapper;
import com.xs.micro.order.domain.em.Sex;
import com.xs.micro.order.domain.pojo.model.RegistLog;
import com.xs.micro.order.domain.pojo.model.TbAccountOne;
import com.xs.micro.order.domain.service.ITbAccountOne;

@Service
public class AccountOptBusImpl implements AccountOptBus {
	
	private Logger LOGGER = LoggerFactory.getLogger(AccountOptBusImpl.class);
	
	@Autowired
	private ITbAccountOne iTbAccountOne;
	
	@Autowired
	private RegistLogMapper registLogMapper;

	@Override
	@Transactional(rollbackFor = RuntimeException.class)
	public TbAccountOne registAcctount(String acctId, Sex sex) {
		// 查询是否已存在注册账户
		TbAccountOne entity = iTbAccountOne.queryByAcctId(acctId);
		Assert.isNull(entity, "账户已存在");
		
		// 添加注册账户
		TbAccountOne acct = iTbAccountOne.insert(acctId, sex);
		
		// 添加注册日志
		RegistLog model = new RegistLog();
		model.setAcctId(acctId);
		model.setRegistTime(new Date());
		registLogMapper.insert(model);
		
		/*if (model.getId() > 0) {
			throw new RuntimeException("测试是否回滚");
		}*/
		
		LOGGER.info("注册账户{}成功，返回账户ID{}，返回注册日志ID{}", acctId, acct.getId(), model.getId());
		
		return iTbAccountOne.queryByAcctId(acctId);
	}

}
