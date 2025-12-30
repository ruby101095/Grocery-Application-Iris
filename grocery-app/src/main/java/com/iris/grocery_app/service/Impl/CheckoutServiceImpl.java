package com.iris.grocery_app.service.Impl;

import com.iris.grocery_app.entity.CartItem;
import com.iris.grocery_app.entity.OrderItem;
import com.iris.grocery_app.entity.Receipt;
import com.iris.grocery_app.service.CheckoutService;
import com.iris.grocery_app.service.DiscountService;
import com.iris.grocery_app.service.PricingService;
import com.iris.grocery_app.service.ReceiptService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

@Service
@RequiredArgsConstructor
public class CheckoutServiceImpl implements CheckoutService
{

    private final PricingService pricingService;
    private final InventoryServiceImpl inventoryService;
    private final DiscountService discountService;
    private final ReceiptService receiptService;
    private final Executor checkoutExecutor;

    @Override
    public Receipt checkout(List<CartItem> cart)
    {
        Executor executor = checkoutExecutor;

        // Price items (blocking dependency)
        CompletableFuture<List<OrderItem>> pricedItemsFuture =
                CompletableFuture.supplyAsync(
                        () -> pricingService.priceItems(cart, inventoryService),
                        executor
                );

        // Subtotal (depends on items)
        CompletableFuture<BigDecimal> subtotalFuture =
                pricedItemsFuture.thenApplyAsync(
                        receiptService::calculateSubtotal,
                        executor
                );

        //  Item-level discount (depends on items)
        CompletableFuture<BigDecimal> itemDiscountFuture =
                pricedItemsFuture.thenApplyAsync(
                        discountService::calculateItemLevelDiscount,
                        executor
                );

        // Cart-level discount (depends on subtotal)
        CompletableFuture<BigDecimal> cartDiscountFuture =
                subtotalFuture.thenApplyAsync(
                        discountService::calculateCartLevelDiscount,
                        executor
                );

        // Combine discounts
        CompletableFuture<BigDecimal> totalDiscountFuture =
                itemDiscountFuture.thenCombine(
                        cartDiscountFuture,
                        BigDecimal::add
                );

        //  Generate receipt
        return pricedItemsFuture
                .thenCombine(subtotalFuture, (items, subtotal) -> new Object[]{items, subtotal})
                .thenCombine(totalDiscountFuture,
                        (data, discount) ->
                                receiptService.generate(
                                        (List<OrderItem>) data[0],
                                        (BigDecimal) data[1],
                                        discount
                                ))
                .join();

    }
}