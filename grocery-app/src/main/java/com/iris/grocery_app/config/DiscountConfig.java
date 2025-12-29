package com.iris.grocery_app.config;

import com.iris.grocery_app.entity.Discount;

import java.util.List;

public class DiscountConfig {

    private List<Discount> discounts;

    public List<Discount> getDiscounts() {
        return discounts;
    }

    public void setDiscounts(List<Discount> discounts) {
        this.discounts = discounts;
    }
}
