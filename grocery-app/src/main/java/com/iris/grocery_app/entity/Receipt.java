package com.iris.grocery_app.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
public class Receipt
{
    private List<OrderItem> items;
    private BigDecimal subtotal;
    private BigDecimal discount;
    private BigDecimal total;
}

