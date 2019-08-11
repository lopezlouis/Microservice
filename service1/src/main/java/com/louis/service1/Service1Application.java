package com.louis.service1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SpringBootApplication
@EnableDiscoveryClient
@RestController
public class Service1Application {

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private LoadBalancerClient loadBalancer;

    @Value("${spring.application.name}")
    private String appName;

    @Value("${server.port}")
    private String portNumber;

    public static void main(String[] args) {
        SpringApplication.run(Service1Application.class, args);
    }

    @GetMapping("/get/service/1")
    public String get(){

        loadBalancer.

        List<ServiceInstance> inst = discoveryClient.getInstances("server1");
        for(ServiceInstance in: inst){
            System.out.println("Running " + in.toString());
        }
        System.out.println();
        System.out.println(discoveryClient.getServices());
        return String.format("Hello from '%s with Port Number %s'!", appName, portNumber);
    }

}
