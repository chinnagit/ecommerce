package com.ecommerce.gateway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.sleuth.metric.SpanMetricReporter;
import org.springframework.cloud.sleuth.zipkin.HttpZipkinSpanReporter;
import org.springframework.cloud.sleuth.zipkin.ZipkinProperties;
import org.springframework.cloud.sleuth.zipkin.ZipkinSpanReporter;
import org.springframework.context.annotation.Bean;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;

import zipkin.Span;

@SpringBootApplication
@EnableDiscoveryClient

@EnableZuulProxy

public class ResourceServerApplication extends SpringBootServletInitializer {

	@Autowired
	private EurekaClient eurekaClient;

//	@Autowired
//	private SpanMetricReporter spanMetricReporter;
//
//	@Autowired
//	private ZipkinProperties zipkinProperties;
	
//TODO need to verify why is not working, but works in products and userr service
//	@Value("${spring.sleuth.web.skipPattern}")
//	private String skipPattern;
	
    public static void main(String[] args) {
        SpringApplication.run(ResourceServerApplication.class, args);
    }
    
//    @Bean
//	public ZipkinSpanReporter makeZipkinSpanReporter() {
//	    return new ZipkinSpanReporter() {
//	        private HttpZipkinSpanReporter delegate;
//	        private String baseUrl;
//	 
//	        @Override
//	        public void report(Span span) {
//	  
//	            InstanceInfo instance = eurekaClient
//	              .getNextServerFromEureka("zipkin-service", false);
//	            if (!(baseUrl != null && 
//	              instance.getHomePageUrl().equals(baseUrl))) {
//	                baseUrl = instance.getHomePageUrl();
//	                delegate = new HttpZipkinSpanReporter(baseUrl,
//	                  zipkinProperties.getFlushInterval(), 
//	                  zipkinProperties.getCompression().isEnabled(), 
//	                  spanMetricReporter);
//	  
////	                if (!span.name.matches(skipPattern)) delegate.report(span);
//	            }
//	        }
//	    };
//	}

}