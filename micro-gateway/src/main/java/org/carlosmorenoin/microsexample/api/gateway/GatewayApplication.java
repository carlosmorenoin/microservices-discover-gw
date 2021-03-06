package org.carlosmorenoin.microsexample.api.gateway;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy
@EnableDiscoveryClient
public class GatewayApplication {

    public static void main(String[] args) {
        System.setProperty("spring.config.name", "micro-gateway");
        new SpringApplicationBuilder(GatewayApplication.class).web(true).run(args);
    }

}
