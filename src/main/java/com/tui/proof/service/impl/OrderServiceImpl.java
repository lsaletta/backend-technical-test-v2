package com.tui.proof.service.impl;

import com.tui.proof.domain.entity.OrderEntity;
import com.tui.proof.domain.repository.AddressRepository;
import com.tui.proof.domain.repository.ClientRepository;
import com.tui.proof.domain.repository.OrderRepository;
import com.tui.proof.exception.TUIMMException;
import com.tui.proof.mapper.OrderMapper;
import com.tui.proof.model.Order;
import com.tui.proof.model.request.BaseOrderRQ;
import com.tui.proof.model.request.CreateOrderRQ;
import com.tui.proof.service.OrderService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;


@Log4j2
@Service
@Getter
@Setter
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final AddressRepository addressRepository;

    private final OrderRepository orderRepository;

    private final ClientRepository clientRepository;

    @Value("${order.updateTime}")
    private Long updateTime;

    /**
     * Search orders by customer data, allowing partial searches.
     *
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

    @Override
    public Order createOrder(CreateOrderRQ createOrderRQ) throws TUIMMException {
        return null;
    }

    @Override
    public Order updateOrder(Long id, BaseOrderRQ baseOrderRQ) throws TUIMMException {
        return null;
    }

    @Override
    public void deleteOrder(Long id) throws TUIMMException {

    }


}
