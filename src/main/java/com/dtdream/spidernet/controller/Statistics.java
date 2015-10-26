package com.dtdream.spidernet.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.governance.service.ConsumerService;
import com.alibaba.dubbo.governance.service.ProviderService;
import com.alibaba.dubbo.registry.common.domain.Consumer;
import com.alibaba.dubbo.registry.common.domain.Provider;
import com.dtdream.spidernet.module.StatInfo;

@RequestMapping("/stat")
@Controller
public class Statistics {
    @Autowired
    private ProviderService providerService;
    
    @Autowired
    private ConsumerService consumerService;
	
    private List<String> joinString(List<String> rawStr){
    	List<String> destStr = new ArrayList<String>();
    	
    	for (Iterator<String> it=rawStr.iterator();it.hasNext();){
    		String raw = it.next();
    		
    		if (destStr.indexOf(it.next()) == -1) {
    			destStr.add(raw);
    		}
    	}
    	
    	return destStr;
    }
    
    @RequestMapping("/total")
	@ResponseBody
	public StatInfo getStatInfo() {
    	StatInfo stat = new StatInfo();
    	
    	List<String> services = providerService.findServices();
    	List<Provider> provd = providerService.findAll();
    	List<Consumer> consm = consumerService.findAll();
    	List<String> provdApps = joinString(providerService.findApplications());
    	List<String> consmApps = joinString(consumerService.findApplications());
    	
    	stat.setServiceTotalNum(services.size());
    	stat.setProviderTotalNum(provd.size());
    	stat.setConsumerTotalNum(consm.size());
    	stat.setAppTotalNum(provdApps.size()+consmApps.size());
    	
		return stat;
	}
}
