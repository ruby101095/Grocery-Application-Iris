package com.iris.grocery_app.service;

import com.iris.grocery_app.entity.OrderItem;
import com.iris.grocery_app.entity.Receipt;

import java.math.BigDecimal;
import java.util.List;

public interface ReceiptService {

    /**
     * Calculates the subtotal of all order items before applying any discounts.
     * For each OrderItem, multiplies the unit price at purchase by the quantity,
     * then sums up all items to get the subtotal.
     */
    BigDecimal calculateSubtotal(List<OrderItem> items);

    /**
     * Generates a Receipt object with all required fields.
     * Computes total by subtracting discount from subtotal.
     */
    Receipt generate(
            List<OrderItem> items,
            BigDecimal subtotal,
            BigDecimal discount
    );
}
