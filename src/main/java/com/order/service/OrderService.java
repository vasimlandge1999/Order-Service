package com.order.service;

import com.order.dto.OrderRequest;
import com.order.dto.OrderResponse;

import java.util.List;

public interface OrderService {
    OrderResponse placeOrder(OrderRequest orderRequest);
    List<OrderResponse> getAllOrders();
}
