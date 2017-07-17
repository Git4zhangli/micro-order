package com.xs.micro.order.domain.pojo.dto.response;

import java.math.BigDecimal;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("数据模型")
public class PagingQueryResponse {
	
	@ApiModelProperty(value = "账户名", dataType = "String", required = true)
	private String acctId;

	@ApiModelProperty(value = "余额", dataType = "BigDecimal", required = true)
	private BigDecimal amount;

	@ApiModelProperty(value = "冻结金额", dataType = "BigDecimal", required = true)
	private BigDecimal frozen;

	@ApiModelProperty(value = "性别", dataType = "String", required = true)
	private String sex;
	
	public PagingQueryResponse() {
		super();
	}

	public PagingQueryResponse(String acctId, BigDecimal amount, BigDecimal frozen, String sex) {
		super();
		this.acctId = acctId;
		this.amount = amount;
		this.frozen = frozen;
		this.sex = sex;
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

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}
}
