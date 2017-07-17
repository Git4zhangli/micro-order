package com.xs.micro.order.config.prop;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

import com.xs.micro.order.config.prop.sub.AdministratorSubConfig;

@Component
@RefreshScope
@ConfigurationProperties(prefix = "app.conf.administrators")
public class AdministratorsPropertiesConfig {

	/**配置说明*/
	private String info;
	
	/**子账户对象*/
	private AdministratorSubConfig administrator;

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public AdministratorSubConfig getAdministrator() {
		return administrator;
	}

	public void setAdministrator(AdministratorSubConfig administrator) {
		this.administrator = administrator;
	}
	
	public Map<String, Object> getFieldValues() {
		Map<String, Object> fields = new HashMap<>();
		fields.put("info", info);
		fields.put("administrator", administrator);
		return fields;
	}
}
