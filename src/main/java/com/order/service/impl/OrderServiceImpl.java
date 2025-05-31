package com.order.service.impl;

import com.order.client.InventoryServiceClient;
import com.order.dto.OrderRequest;
import com.order.dto.OrderResponse;
import com.order.model.Order;
import com.order.repository.OrderRepository;
import com.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final InventoryServiceClient inventoryServiceClient;

    public OrderResponse placeOrder(OrderRequest orderRequest){
        //1.Use Mockito
        //2.Using Wiremock
        var isInStock=inventoryServiceClient.isInStock(orderRequest.skuCode(),orderRequest.quantity());
        if(isInStock){
            Order order = new Order();
            order.setOrderNumber(UUID.randomUUID().toString());
            order.setSkuCode(orderRequest.skuCode());
            order.setPrice(orderRequest.price());
            order.setQuantity(orderRequest.quantity());
            return convertFromOrderToOrderResponse(order);
        }
        else{
            throw new RuntimeException("Product with sku Code "+orderRequest.skuCode()+" is not in stock");
        }
    }

    public List<OrderResponse> getAllOrders(){
        List<Order> orders = orderRepository.findAll();
        return orders.stream().map(this::convertFromOrderToOrderResponse).toList();
    }

    private OrderResponse convertFromOrderToOrderResponse(Order order){
        return new OrderResponse(order.getId(),order.getOrderNumber(),order.getSkuCode(),
                order.getPrice(),order.getQuantity());
    }
}
