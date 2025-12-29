package com.iris.grocery_app.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class PriceVersionConfig
{
    private String version;
    private BigDecimal price;
    private LocalDateTime effectiveFrom;
}

