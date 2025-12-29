package com.iris.grocery_app.serviceImpl;

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

@Service
@RequiredArgsConstructor
public class CheckoutServiceImpl implements CheckoutService {

    private final PricingService pricingService;
    private final InventoryServiceImpl inventoryService;
    private final DiscountService discountService;
    private final ReceiptService receiptService;

    @Override
    public Receipt checkout(List<CartItem> cart)
    {

        List<OrderItem> items =
                pricingService.priceItems(cart, inventoryService);

        BigDecimal subtotal =
                receiptService.calculateSubtotal(items);

        BigDecimal itemDiscount =
                discountService.calculateItemLevelDiscount(items);

        BigDecimal cartDiscount =
                discountService.calculateCartLevelDiscount(subtotal);

        BigDecimal totalDiscount = itemDiscount.add(cartDiscount);

        return receiptService.generate(items, subtotal, totalDiscount);
    }
}