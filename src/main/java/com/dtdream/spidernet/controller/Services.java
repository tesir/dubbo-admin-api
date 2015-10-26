package com.dtdream.spidernet.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.common.logger.Logger;
import com.alibaba.dubbo.common.logger.LoggerFactory;
import com.alibaba.dubbo.governance.service.ConsumerService;
import com.alibaba.dubbo.governance.service.ProviderService;
import com.alibaba.dubbo.registry.common.domain.Consumer;
import com.alibaba.dubbo.registry.common.domain.Provider;
import com.dtdream.spidernet.exception.ErrorInfo;
import com.dtdream.spidernet.exception.SpiderException;
import com.dtdream.spidernet.module.ServiceInfo;
import com.dtdream.spidernet.module.ServiceSummary;


@Controller
@RequestMapping("/services")
public class Services{
    @Autowired
    private ProviderService providerService;
    
    @Autowired
    private ConsumerService consumerService;
    private static final Logger logger = LoggerFactory.getLogger(Services.class);
    
    @ExceptionHandler
    @ResponseBody
    public ErrorInfo handleException(SpiderException t) throws SpiderException {
    	ErrorInfo errInfo = new ErrorInfo();
    	
    	errInfo.setCode(t.getStatus());
    	errInfo.setErrMsg(t.getMessage());
        return errInfo;
    }
    
	@RequestMapping("/{service:.+}")
	@ResponseBody
	public ServiceInfo getServiceInfo(@PathVariable ("service") String service) throws Exception{
		ServiceInfo servinfo = new ServiceInfo();
		
		/* 获取服务提供者列表 */
		List<Provider> provider = providerService.findByService(service);
		
		if (!provider.isEmpty()) {
			List<String> method = providerService.findMethodsByService(service);
			
			servinfo.setServiceName(service);
			servinfo.setMethods(method);
			
			for (Iterator<Provider> it=provider.iterator();it.hasNext();){
				servinfo.AddProviders(it.next());
			}
			
			/* 获取消费者列表 */
			List<Consumer> consumer = consumerService.findByService(service);
			
			for (Iterator<Consumer> it=consumer.iterator();it.hasNext();){
				servinfo.AddConsumers(it.next());
			}
			
			return servinfo;
		}else{
			throw new SpiderException("Services not exist!", HttpStatus.NOT_FOUND);
		}
	}
    
	@RequestMapping("/summary")
	@ResponseBody
	public List<ServiceSummary> listServices() {
		List<String> services = null;
		List<Provider> provider = null;
		List<ServiceSummary> servicInfos = new ArrayList<ServiceSummary>();
		
		/* 获取所有的服务列表 */
		services = providerService.findServices();
		
		for (Iterator<String> it=services.iterator();it.hasNext();){			
			ServiceSummary serv = new ServiceSummary();
			
			String servName = it.next();
			provider = providerService.findByService(servName);
			List<Consumer> consumer = consumerService.findByService(servName);
			
			if (!provider.isEmpty()) {
				serv.setServiceName(servName);
				serv.setProvidersNum(provider.size());
				serv.setConsumersNum(consumer.size());
				serv.setAppName(provider.get(0).getApplication());
			} else {
				logger.warn("Service has no provider!");
			}
			
			servicInfos.add(serv);
		}
		
		return servicInfos;
	}
}
