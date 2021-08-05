package com.tui.proof.service.impl;

import com.tui.proof.domain.entity.AddressEntity;
import com.tui.proof.domain.entity.OrderEntity;
import com.tui.proof.domain.repository.AddressRepository;
import com.tui.proof.domain.repository.ClientRepository;
import com.tui.proof.domain.repository.OrderRepository;
import com.tui.proof.exception.TUIMMException;
import com.tui.proof.mapper.AddressMapper;
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
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;


@Log4j2
@Service
@Getter
@Setter
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    public static final double BASE_PRICE = 95.9D;
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
    @Transactional
    public Order createOrder(final CreateOrderRQ createOrderRq) {

        try {

            if (clientRepository.findById(createOrderRq.getClientId()).isPresent()) {
                //Mapper to Entity
                OrderEntity orderEntity = OrderMapper.INSTANCE.toEntity(createOrderRq);
                //Save Address
                AddressEntity addressEntity = addressRepository.save(orderEntity.getAddress());

                //Set foreign key and additionals data
                orderEntity.setAddressId(addressEntity.getId());
                orderEntity.setOrderTotal(calculateTotalOrder(createOrderRq.getPilotes()));

                //Save Order
                OrderEntity save = orderRepository.save(orderEntity);

                //Mapper to Dto
                return OrderMapper.INSTANCE.toDto(save);
            }

        } catch (Exception e) {
            log.error("Error in create order: {}", e);
            throw new TUIMMException("Error in create order", e);
        }

        throw new TUIMMException("Client not exist, the order will not be created.");

    }


    @Override
    @Transactional
    public Order updateOrder(final Long id, final BaseOrderRQ baseOrderRQ) throws TUIMMException {
        String errorMessage = "Order not exists";

        try {
            Optional<OrderEntity> byId = orderRepository.findById(id);
            if (byId.isPresent()) {
                if (checkDateValidation(byId.get().getCreationDate())) {
                    OrderEntity orderEntity = updateOrderData(baseOrderRQ, byId.get());
                    OrderEntity save = orderRepository.save(orderEntity);
                    return OrderMapper.INSTANCE.toDto(save);
                }
                errorMessage = "Order cannot be updated: time exceeded";
            }
        } catch (Exception e) {
            log.error("Error in update order: {}", e);
            throw new TUIMMException("Error in update order", e);
        }

        throw new TUIMMException(errorMessage);
    }

    private boolean checkDateValidation(final LocalDateTime dateTime) {
        return LocalDateTime.now()
                .isBefore(dateTime.plusSeconds(updateTime));
    }

    @Override
    public void deleteOrder(final Long id) throws TUIMMException {

        String errorMessage = "Order not exists";

        try {
            Optional<OrderEntity> byId = orderRepository.findById(id);
            if (byId.isPresent()) {
                if (checkDateValidation(byId.get().getCreationDate())) {
                    orderRepository.deleteById(id);
                    return;
                }
                errorMessage = "Order cannot be updated: time exceeded";
            }
        } catch (Exception e) {
            log.error("Error in delete order: {}", e);
            throw new TUIMMException("Error in delete order", e);
        }

        throw new TUIMMException(errorMessage);

    }

    private OrderEntity updateOrderData(final BaseOrderRQ baseOrderRQ, final OrderEntity orderEntity) {
        orderEntity.setAddress(AddressMapper.INSTANCE
                .toEntity(orderEntity.getAddressId(), baseOrderRQ.getDeliveryAddress()));
        orderEntity.setPilotes(baseOrderRQ.getPilotes());
        orderEntity.setOrderTotal(calculateTotalOrder(baseOrderRQ.getPilotes()));
        return orderEntity;
    }

    private double calculateTotalOrder(final int pilotes) {
        return BASE_PRICE * pilotes;
    }
}
