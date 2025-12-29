package com.iris.grocery_app.service;

import com.iris.grocery_app.entity.OrderItem;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class RefundService {

    public BigDecimal refund(OrderItem item)
    {
        return item.getPriceSnapshot()
                .getPriceAtPurchase()
                .multiply(BigDecimal.valueOf(item.getQuantity()));
    }
}

