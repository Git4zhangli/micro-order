package com.xs.micro.order.domain.tcc;

import java.math.BigDecimal;

/**
 * 资金增加
 * @author zhangli 2017年6月28日
 */
public interface IAcctIncrease {
	void increase(String acctId, BigDecimal increaseAmt);
}
