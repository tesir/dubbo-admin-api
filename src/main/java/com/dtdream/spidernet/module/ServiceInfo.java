package com.dtdream.spidernet.module;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.dubbo.registry.common.domain.Consumer;
import com.alibaba.dubbo.registry.common.domain.Provider;

public class ServiceInfo {
	private String ServiceName;
	private List<String>Methods;
	
	private List<Provider> Providers = new ArrayList<Provider>();
	private List<Consumer> Consumers = new ArrayList<Consumer>();
	public String getServiceName() {
		return ServiceName;
	}
	public void setServiceName(String serviceName) {
		ServiceName = serviceName;
	}

	public List<String> getMethods() {
		return Methods;
	}
	public void setMethods(List<String> methods) {
		Methods = methods;
	}
	public List<Provider> getProviders() {
		return Providers;
	}
	public void setProviders(List<Provider> providers) {
		Providers = providers;
	}
	public void AddProviders(Provider prov) {
		Providers.add(prov);
	}
	
	public List<Consumer> getConsumers() {
		return Consumers;
	}
	public void setConsumers(List<Consumer> consumers) {
		Consumers = consumers;
	}
	
	public void AddConsumers(Consumer cons) {
		Consumers.add(cons);
	}
}
