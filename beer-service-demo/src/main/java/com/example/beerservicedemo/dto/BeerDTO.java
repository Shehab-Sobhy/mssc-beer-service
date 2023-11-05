package com.example.beerservicedemo.dto;

import com.example.beerservicedemo.entity.BeerStyleEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BeerDTO {

    UUID id;
    Integer version;
    private OffsetDateTime createData;
    private OffsetDateTime lastModifiedDate;
    String beerName;
    private BeerStyleEnum beerStyle;

    private Long upc;

    private BigDecimal price;

    private Integer quantityOnHand;


}
