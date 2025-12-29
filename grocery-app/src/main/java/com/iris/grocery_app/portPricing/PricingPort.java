package com.iris.grocery_app.portPricing;

import com.iris.grocery_app.entity.PriceSnapshot;

public interface PricingPort
{
    PriceSnapshot getCurrentPrice(String productCode);
}

