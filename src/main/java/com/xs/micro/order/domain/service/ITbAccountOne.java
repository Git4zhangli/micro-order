package com.xs.micro.order.domain.service;

import java.math.BigDecimal;
import java.util.List;

import com.xs.micro.order.domain.em.Sex;
import com.xs.micro.order.domain.pojo.model.TbAccountOne;

public interface ITbAccountOne {
	TbAccountOne queryByAcctId(String acctId);
	
	List<TbAccountOne> queryByAll();
	
	TbAccountOne insert(String acctId, Sex sex);
	
	void fundChange(String acctId, BigDecimal amount, BigDecimal frozen, int version);
}
