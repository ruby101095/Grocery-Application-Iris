package com.iris.grocery_app.service;


import com.iris.grocery_app.entity.CartItem;
import com.iris.grocery_app.entity.Discount;
import com.iris.grocery_app.entity.PriceSnapshot;
import com.iris.grocery_app.entity.Receipt;
import com.iris.grocery_app.portPricing.DiscountPort;
import com.iris.grocery_app.portPricing.PricingPort;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@ExtendWith(MockitoExtension.class)
class CheckoutServiceTest {

    @Mock
    private PricingPort pricingPort;

    @Mock
    private DiscountPort discountPort;

    @Mock
    private DiscountEngine discountEngine;

    @Mock
    private InventoryService inventoryService;

    @InjectMocks
    private CheckoutService checkoutService;

    @Test
    void shouldCalculateReceiptCorrectly() {

        CartItem banana = new CartItem("BANANA", 3);
        CartItem orange = new CartItem("ORANGE", 4);

        List<CartItem> cart = List.of(banana, orange);

        Mockito.when(inventoryService.isAvailable(Mockito.anyString(), Mockito.anyInt()))
                .thenReturn(true);

        Mockito.when(pricingPort.getCurrentPrice("BANANA"))
                .thenReturn(new PriceSnapshot("BANANA", "v1", BigDecimal.valueOf(0.50)));

        Mockito.when(pricingPort.getCurrentPrice("ORANGE"))
                .thenReturn(new PriceSnapshot("ORANGE", "v1", BigDecimal.valueOf(0.30)));

        Mockito.when(discountPort.getDiscount(Mockito.anyString()))
                .thenReturn(new Discount(
                        "DISC",
                        "BANANA",
                        "0",
                        Map.of()
                ));

        Mockito.when(discountEngine.calculate(
                        Mockito.any(), Mockito.anyInt(), Mockito.any()))
                .thenReturn(BigDecimal.ZERO);

        Receipt receipt = checkoutService.checkout(cart);

        Assertions.assertEquals(BigDecimal.valueOf(2.70), receipt.getSubtotal());
        Assertions.assertEquals(BigDecimal.ZERO, receipt.getDiscount());
        Assertions.assertEquals(BigDecimal.valueOf(2.70), receipt.getTotal());

        Mockito.verify(pricingPort, Mockito.times(2))
                .getCurrentPrice(Mockito.anyString());

        Mockito.verify(discountEngine, Mockito.times(2))
                .calculate(Mockito.any(), Mockito.anyInt(), Mockito.any());
    }
}
