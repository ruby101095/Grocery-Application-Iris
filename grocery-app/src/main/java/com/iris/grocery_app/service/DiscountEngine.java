package com.iris.grocery_app.service;

import com.iris.grocery_app.entity.Discount;

import java.math.BigDecimal;

public interface DiscountEngine
{
    BigDecimal calculateItemLevel(
            Discount discount,
            int quantity,
            BigDecimal price);

    BigDecimal calculateCartLevel(
            Discount discount,
            BigDecimal subtotal);
}
