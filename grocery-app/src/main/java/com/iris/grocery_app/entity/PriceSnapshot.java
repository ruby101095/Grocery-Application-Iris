package com.iris.grocery_app.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class PriceSnapshot
{
    private String productCode;
    private String priceVersion;
    private BigDecimal priceAtPurchase;
}
