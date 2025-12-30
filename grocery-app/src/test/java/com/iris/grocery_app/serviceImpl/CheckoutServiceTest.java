package com.iris.grocery_app.serviceImpl;


import com.iris.grocery_app.entity.*;
import com.iris.grocery_app.service.Impl.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CheckoutServiceImplTest {

    @Mock
    private PricingServiceImpl pricingService;

    @Mock
    private InventoryServiceImpl inventoryService;

    @Mock
    private DiscountServiceImpl discountService;

    @Mock
    private ReceiptServiceImpl receiptService;

    @InjectMocks
    private CheckoutServiceImpl checkoutService;

    @Test
    void checkout_success_flow()
    {

        CartItem cartItem = new CartItem("BANANA", 3);
        List<CartItem> cart = List.of(cartItem);

        OrderItem orderItem = new OrderItem(
                new Product("BANANA", "BANANA"),
                3,
                new PriceSnapshot("BANANA", "v1", BigDecimal.valueOf(0.50))
        );

        List<OrderItem> items = List.of(orderItem);

        BigDecimal subtotal = BigDecimal.valueOf(1.50);
        BigDecimal itemDiscount = BigDecimal.valueOf(0.50);
        BigDecimal cartDiscount = BigDecimal.ZERO;
        BigDecimal totalDiscount = BigDecimal.valueOf(0.50);

        Receipt receipt = new Receipt(
                items,
                subtotal,
                totalDiscount,
                BigDecimal.valueOf(1.00)
        );

        when(pricingService.priceItems(cart, inventoryService))
                .thenReturn(items);

        when(receiptService.calculateSubtotal(items))
                .thenReturn(subtotal);

        when(discountService.calculateItemLevelDiscount(items))
                .thenReturn(itemDiscount);

        when(discountService.calculateCartLevelDiscount(subtotal))
                .thenReturn(cartDiscount);

        when(receiptService.generate(items, subtotal, totalDiscount))
                .thenReturn(receipt);

        Receipt result = checkoutService.checkout(cart);

        assertEquals(BigDecimal.valueOf(1.00), result.getTotal());

        verify(pricingService).priceItems(cart, inventoryService);
        verify(receiptService).calculateSubtotal(items);
        verify(discountService).calculateItemLevelDiscount(items);
        verify(discountService).calculateCartLevelDiscount(subtotal);
        verify(receiptService).generate(items, subtotal, totalDiscount);
    }
}