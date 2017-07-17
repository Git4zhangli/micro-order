package com.xs.micro.order.config.bean;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
@AutoConfigureAfter(SqlSessionFactory.class)
public class JavaBeanConfig {

	/**
	 * 配置Mybatis动态代理DAO接口
	 */
	@Bean
	public MapperScannerConfigurer mapperScannerConfigurer() {
		MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
		mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
		mapperScannerConfigurer.setBasePackage("com.xs.micro.order.domain.dao");
		return mapperScannerConfigurer;
	}
	
	/**
	 * swagger2 配置
	 * 
	 * @author YuanZhiQiang
	 * @return
	 */
	@Bean
	@Profile({ "local", "dev", "test" })
	public Docket createRestApi() {
		// 全局头部参数配置
		ParameterBuilder aParameterBuilder = new ParameterBuilder();
		aParameterBuilder.name("header_param").description("全局头部参数").modelRef(new ModelRef("string")).parameterType("header").required(false).build();

		// 全局公共参数配置
		ParameterBuilder aParameterBuilder1 = new ParameterBuilder();
		aParameterBuilder1.name("common_param").description("全局公共参数").modelRef(new ModelRef("string")).parameterType("query").required(false).build();

		List<Parameter> aParameters = new ArrayList<Parameter>();
		aParameters.add(aParameterBuilder.build());
		aParameters.add(aParameterBuilder1.build());

		return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select().apis(RequestHandlerSelectors.basePackage("com.xs.micro.order.domain.controller"))// 本地、开发、测试环境扫描真正的Controller包
				.paths(PathSelectors.any()).build().globalOperationParameters(aParameters);
	}

	@Bean
	@Profile("online")
	public Docket createRestApiOnline() {
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select().apis(RequestHandlerSelectors.basePackage("com.xs.micro.order.domain.controller.disable"))// 生产环境不公开API，所以扫描一个不存在的
				.paths(PathSelectors.any()).build();
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("订单服务 API").description("这只是个Demo项目。").termsOfServiceUrl("http://git.xs.jf/xiangshang-micro/micro-order.git")
				.build();
	}
}