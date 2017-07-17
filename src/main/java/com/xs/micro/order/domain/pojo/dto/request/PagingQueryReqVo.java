package com.xs.micro.order.domain.pojo.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("RequestBody")
public class PagingQueryReqVo {
	
	@ApiModelProperty(value = "查询页码", dataType = "int", required = true)
	private int pageNum;

	@ApiModelProperty(value = "每页大小", dataType = "int", required = true)
	private int pageSize;

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
}
