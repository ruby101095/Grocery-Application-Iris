package com.iris.grocery_app.controller;

import com.iris.grocery_app.entity.CartItem;
import com.iris.grocery_app.entity.Receipt;
import com.iris.grocery_app.service.Impl.CheckoutServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/checkout")
@RequiredArgsConstructor
public class CheckoutController
{
    private final CheckoutServiceImpl checkoutServiceImpl;

    @PostMapping
    public Receipt checkout(@RequestBody List<CartItem> cart)
    {
        return checkoutServiceImpl.checkout(cart);
    }
}
