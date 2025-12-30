package com.iris.grocery_app.service;

import com.iris.grocery_app.entity.OrderItem;

import java.math.BigDecimal;
import java.util.List;

public interface DiscountService {

    /**
     * Calculates the total discount applicable at the item level.
     * <p>
     * Iterates through all order items and applies eligible item-level
     * discount rules (e.g., quantity-based, product-specific offers).
     *
     * @param items list of order items in the cart
     * @return      total item-level discount amount
     */
    BigDecimal calculateItemLevelDiscount(List<OrderItem> items);

    /**
     * Calculates the discount applicable at the cart level.
     * <p>
     * Applies cart-wide discount rules such as minimum order value,
     * seasonal promotions, or coupon-based discounts.
     *
     * @param subtotal total cart value before any discounts
     * @return         cart-level discount amount
     */
    BigDecimal calculateCartLevelDiscount(BigDecimal subtotal);
}
