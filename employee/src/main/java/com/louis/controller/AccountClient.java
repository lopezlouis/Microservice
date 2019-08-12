package com.louis.controller;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Created by louislopez on 8/11/19.
 *
 * Will not be used for now
 */
@FeignClient(name = "account-service")
public interface AccountClient {
    @GetMapping(value = "/account/{employeeId}")
    String getAccount(@PathVariable long employeeId);
}
