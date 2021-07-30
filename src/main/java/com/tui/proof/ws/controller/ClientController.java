package com.tui.proof.ws.controller;

import com.tui.proof.domain.entity.ClientEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@RequestMapping("/client")
@RequiredArgsConstructor
public class ClientController {

    @GetMapping("/{id}")
    public ResponseEntity<String> getClient(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestParam(value = "name", defaultValue = "") String name,
            @PathVariable(value = "id") String telephone) {
        return new ResponseEntity<>("", HttpStatus.OK);
    }
}
