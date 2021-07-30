package com.tui.proof.service.impl;

import com.tui.proof.domain.repository.OrderRepository;
import com.tui.proof.exception.TUIMMException;
import com.tui.proof.model.Order;
import com.tui.proof.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;


@Log4j2
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;


    @Override
    public Page<Order> getOrders(int page, int size, String name, String lastName) throws TUIMMException {
        return null;
    }
}
