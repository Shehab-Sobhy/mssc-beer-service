package com.example.beerservicedemo.controller;

import com.example.beerservicedemo.dto.BeerDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(BeerController.class)
class BeerControllerTest {


    @Autowired
    public MockMvc MOCK_MVC;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void getBeerById() throws Exception {
//        Perform the request and assert the response
        MOCK_MVC.perform(MockMvcRequestBuilders.get("/api/v1/beer/{beerId}", UUID.randomUUID().toString())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }


    @Test
    void saveNewBeer() throws Exception {
        BeerDTO beerDTO = BeerDTO.builder().build();
        String beerDtoJson = objectMapper.writeValueAsString(beerDTO);

        MOCK_MVC.perform(MockMvcRequestBuilders.post("/api/v1/beer/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(beerDtoJson))
                .andExpect(status().isCreated());
    }

    @Test
    void updateBeerById() throws Exception {
        BeerDTO beerDTO = BeerDTO.builder().build();
        String beerDtoJson = objectMapper.writeValueAsString(beerDTO);

        MOCK_MVC.perform(MockMvcRequestBuilders.put("/api/v1/beer/", UUID.randomUUID().toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(beerDtoJson))
                .andExpect(status().isNoContent());

    }
}