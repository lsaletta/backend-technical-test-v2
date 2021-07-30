package com.tui.proof.service;

import com.tui.proof.exception.TUIMMException;
import com.tui.proof.model.Order;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public interface OrderService {

    Page<Order> getOrders(
            int page,
            int size,
            String name,
            String lastName) throws TUIMMException;

}
