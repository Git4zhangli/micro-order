package com.xs.micro.order;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import com.alibaba.fastjson.JSON;
import com.xs.micro.order.domain.pojo.model.TbAccountOne;
import com.xs.micro.order.domain.service.ITbAccountOne;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class MicroOrderApplicationTests {
	
	@Autowired
	private ITbAccountOne iTbAccountOne;

	@Test
	public void contextLoads() {
		List<TbAccountOne> dataList = iTbAccountOne.queryByAll();
		System.out.println("==================================");
		System.out.println(JSON.toJSONString(dataList));
		System.out.println("==================================");
	}

}
