package com.iris.grocery_app.service;

import com.iris.grocery_app.entity.Discount;

import java.math.BigDecimal;

public interface DiscountEngine
{
    /**
     * Calculates the discount for a single item based on the provided Discount rule.
     * <p>
     * Uses Spring Expression Language (SpEL) to evaluate dynamic discount expressions.
     * Context variables include quantity, price, and any additional parameters from the discount.
     *
     */
    BigDecimal calculateItemLevel(
            Discount discount,
            int quantity,
            BigDecimal price);

    /**
     * Calculates the discount applicable at the cart level based on the provided Discount rule.
     * <p>
     * Uses SpEL to evaluate expressions with subtotal and any additional parameters.
     * Returns BigDecimal.ZERO if discount is null.
     *
     */
    BigDecimal calculateCartLevel(
            Discount discount,
            BigDecimal subtotal);
}
