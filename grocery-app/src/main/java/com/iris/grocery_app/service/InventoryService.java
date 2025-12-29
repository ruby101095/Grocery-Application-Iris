package com.iris.grocery_app.service;

public interface InventoryService
{
    boolean isAvailable(String productCode, int qty);
}
