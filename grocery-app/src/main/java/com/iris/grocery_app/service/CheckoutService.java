package com.iris.grocery_app.service;

import com.iris.grocery_app.entity.*;
import com.iris.grocery_app.portPricing.DiscountPort;
import com.iris.grocery_app.portPricing.PricingPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CheckoutService {

    private final PricingPort pricingPort;
    private final DiscountPort discountPort;
    private final DiscountEngine discountEngine;
    private final InventoryService inventoryService;

    public Receipt checkout(List<CartItem> cart)
    {
        List<OrderItem> items = cart.parallelStream()
                .filter(i -> inventoryService.isAvailable(i.getProductCode(), i.getQuantity()))
                .map(i -> {
                    PriceSnapshot snapshot = pricingPort.getCurrentPrice(i.getProductCode());
                    return new OrderItem(
                            new Product(i.getProductCode(), i.getProductCode()),
                            i.getQuantity(),
                            snapshot
                    );
                })
                .toList();

        BigDecimal subtotal = items.stream()
                .map(i -> i.getPriceSnapshot().getPriceAtPurchase()
                        .multiply(BigDecimal.valueOf(i.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal discount = items.stream()
                .map(i -> {
                    Discount d = discountPort.getDiscount(i.getProduct().getCode());
                    return discountEngine.calculate(
                            d,
                            i.getQuantity(),
                            i.getPriceSnapshot().getPriceAtPurchase());
                })
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return new Receipt(items, subtotal, discount, subtotal.subtract(discount));
    }
}
