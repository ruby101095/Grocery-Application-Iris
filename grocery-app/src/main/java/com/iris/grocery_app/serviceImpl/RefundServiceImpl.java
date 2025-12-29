package com.iris.grocery_app.serviceImpl;

import com.iris.grocery_app.entity.OrderItem;
import com.iris.grocery_app.service.RefundService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class RefundServiceImpl implements RefundService
{
    public BigDecimal refund(OrderItem item)
    {
        return item.getPriceSnapshot()
                .getPriceAtPurchase()
                .multiply(BigDecimal.valueOf(item.getQuantity()));
    }
}

