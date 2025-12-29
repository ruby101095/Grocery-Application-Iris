package com.iris.grocery_app.entity;

import com.iris.grocery_app.DiscountScope;
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

    private String version;

    private DiscountScope scope;

    private String productCode;

    private String expression;

    private Map<String, BigDecimal> params;
}

