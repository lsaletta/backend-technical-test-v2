package com.tui.proof.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class OrderControllerTests {

    public static final String ORDER_ENDPOINT = "/order";
    @Autowired
    private MockMvc mvc;

    @Test
    public void givenDefaultInput_whenGetOrders_ReturnAllOrders()
            throws Exception {
        mvc.perform(get(ORDER_ENDPOINT)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content", hasSize(5)));
    }

    @Test
    public void givenCustomSize_whenGetOrders_ReturnAllOrders()
            throws Exception {
        mvc.perform(get(ORDER_ENDPOINT)
                .param("size", "2")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content", hasSize(2)));
    }

    @Test
    public void givenInput1_whenGetOrders_ReturnOrders()
            throws Exception {
        mvc.perform(get(ORDER_ENDPOINT)
                .param("name", "LU")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content", hasSize(2)));
    }

    @Test
    public void givenInput2_whenGetOrders_ReturnOrders()
            throws Exception {
        mvc.perform(get(ORDER_ENDPOINT)
                .param("lastName", "SA")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content", hasSize(4)));
    }

    @Test
    public void givenInput3_whenGetOrders_ReturnOrders()
            throws Exception {
        mvc.perform(get(ORDER_ENDPOINT)
                .param("name", "P")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content", hasSize(2)));
    }

    @Test
    public void givenInput4_whenGetOrders_ReturnNoContent()
            throws Exception {
        mvc.perform(get(ORDER_ENDPOINT)
                .param("name", "X")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    public void givenInput5_whenGetOrders_ReturnBadRequest()
            throws Exception {
        mvc.perform(get(ORDER_ENDPOINT)
                .param("name", "1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void givenInput6_whenGetOrders_ReturnBadRequest()
            throws Exception {
        mvc.perform(get(ORDER_ENDPOINT)
                .param("lastName", "1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

}
