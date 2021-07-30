package com.tui.proof.ws.controller;

import com.tui.proof.model.Order;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@Log4j2
@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Search orders by customer data, allowing partial searches")
    public ResponseEntity<Page<Order>> getOrders(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestParam(value = "name", defaultValue = "") String name,
            @RequestParam(value = "lastName", defaultValue = "") String lastName) {
        return new ResponseEntity<>(new PageImpl<>(new ArrayList<>()), HttpStatus.OK);
    }
}
