package com.example.beerservicedemo.controller;

import com.example.beerservicedemo.dto.BeerDTO;
import com.example.beerservicedemo.entity.BeerStyleEnum;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjuster;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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
        MOCK_MVC.perform(get("/api/v1/beer/{beerId}", UUID.randomUUID().toString())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }


    @Test
    void saveNewBeer() throws Exception {
        BeerDTO beerDTO = BeerDTO.builder().beerName("asdasdasd").build();
        String beerDtoJson = objectMapper.writeValueAsString(beerDTO);

        MOCK_MVC.perform(post("/api/v1/beer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(beerDtoJson))
                .andExpect(status().isCreated());
    }

    @Test
    void updateBeerById() throws Exception {
        // Create a random UUID for testing
        UUID beerId = UUID.randomUUID();

        // Create a BeerDTO with the updated name
        BeerDTO beerDTO = BeerDTO.builder().beerName("update name").build();
        String beerDtoJson = objectMapper.writeValueAsString(beerDTO);
        System.out.println( "out put ..................."+ beerDtoJson);
        MOCK_MVC.perform(put("/api/v1/beer/{beerId}", beerId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(beerDtoJson))
                .andExpect(status().isNoContent());
    }

    /**
     * Method under test: {@link BeerController#updateBeerById(UUID, BeerDTO)}
     */
    @Test
    void testUpdateBeerById() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R005 Unable to load class.
        //   Class: javax.servlet.http.HttpServlet
        //   Please check that the class is available on your test runtime classpath.
        //   See https://diff.blue/R005 to resolve this issue.

        BeerController beerController = new BeerController();
        UUID beerId = UUID.randomUUID();
        ResponseEntity<BeerDTO> actualUpdateBeerByIdResult = beerController.updateBeerById(beerId, new BeerDTO());
        assertNull(actualUpdateBeerByIdResult.getBody());
        assertEquals(204, actualUpdateBeerByIdResult.getStatusCodeValue());
        assertTrue(actualUpdateBeerByIdResult.getHeaders().isEmpty());
    }

    /**
     * Method under test: {@link BeerController#updateBeerById(UUID, BeerDTO)}
     */
    @Test
    void testUpdateBeerById2() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R005 Unable to load class.
        //   Class: javax.servlet.http.HttpServlet
        //   Please check that the class is available on your test runtime classpath.
        //   See https://diff.blue/R005 to resolve this issue.

        BeerController beerController = new BeerController();
        UUID beerId = UUID.randomUUID();
        TemporalAdjuster temporalAdjuster = mock(TemporalAdjuster.class);
        when(temporalAdjuster.adjustInto(Mockito.<Temporal>any()))
                .thenReturn(OffsetDateTime.of(LocalDate.of(1970, 1, 1), LocalTime.MIDNIGHT, ZoneOffset.UTC));
        OffsetDateTime createData = OffsetDateTime.of(LocalDate.of(1970, 1, 1), LocalTime.MIDNIGHT, ZoneOffset.UTC);
        createData.with(temporalAdjuster);
        UUID id = UUID.randomUUID();
        OffsetDateTime lastModifiedDate = OffsetDateTime.of(LocalDate.of(1970, 1, 1), LocalTime.MIDNIGHT, ZoneOffset.UTC);
        ResponseEntity<BeerDTO> actualUpdateBeerByIdResult = beerController.updateBeerById(beerId, new BeerDTO(id, 1,
                createData, lastModifiedDate, "Beer Name", BeerStyleEnum.LAGER, 1L, new BigDecimal("2.3"), 1));
        verify(temporalAdjuster).adjustInto(Mockito.<Temporal>any());
        assertNull(actualUpdateBeerByIdResult.getBody());
        assertEquals(204, actualUpdateBeerByIdResult.getStatusCodeValue());
        assertTrue(actualUpdateBeerByIdResult.getHeaders().isEmpty());
    }
}


