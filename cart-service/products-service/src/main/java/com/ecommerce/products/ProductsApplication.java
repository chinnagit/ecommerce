package com.ecommerce.products;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.sleuth.metric.SpanMetricReporter;
import org.springframework.cloud.sleuth.zipkin.HttpZipkinSpanReporter;
import org.springframework.cloud.sleuth.zipkin.ZipkinProperties;
import org.springframework.cloud.sleuth.zipkin.ZipkinSpanReporter;
import org.springframework.context.annotation.Bean;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;

import zipkin.Span;



//@EnableAutoConfiguration
//@Configuration
//@SpringBootApplication
//@EnableDiscoveryClient

@SpringBootApplication
@EnableDiscoveryClient

public class ProductsApplication {
	
	@Autowired
	private EurekaClient eurekaClient;

	@Autowired
	private SpanMetricReporter spanMetricReporter;

	@Autowired
	private ZipkinProperties zipkinProperties;

	@Value("${spring.sleuth.web.skipPattern}")
	private String skipPattern;

	public static void main(String[] args) {
		SpringApplication.run(ProductsApplication.class, args);
	}
	
	@Bean
	public ZipkinSpanReporter makeZipkinSpanReporter() {
	    return new ZipkinSpanReporter() {
	        private HttpZipkinSpanReporter delegate;
	        private String baseUrl;
	 
	        @Override
	        public void report(Span span) {
	  
	            InstanceInfo instance = eurekaClient
	              .getNextServerFromEureka("zipkin-service", false);
	            if (!(baseUrl != null && 
	              instance.getHomePageUrl().equals(baseUrl))) {
	                baseUrl = instance.getHomePageUrl();
	                delegate = new HttpZipkinSpanReporter(baseUrl,
	                  zipkinProperties.getFlushInterval(), 
	                  zipkinProperties.getCompression().isEnabled(), 
	                  spanMetricReporter);
	  
	                if (!span.name.matches(skipPattern)) delegate.report(span);
	            }
	        }
	    };
	}

}
