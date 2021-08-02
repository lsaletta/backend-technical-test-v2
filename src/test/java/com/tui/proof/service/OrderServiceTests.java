package com.tui.proof.service;


import com.tui.proof.common.BaseTest;
import com.tui.proof.domain.entity.OrderEntity;
import com.tui.proof.domain.repository.OrderRepository;
import com.tui.proof.exception.TUIMMException;
import com.tui.proof.model.Order;
import com.tui.proof.service.impl.OrderServiceImpl;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.apache.commons.lang3.StringUtils.EMPTY;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTests extends BaseTest {

    private OrderService orderService;

    @Mock
    private OrderRepository orderRepository;

    private List<OrderEntity> orderEntityList;

    @BeforeEach
    public void init() throws IOException {
        orderEntityList =
                getFileFromListResources("jsons/Orders.json", OrderEntity.class);
        orderService = new OrderServiceImpl(orderRepository);
    }

    @Test
    void givenRepositoryOutputTest1_whenGetPrice_thenReturnOrders() throws IOException {
        Mockito.when(orderRepository.getByClientInfo(eq(EMPTY), eq(EMPTY), any(Pageable.class)))
                .thenReturn(new PageImpl<>(orderEntityList));

        Page<Order> orders = orderService.getOrders(0, 5, EMPTY, EMPTY);
        verify(orderRepository, times(1))
                .getByClientInfo(eq(EMPTY), eq(EMPTY), any(Pageable.class));

        Assertions.assertEquals(5L, orders.getTotalElements());

    }

    @Test
    void givenRepositoryOutputTest2_whenGetPrice_thenThrowException() throws IOException {
        Mockito.when(orderRepository.getByClientInfo(eq(EMPTY), eq(EMPTY), any(Pageable.class)))
                .thenReturn(new PageImpl<>(new ArrayList<>()));

        TUIMMException tuimmException = Assertions.assertThrows(TUIMMException.class, () -> {
            orderService.getOrders(0, 5, EMPTY, EMPTY);
        });

        verify(orderRepository, times(1))
                .getByClientInfo(eq(EMPTY), eq(EMPTY), any(Pageable.class));

        Assert.assertEquals("No orders content", tuimmException.getErrorDescription());

    }

    @Test
    void givenRepositoryOutputTest3_whenGetPrice_thenThrowException() throws IOException {
        Mockito.when(orderRepository.getByClientInfo(eq(EMPTY), eq(EMPTY), any(Pageable.class)))
                .thenReturn(null);

        TUIMMException tuimmException = Assertions.assertThrows(TUIMMException.class, () -> {
            orderService.getOrders(0, 5, EMPTY, EMPTY);
        });

        verify(orderRepository, times(1))
                .getByClientInfo(eq(EMPTY), eq(EMPTY), any(Pageable.class));

        Assert.assertEquals("Error in order search", tuimmException.getErrorDescription());

    }

}
