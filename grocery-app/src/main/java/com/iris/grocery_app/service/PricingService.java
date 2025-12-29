package com.iris.grocery_app.service;

import com.iris.grocery_app.entity.CartItem;
import com.iris.grocery_app.entity.OrderItem;
import com.iris.grocery_app.serviceImpl.InventoryServiceImpl;

import java.util.List;

public interface PricingService
{
    List<OrderItem> priceItems(List<CartItem> cart, InventoryServiceImpl inventoryService);
}

