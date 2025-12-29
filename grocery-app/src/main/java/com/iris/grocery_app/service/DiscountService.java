package com.iris.grocery_app.service;

import com.iris.grocery_app.entity.OrderItem;

import java.math.BigDecimal;
import java.util.List;

public interface DiscountService {

    BigDecimal calculateItemLevelDiscount(List<OrderItem> items);

    BigDecimal calculateCartLevelDiscount(BigDecimal subtotal);
}
