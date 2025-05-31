package com.order.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "inventory",url = "http://localhost:8083")
public interface InventoryServiceClient {

    @RequestMapping(method = RequestMethod.GET,value = "/api/inventory")
    boolean isInStock(@RequestParam String skuCode,@RequestParam int quantity);
}
