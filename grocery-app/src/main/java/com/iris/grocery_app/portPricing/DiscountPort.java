package com.iris.grocery_app.portPricing;

import com.iris.grocery_app.entity.Discount;

public interface DiscountPort
{
    Discount getDiscount(String productCode);
}
