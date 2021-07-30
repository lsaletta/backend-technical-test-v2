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
    public void givenCustomOffset_whenGetOrders_ReturnAllOrders()
            throws Exception {
        mvc.perform(get(ORDER_ENDPOINT)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content", hasSize(5)));
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
    public void givenInput4_whenGetOrders_ReturnEmpty()
            throws Exception {
        mvc.perform(get(ORDER_ENDPOINT)
                .param("name", "X")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

//    @Test
//    public void givenInputTest2_whenGetPrice_thenReturnPriceID2()
//            throws Exception {
//        mvc.perform(get(PRICE_ENDPOINT)
//                .param("application_date", "2020-06-14 16:00:00")
//                .param("product_id", "35455")
//                .param("brand_id", "1")
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.price").value("25.45"));
//    }
//
//    @Test
//    public void givenInputTest3_whenGetPrice_thenReturnPriceID1()
//            throws Exception {
//        mvc.perform(get(PRICE_ENDPOINT)
//                .param("application_date", "2020-06-14 21:00:00")
//                .param("product_id", "35455")
//                .param("brand_id", "1")
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.price").value("35.5"));
//    }
//
//    @Test
//    public void givenInputTest4_whenGetPrice_thenReturnPriceID3()
//            throws Exception {
//        mvc.perform(get(PRICE_ENDPOINT)
//                .param("application_date", "2020-06-15 10:00:00")
//                .param("product_id", "35455")
//                .param("brand_id", "1")
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.price").value("30.5"));
//    }
//
//    @Test
//    public void givenInputTest5_whenGetPrice_thenReturnPriceID4()
//            throws Exception {
//        mvc.perform(get(PRICE_ENDPOINT)
//                .param("application_date", "2020-06-16 21:00:00")
//                .param("product_id", "35455")
//                .param("brand_id", "1")
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.price").value("38.95"));
//    }
//
//    @Test
//    public void givenInputTest5_whenGetPrice_thenReturnNotFoundPrice()
//            throws Exception {
//        mvc.perform(get(PRICE_ENDPOINT)
//                .param("application_date", "2020-06-16 21:00:00")
//                .param("product_id", "99999")
//                .param("brand_id", "1")
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isNotFound())
//                .andExpect(jsonPath("$.message").value("Price not found"));
//    }
}
