package com.example.beerservicedemo.controller;

import com.example.beerservicedemo.dto.BeerDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/beer")
public class BeerController {


    @GetMapping("/{beerId}")
    public ResponseEntity<BeerDTO> getBeerById(@PathVariable UUID beerId) {

        // Todo impl
        return ResponseEntity.ok(BeerDTO.builder().build());
    }


    @PostMapping("")

    public ResponseEntity saveNewBeer(@RequestBody BeerDTO beerDTO) {

        // Todo impl
        return  ResponseEntity.ok(HttpStatus.CREATED);
    }


    @PutMapping("/{beerId}")
    public ResponseEntity<BeerDTO> updateBeerById(@PathVariable UUID beerId , @RequestBody BeerDTO beerDTO )  {

        // Todo impl
        return   ResponseEntity.noContent().build();

    }












}
