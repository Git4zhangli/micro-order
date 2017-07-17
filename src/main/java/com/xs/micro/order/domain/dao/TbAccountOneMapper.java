package com.xs.micro.order.domain.dao;

import java.math.BigDecimal;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xs.micro.order.domain.pojo.model.TbAccountOne;

public interface TbAccountOneMapper {
	TbAccountOne queryByAcctId(String acctId);

	List<TbAccountOne> queryByAll();

	int insert(TbAccountOne model);

	int fundChange(@Param("acctId") String acctId, @Param("amount") BigDecimal amount, @Param("frozen") BigDecimal frozen, @Param("version") int version);
}
