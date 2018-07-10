package com.example.config.mongo;

public class MongoProperties {

	/**
	 * 数据源链接url
	 */
	private String url;
	/**
	 * 生成mongoTemplate bean 的名称
	 */
	private String templateName;
	
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getTemplateName() {
		return templateName;
	}
	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}
}
