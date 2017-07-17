package com.xs.micro.order.domain.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xs.micro.order.domain.pojo.dto.ResponseEntity;
import com.xs.micro.order.domain.pojo.dto.SuccessResponseEntity;
import com.xs.micro.order.domain.pojo.dto.request.PagingQueryReqVo;
import com.xs.micro.order.domain.pojo.dto.response.PagingQueryResponse;
import com.xs.micro.order.domain.pojo.model.TbAccountOne;
import com.xs.micro.order.domain.service.ITbAccountOne;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/demo")
public class PagingQueryController {

	@Autowired
	private ITbAccountOne iTbAccountOne;
	
	@RequestMapping(value = "/query/acctId", method = RequestMethod.GET)
	@ApiOperation("查询账户信息—RequestParam")
	@ApiImplicitParam(name = "acctId", value = "账户号", dataType = "String", required = true, paramType = "query")
	public ResponseEntity<PagingQueryResponse> queryByAcctId(@RequestParam String acctId) {
		TbAccountOne entity = iTbAccountOne.queryByAcctId(acctId);
		return new SuccessResponseEntity<PagingQueryResponse>(new PagingQueryResponse(entity.getAcctId(), entity.getAmount(), entity.getFrozen(), entity.getSex().toString()));
	}

	@RequestMapping(value = "/query/all", method = RequestMethod.GET)
	@ApiOperation("查询所有数据—无请求参数")
	public ResponseEntity<List<TbAccountOne>> queryAll() {
		List<TbAccountOne> dataList = iTbAccountOne.queryByAll();
		return new SuccessResponseEntity<List<TbAccountOne>>(dataList);
	}

	@RequestMapping(value = "/query/paging/{pageNum}/{pageSize}", method = RequestMethod.GET)
	@ApiOperation("分页查询数据—PathParam")
	@ApiImplicitParams({ 
		@ApiImplicitParam(name = "pageNum", value = "查询页码", dataType = "int", required = true, paramType = "path"),
		@ApiImplicitParam(name = "pageSize", value = "每页大小", dataType = "int", required = true, paramType = "path")
	})
	public ResponseEntity<PageInfo<TbAccountOne>> pagingQuery(@PathVariable int pageNum, @PathVariable int pageSize) {

		PageHelper.startPage(pageNum, pageSize);
		List<TbAccountOne> dataList = iTbAccountOne.queryByAll();
		PageInfo<TbAccountOne> pageInfo = new PageInfo<TbAccountOne>(dataList);

		return new SuccessResponseEntity<PageInfo<TbAccountOne>>(pageInfo);
	}
	
	@RequestMapping(value = "/query/paging", method = RequestMethod.POST)
	@ApiOperation("分页查询数据—RequestBody")
	public ResponseEntity<PageInfo<TbAccountOne>> pagingQuery(@RequestBody PagingQueryReqVo reqVo) {

		PageHelper.startPage(reqVo);
		List<TbAccountOne> dataList = iTbAccountOne.queryByAll();
		PageInfo<TbAccountOne> pageInfo = new PageInfo<TbAccountOne>(dataList);

		return new SuccessResponseEntity<PageInfo<TbAccountOne>>(pageInfo);
	}
}
