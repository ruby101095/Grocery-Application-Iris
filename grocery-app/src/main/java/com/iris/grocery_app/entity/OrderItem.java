package com.iris.grocery_app.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderItem
{
    private Product product;
    private int quantity;
    private PriceSnapshot priceSnapshot;
}
