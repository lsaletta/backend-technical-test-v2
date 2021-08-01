package com.tui.proof.ws.controller;

import com.tui.proof.annotation.AlphaConstraint;
import com.tui.proof.model.Order;
import com.tui.proof.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
}
