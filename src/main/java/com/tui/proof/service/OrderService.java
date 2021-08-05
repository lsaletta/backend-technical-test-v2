package com.tui.proof.service;

import com.tui.proof.exception.TUIMMException;
import com.tui.proof.model.Order;
import com.tui.proof.model.request.BaseOrderRQ;
import com.tui.proof.model.request.CreateOrderRQ;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public interface OrderService {

    Page<Order> getOrders(
            int page,
            int size,
            String name,
            String lastName) throws TUIMMException;

    Order createOrder(CreateOrderRQ createOrderRQ) throws TUIMMException;


    Order updateOrder(Long id, BaseOrderRQ baseOrderRQ) throws TUIMMException;

    void deleteOrder(Long id) throws TUIMMException;


}
