package com.iris.grocery_app.service.Impl;

import com.iris.grocery_app.service.InventoryService;
import org.springframework.stereotype.Service;

@Service
public class InventoryServiceImpl implements InventoryService
{
    public boolean isAvailable(String productCode, int qty)
    {
        return true;
    }
}
