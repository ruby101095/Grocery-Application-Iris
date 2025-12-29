package com.iris.grocery_app;

import java.math.BigDecimal;

public class DiscountSummary
{
    private BigDecimal itemDiscount;
    private BigDecimal cartDiscount;

    public BigDecimal totalDiscount()
    {
        return itemDiscount.add(cartDiscount);
    }
}
