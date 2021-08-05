package com.tui.proof.controller;

import com.tui.proof.common.BaseTest;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;

import java.util.concurrent.TimeUnit;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ActiveProfiles("test")
class OrderControllerTests extends BaseTest {

    public static final String ORDER_ENDPOINT = "/order";
    @Autowired
    private MockMvc mvc;

    @Value("${order.updateTime}")
    private Long updateTime;

    @Test
    @Order(1)
    public void givenDefaultInput_whenGetOrders_ReturnAllOrders()
            throws Exception {
        mvc.perform(get(ORDER_ENDPOINT)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content", hasSize(8)));
    }

    @Test
    @Order(2)
    public void givenCustomSize_whenGetOrders_ReturnAllOrders()
            throws Exception {
        mvc.perform(get(ORDER_ENDPOINT)
                .param("size", "2")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content", hasSize(2)));
    }

    @Test
    @Order(3)
    public void givenInput1_whenGetOrders_ReturnOrders()
            throws Exception {
        mvc.perform(get(ORDER_ENDPOINT)
                .param("name", "LU")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content", hasSize(2)));
    }

    @Test
    @Order(4)
    public void givenInput2_whenGetOrders_ReturnOrders()
            throws Exception {
        mvc.perform(get(ORDER_ENDPOINT)
                .param("lastName", "SA")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content", hasSize(4)));
    }

    @Test
    @Order(5)
    public void givenInput3_whenGetOrders_ReturnOrders()
            throws Exception {
        mvc.perform(get(ORDER_ENDPOINT)
                .param("name", "P")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content", hasSize(2)));
    }

    @Test
    @Order(6)
    public void givenInput4_whenGetOrders_ReturnNoContent()
            throws Exception {
        mvc.perform(get(ORDER_ENDPOINT)
                .param("name", "X")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    @Order(7)
    public void givenInput5_whenGetOrders_ReturnBadRequest()
            throws Exception {
        mvc.perform(get(ORDER_ENDPOINT)
                .param("name", "1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    @Order(8)
    public void givenInput6_whenGetOrders_ReturnBadRequest()
            throws Exception {
        mvc.perform(get(ORDER_ENDPOINT)
                .param("lastName", "1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    @Order(9)
    public void givenValidInput_whenSaveOrder_ReturnCreated()
            throws Exception {
        postOrderCreated();
    }

    @Test
    @Order(10)
    public void givenClientNotExists_whenSaveOrder_ReturnNoContent()
            throws Exception {
        mvc.perform(post(ORDER_ENDPOINT)
                .content("{\n" +
                        "  \"delivery_address\": {\n" +
                        "    \"street\": \"string\",\n" +
                        "    \"postcode\": \"string\",\n" +
                        "    \"city\": \"string\",\n" +
                        "    \"country\": \"string\"\n" +
                        "  },\n" +
                        "  \"pilotes\": 10,\n" +
                        "  \"client_id\": 9\n" +
                        "}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    @Order(11)
    public void givenInputInvalid_whenSaveOrder_ReturnBadRequest()
            throws Exception {
        mvc.perform(post(ORDER_ENDPOINT)
                .content("{\n" +
                        "  \"delivery_address\": {\n" +
                        "    \"street\": \"string\",\n" +
                        "    \"postcode\": \"string\",\n" +
                        "    \"city\": \"string\",\n" +
                        "    \"country\": \"string\"\n" +
                        "  },\n" +
                        "  \"pilotes\": 0,\n" +
                        "  \"client_id\": 9\n" +
                        "}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    @Order(12)
    public void givenInputValid_whenUpdatedOrder_ReturnOk()
            throws Exception {

        String contentAsString = postOrderCreated()
                .andReturn().getResponse().getContentAsString();

        com.tui.proof.model.Order order =
                getFileFromResources(contentAsString, com.tui.proof.model.Order.class);

        putOrder(order.getNumber(), status().isOk());

    }

    @Test
    @Order(13)
    public void givenUpdateTimeExceeded_whenUpdatedOrder_ReturnNoContent()
            throws Exception {

        String contentAsString = postOrderCreated()
                .andReturn().getResponse().getContentAsString();

        com.tui.proof.model.Order order =
                getFileFromResources(contentAsString, com.tui.proof.model.Order.class);

        TimeUnit.SECONDS.sleep(updateTime + 1);

        putOrder(order.getNumber(), status().isNoContent());

    }

    @Test
    @Order(14)
    public void givenInputValid_whenDeleteOrder_ReturnOk()
            throws Exception {

        String contentAsString = postOrderCreated()
                .andReturn().getResponse().getContentAsString();

        com.tui.proof.model.Order order =
                getFileFromResources(contentAsString, com.tui.proof.model.Order.class);

        deleteOrder(order.getNumber(), status().isOk());

    }

    @Test
    @Order(15)
    public void givenUpdateTimeExceeded_whenDeleteOrder_ReturnNoContent()
            throws Exception {

        String contentAsString = postOrderCreated()
                .andReturn().getResponse().getContentAsString();

        com.tui.proof.model.Order order =
                getFileFromResources(contentAsString, com.tui.proof.model.Order.class);

        TimeUnit.SECONDS.sleep(updateTime + 1);

        deleteOrder(order.getNumber(), status().isNoContent());

    }


    private ResultActions postOrderCreated() throws Exception {
        return mvc.perform(post(ORDER_ENDPOINT)
                .content("{\n" +
                        "  \"delivery_address\": {\n" +
                        "    \"street\": \"string\",\n" +
                        "    \"postcode\": \"string\",\n" +
                        "    \"city\": \"string\",\n" +
                        "    \"country\": \"string\"\n" +
                        "  },\n" +
                        "  \"pilotes\": 5,\n" +
                        "  \"client_id\": 2\n" +
                        "}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    private void putOrder(Long orderNumber, ResultMatcher expectedStatus) throws Exception {
        mvc.perform(put(ORDER_ENDPOINT + "/" + orderNumber)
                .content("{\n" +
                        "  \"delivery_address\": {\n" +
                        "    \"street\": \"string\",\n" +
                        "    \"postcode\": \"string\",\n" +
                        "    \"city\": \"string\",\n" +
                        "    \"country\": \"string\"\n" +
                        "  },\n" +
                        "  \"pilotes\": 5,\n" +
                        "  \"client_id\": 2\n" +
                        "}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(expectedStatus);
    }

    private void deleteOrder(Long orderNumber, ResultMatcher expectedStatus) throws Exception {
        mvc.perform(delete(ORDER_ENDPOINT + "/" + orderNumber)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(expectedStatus);
    }


}
