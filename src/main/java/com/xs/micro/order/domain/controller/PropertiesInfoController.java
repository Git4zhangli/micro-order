/**
 * 
 */
package com.xs.micro.order.domain.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xs.micro.order.config.prop.AdministratorsPropertiesConfig;
import com.xs.micro.order.config.prop.ProjectPropertiesConfig;
import com.xs.micro.order.domain.pojo.dto.ResponseEntity;
import com.xs.micro.order.domain.pojo.dto.SuccessResponseEntity;

import io.swagger.annotations.ApiOperation;

/**
 * 查询配置参数
 * @author zhangli
 * @date 2017年6月24日
 */
@RestController
@RequestMapping(value = "/demo")
public class PropertiesInfoController {
	
	@Autowired
	private ProjectPropertiesConfig projectPropertiesConfig;

	@Autowired
	private AdministratorsPropertiesConfig administratorsPropertiesConfig;
	
	@Value("${encrypt.key}")
	private String encryptKey;
	
	@Value("${person.first.name}")
	private String personFirstName;

	@RequestMapping(value = "/properties/info")
	@ApiOperation("查询配置参数信息")
	public ResponseEntity<Map<String, Object>> propertiesInfo() {
		
		Map<String, Object> retMap = new HashMap<String, Object>();
		retMap.put("projectInfo", projectPropertiesConfig.getFieldValues());
		retMap.put("administratorsInfo", administratorsPropertiesConfig.getFieldValues());
		retMap.put("encryptKey", encryptKey);
		retMap.put("personFirstName", personFirstName);
		
		return new SuccessResponseEntity<Map<String,Object>>(retMap);
	}
}
