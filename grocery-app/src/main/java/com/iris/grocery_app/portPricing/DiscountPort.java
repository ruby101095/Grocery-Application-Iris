package com.iris.grocery_app.portPricing;

import com.iris.grocery_app.entity.Discount;

import java.util.List;

public interface DiscountPort {

    List<Discount> getItemDiscounts(String productCode);

    List<Discount> getCartDiscounts();
}

