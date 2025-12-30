package com.iris.grocery_app.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class PriceSnapshot
{
    private String productCode;

    @JsonIgnore
    private String priceVersion;

    private BigDecimal priceAtPurchase;
}
