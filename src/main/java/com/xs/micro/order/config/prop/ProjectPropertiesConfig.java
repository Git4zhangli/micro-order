package com.xs.micro.order.config.prop;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

@Component
@RefreshScope
@ConfigurationProperties(prefix = "app.conf.project")
public class ProjectPropertiesConfig {
	/**项目标题*/
	private String title;
	
	/**项目描述*/
	private String description;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public Map<String, Object> getFieldValues() {
		Map<String, Object> fields = new HashMap<>();
		fields.put("title", title);
		fields.put("description", description);
		return fields;
	}
}
