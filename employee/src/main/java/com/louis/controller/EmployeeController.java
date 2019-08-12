package com.louis.controller;

import com.louis.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by louislopez on 8/11/19.
 */
@RestController
public class EmployeeController {


    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    @Autowired
    RestTemplate restTemplate;


    @Value("${spring.application.name}")
    private String appName;

    @Value("${server.port}")
    private String portNumber;

    private static final List<Employee> employeeList = new ArrayList<>();

    static {
        employeeList.add(new Employee("Lebron James ", 1, 10000));
        employeeList.add(new Employee("Kobe Bryant", 2, 20000));
        employeeList.add(new Employee("Carmelo Anthony", 3, 30000));
        employeeList.add(new Employee("Nate Robinson", 4, 40000));
    }


    @GetMapping(value="/home")
    public String home(){
        return String.format("Employee Controller");
    }

    @GetMapping("/{id}")
    public String get(@PathVariable long id) {
        System.err.println("Hello from " + appName + " with Port Number " + portNumber + ". Time= "
                + LocalTime.now() + "\nRequest with employee id: " + id);

        Optional<Employee> employeeOptional = employeeList
                .stream()
                .filter(emp -> emp.getEmployeeId() == id)
                .findFirst();

        if (!employeeOptional.isPresent()) {
            return "Employee with " + id + " not found.";
        }

        return restTemplate.getForObject("http://account-service/" +
                employeeOptional.get().getEmployeeId(), String.class);
    }
}
