package com.iris.grocery_app.service;

import com.iris.grocery_app.entity.CartItem;
import com.iris.grocery_app.entity.OrderItem;
import com.iris.grocery_app.service.Impl.InventoryServiceImpl;

import java.util.List;

public interface PricingService
{
    /**
     * Converts a list of CartItem objects into priced OrderItem objects.
     * <p>
     * Steps:
     * 1. Filters out items that are unavailable in inventory.
     * 2. Maps each available cart item to an OrderItem with:
     *    - Product details
     *    - Quantity
     *    - Current price snapshot fetched from PricingPort
     */
    List<OrderItem> priceItems(List<CartItem> cart, InventoryServiceImpl inventoryService);
}

