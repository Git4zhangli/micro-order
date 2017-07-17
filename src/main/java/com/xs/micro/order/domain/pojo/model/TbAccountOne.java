/**
 * 
 */
package com.xs.micro.order.domain.pojo.model;

import java.math.BigDecimal;

import com.xs.micro.order.domain.em.Sex;

/**
 * 账户
 * @author zhangli
 * @date 2017年6月24日
 */
public class TbAccountOne {

	private int id;

	private String acctId;

	private BigDecimal amount;

	private BigDecimal frozen;

	private Sex sex;
	
	private int version;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAcctId() {
		return acctId;
	}

	public void setAcctId(String acctId) {
		this.acctId = acctId;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public BigDecimal getFrozen() {
		return frozen;
	}

	public void setFrozen(BigDecimal frozen) {
		this.frozen = frozen;
	}

	public Sex getSex() {
		return sex;
	}

	public void setSex(Sex sex) {
		this.sex = sex;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}
}
