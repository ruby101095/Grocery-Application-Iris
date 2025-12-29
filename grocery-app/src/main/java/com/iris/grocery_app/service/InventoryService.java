package com.iris.grocery_app.service;

import org.springframework.stereotype.Service;

@Service
public class InventoryService
{
    public boolean isAvailable(String productCode, int qty)
    {
        return true;
    }
}
