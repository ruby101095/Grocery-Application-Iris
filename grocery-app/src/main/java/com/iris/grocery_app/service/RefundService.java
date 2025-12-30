package com.iris.grocery_app.service;

import com.iris.grocery_app.entity.OrderItem;

import java.math.BigDecimal;

public interface RefundService
{
     BigDecimal refund(OrderItem item);
}
