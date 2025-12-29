package com.iris.grocery_app.service;

import com.iris.grocery_app.entity.OrderItem;
import com.iris.grocery_app.entity.Receipt;

import java.math.BigDecimal;
import java.util.List;

public interface ReceiptService {

    BigDecimal calculateSubtotal(List<OrderItem> items);

    Receipt generate(
            List<OrderItem> items,
            BigDecimal subtotal,
            BigDecimal discount
    );
}
