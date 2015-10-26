package com.dtdream.spidernet.module;

public class ServiceSummary {
	private String ServiceName;
	private String AppName;
	private int ProvidersNum;
	private int ConsumersNum;
	
	public String getServiceName() {
		return ServiceName;
	}
	public void setServiceName(String serviceName) {
		ServiceName = serviceName;
	}
	public String getAppName() {
		return AppName;
	}
	public void setAppName(String appName) {
		AppName = appName;
	}
	public int getProvidersNum() {
		return ProvidersNum;
	}
	public void setProvidersNum(int providersNum) {
		ProvidersNum = providersNum;
	}
	public int getConsumersNum() {
		return ConsumersNum;
	}
	public void setConsumersNum(int consumersNum) {
		ConsumersNum = consumersNum;
	}
}
