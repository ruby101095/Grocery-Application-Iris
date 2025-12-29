package com.iris.grocery_app.serviceImpl;

import com.iris.grocery_app.entity.OrderItem;
import com.iris.grocery_app.entity.Receipt;
import com.iris.grocery_app.service.ReceiptService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ReceiptServiceImpl implements ReceiptService
{

    @Override
    public BigDecimal calculateSubtotal(List<OrderItem> items)
    {
        return items.stream()
                .map(i -> i.getPriceSnapshot()
                        .getPriceAtPurchase()
                        .multiply(BigDecimal.valueOf(i.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Override
    public Receipt generate(
            List<OrderItem> items,
            BigDecimal subtotal,
            BigDecimal discount) {

        return new Receipt(
                items,
                subtotal,
                discount,
                subtotal.subtract(discount)
        );
    }
}
