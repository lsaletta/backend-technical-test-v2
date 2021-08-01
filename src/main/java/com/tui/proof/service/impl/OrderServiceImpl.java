package com.tui.proof.service.impl;

import com.tui.proof.domain.entity.OrderEntity;
import com.tui.proof.domain.repository.OrderRepository;
import com.tui.proof.exception.TUIMMException;
import com.tui.proof.mapper.OrderMapper;
import com.tui.proof.model.Order;
import com.tui.proof.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;


@Log4j2
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    /**
     * Search orders by customer data, allowing partial searches.
     * @param page
     * @param size
     * @param name
     * @param lastName
     * @return paged orders
     * @throws TUIMMException
     */
    @Override
    public Page<Order> getOrders(final int page, final int size, final String name, final String lastName)
            throws TUIMMException {
        try {
            Page<OrderEntity> byClientInfo =
                    orderRepository.getByClientInfo(name, lastName, PageRequest.of(page, size));
            if (byClientInfo.getTotalElements() != 0L) {
                return byClientInfo.map(item -> OrderMapper.INSTANCE.toDto(item));
            }
        } catch (Exception e) {
            log.error("Error in order search: {}", e);
            throw new TUIMMException("Error in order search", e);
        }

        throw new TUIMMException("No orders content");
    }
}
