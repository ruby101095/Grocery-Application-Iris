package com.iris.grocery_app.dto;

import lombok.Data;

import java.util.List;

@Data
public class ProductPriceConfig
{
    private String productCode;
    private List<PriceVersionConfig> priceVersions;
}
