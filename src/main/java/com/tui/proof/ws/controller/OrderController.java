package com.tui.proof.ws.controller;

import com.tui.proof.annotation.AlphaConstraint;
import com.tui.proof.model.Order;
import com.tui.proof.model.request.BaseOrderRQ;
import com.tui.proof.model.request.CreateOrderRQ;
import com.tui.proof.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Log4j2
@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
@Validated
public class OrderController {

    private final OrderService orderService;

    /**
     * Search orders by customer data, allowing partial searches.
     *
     * @param page
     * @param size
     * @param name
     * @param lastName
     * @return paged orders
     */
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Search orders by customer data, allowing partial searches")
    public ResponseEntity<Page<Order>> getOrders(
            @RequestParam(value = "page", defaultValue = "0") final int page,
            @RequestParam(value = "size", defaultValue = "10") final int size,
            @RequestParam(value = "name", defaultValue = "") @AlphaConstraint final String name,
            @RequestParam(value = "lastName", defaultValue = "") @AlphaConstraint final String lastName) {
        return new ResponseEntity<>(orderService.getOrders(page, size, name, lastName), HttpStatus.OK);
    }


    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Create pilotes order")
    public ResponseEntity<Order> create(@Valid @RequestBody final CreateOrderRQ createOrderRQ) {
        return new ResponseEntity<>(orderService.createOrder(createOrderRQ), HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Update pilotes order")
    public ResponseEntity<Order> update(@Valid @RequestBody final BaseOrderRQ baseOrderRQ,
                                        @PathVariable("id") final Long id) {
        return new ResponseEntity<>(orderService.updateOrder(id, baseOrderRQ), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Delete pilotes order")
    public ResponseEntity<Void> delete(
            @PathVariable("id") final Long id) {
        orderService.deleteOrder(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
