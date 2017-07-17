package com.xs.micro.order.domain.tcc;

import java.math.BigDecimal;

public interface ITccIncrease {
	void increase(String acctId, BigDecimal increaseAmt);
}
