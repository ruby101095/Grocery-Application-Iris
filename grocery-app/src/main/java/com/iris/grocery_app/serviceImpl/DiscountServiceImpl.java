package com.iris.grocery_app.serviceImpl;

import com.iris.grocery_app.entity.OrderItem;
import com.iris.grocery_app.portPricing.DiscountPort;
import com.iris.grocery_app.service.DiscountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DiscountServiceImpl implements DiscountService {

    private final DiscountPort discountPort;
    private final DiscountEngineImpl discountEngine;

    @Override
    public BigDecimal calculateItemLevelDiscount(List<OrderItem> items) {

        return items.stream()
                .flatMap(item ->
                        discountPort.getItemDiscounts(item.getProduct().getCode())
                                .stream()
                                .map(discount ->
                                        discountEngine.calculateItemLevel(
                                                discount,
                                                item.getQuantity(),
                                                item.getPriceSnapshot().getPriceAtPurchase()
                                        )
                                )
                )
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Override
    public BigDecimal calculateCartLevelDiscount(BigDecimal subtotal) {

        return discountPort.getCartDiscounts()
                .stream()
                .map(discount ->
                        discountEngine.calculateCartLevel(discount, subtotal)
                )
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
