package com.louis.controller;

import com.louis.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by louislopez on 8/11/19.
 */
@RestController
public class AccountController {

    @Value("${spring.application.name}")
    private String appName;

    @Value("${server.port}")
    private String portNumber;

    private static final List<Account> accountList = new ArrayList<>();

    static {
        accountList.add(new Account(1, "Checking Account", 10000, 5000.23));
        accountList.add(new Account(2, "Savings Account", 20000, 21000.04));
        accountList.add(new Account(3, "Investment Account", 30000, 4309.18));
        accountList.add(new Account(4, "Checking Account", 40000, 89032.55));
    }

    @GetMapping(value = "/{employeeId}")
    public String getAccount(@PathVariable long employeeId) {
        System.err.println("Hello from " + appName + " with Port Number " + portNumber + ". Time= "
                + LocalTime.now() + "\nRequest with employee id: " + employeeId);

        Optional<Account> accountOptional = accountList
                .stream()
                .filter(acc -> acc.getEmployeeId() == employeeId)
                .findFirst();

        if (!accountOptional.isPresent()) {
            return "Account Not Found for Employee Id: ";
        } else {
            return accountOptional.get().toString();
        }
    }
}
