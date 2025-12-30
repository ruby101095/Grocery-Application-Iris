package com.iris.grocery_app.controller;

import com.iris.grocery_app.entity.CartItem;
import com.iris.grocery_app.entity.Receipt;
import com.iris.grocery_app.service.Impl.CheckoutServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;


@ExtendWith(MockitoExtension.class)
class CheckoutControllerTest {

    @Mock
    private CheckoutServiceImpl checkoutServiceImpl;

    @InjectMocks
    private CheckoutController checkoutController;

    @Test
    void shouldReturnReceipt_whenCheckoutIsCalled()
    {

        List<CartItem> cart = List.of(
                new CartItem("BANANA", 3),
                new CartItem("ORANGE", 4)
        );

        Receipt expectedReceipt = new Receipt(
                List.of(),
                BigDecimal.valueOf(3.00),
                BigDecimal.valueOf(0.95),
                BigDecimal.valueOf(2.05)
        );

        Mockito.when(checkoutServiceImpl.checkout(cart))
                .thenReturn(expectedReceipt);

        Receipt actualReceipt = checkoutController.checkout(cart);

        Assertions.assertNotNull(actualReceipt);
        Assertions.assertEquals(BigDecimal.valueOf(3.00), actualReceipt.getSubtotal());
        Assertions.assertEquals(BigDecimal.valueOf(0.95), actualReceipt.getDiscount());
        Assertions.assertEquals(BigDecimal.valueOf(2.05), actualReceipt.getTotal());

        Mockito.verify(checkoutServiceImpl).checkout(cart);
    }

    @Test
    void shouldReturnFullReceipt_withDummyData()
    {
        List<CartItem> cart = List.of(
                new CartItem("BANANA", 3),
                new CartItem("ORANGE", 4),
                new CartItem("APPLE", 1)
        );

        Receipt dummyReceipt = new Receipt(
                List.of(),
                BigDecimal.valueOf(3.30),
                BigDecimal.valueOf(0.65),
                BigDecimal.valueOf(2.65)
        );

        Mockito.when(checkoutServiceImpl.checkout(Mockito.anyList()))
                .thenReturn(dummyReceipt);

        Receipt response = checkoutController.checkout(cart);

        Assertions.assertNotNull(response);
        Assertions.assertEquals(BigDecimal.valueOf(3.30), response.getSubtotal());
        Assertions.assertEquals(BigDecimal.valueOf(0.65), response.getDiscount());
        Assertions.assertEquals(BigDecimal.valueOf(2.65), response.getTotal());

        Mockito.verify(checkoutServiceImpl, Mockito.times(1))
                .checkout(cart);
    }

}