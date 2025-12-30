package com.iris.grocery_app.dto;

import lombok.Data;

import java.util.List;

@Data
public class PriceConfigRoot
{
    private List<ProductPriceConfig> products;
}
