package com.iris.grocery_app.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Discount
{
    private String discountId;

    private String productCode;

    private String expression;

    private Map<String, BigDecimal> params;
}

