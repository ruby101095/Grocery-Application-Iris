package com.iris.grocery_app.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CartItem
{
    private String productCode;
    private int quantity;
}
