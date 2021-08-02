package com.tui.proof.controller;

import com.tui.proof.common.BaseTest;
import com.tui.proof.model.Client;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ClientControllerTests extends BaseTest {

    public static final String CLIENT_ENDPOINT = "/client";
    @Autowired
    private MockMvc mvc;

    @Test
    public void givenDefaultInput_whenGetClients_ReturnClients()
            throws Exception {
        mvc.perform(get(CLIENT_ENDPOINT)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content", hasSize(4)));
    }

    @Test
    public void givenCustomSize_whenGetClients_ReturnClients()
            throws Exception {
        mvc.perform(get(CLIENT_ENDPOINT)
                .param("size", "2")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content", hasSize(2)));
    }

    @Test
    public void givenExistClient_whenGetClient_ReturnClient()
            throws Exception {
        mvc.perform(get(CLIENT_ENDPOINT + "/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void givenNotExistClient_whenGetClient_ReturnNoContent()
            throws Exception {
        mvc.perform(get(CLIENT_ENDPOINT + "/8")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    public void givenValidInput_whenSaveClient_ReturnCreated()
            throws Exception {
        mvc.perform(post(CLIENT_ENDPOINT)
                .content("{\n" +
                        "  \"first_name\" : \"CARLO\",\n" +
                        "  \"last_name\" : \"SALETTA\",\n" +
                        "  \"telephone\" : \"600112233\"\n" +
                        "}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    public void givenInputDuplicatePhone_whenSaveClient_ReturnNoContent()
            throws Exception {
        mvc.perform(post(CLIENT_ENDPOINT)
                .content("{\n" +
                        "  \"first_name\" : \"LUCIA\",\n" +
                        "  \"last_name\" : \"SALETTA\",\n" +
                        "  \"telephone\" : \"600097595\"\n" +
                        "}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    public void givenInvalidNamePhone_whenSaveClient_ReturnBadRequest()
            throws Exception {
        mvc.perform(post(CLIENT_ENDPOINT)
                .content("{\n" +
                        "  \"first_name\" : \"1\",\n" +
                        "  \"last_name\" : \"SALETTA\",\n" +
                        "  \"telephone\" : \"600097595\"\n" +
                        "}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void givenInvalidLastNamePhone_whenSaveClient_ReturnBadRequest()
            throws Exception {
        mvc.perform(post(CLIENT_ENDPOINT)
                .content("{\n" +
                        "  \"first_name\" : \"LUCA\",\n" +
                        "  \"last_name\" : \"\",\n" +
                        "  \"telephone\" : \"600097595\"\n" +
                        "}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void givenExistClient_whenDeleteClient_ReturnOk()
            throws Exception {

        String contentAsString = mvc.perform(post(CLIENT_ENDPOINT)
                .content("{\n" +
                        "  \"first_name\" : \"TEMPNAME\",\n" +
                        "  \"last_name\" : \"TEMPLASTNAME\",\n" +
                        "  \"telephone\" : \"612112233\"\n" +
                        "}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn().getResponse().getContentAsString();

        Client client =
                getFileFromResources(contentAsString, Client.class);


        mvc.perform(delete(CLIENT_ENDPOINT + "/" + client.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void givenNotExistClient_whenDeleteClient_ReturnNoContent()
            throws Exception {
        mvc.perform(delete(CLIENT_ENDPOINT + "/8")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }


}
