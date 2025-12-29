package com.iris.grocery_app.controller;

import com.iris.grocery_app.service.CheckoutService;
import com.iris.grocery_app.entity.CartItem;
import com.iris.grocery_app.entity.Receipt;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/checkout")
@RequiredArgsConstructor
public class CheckoutController {

    private final CheckoutService checkoutService;

    @PostMapping
    public Receipt checkout(@RequestBody List<CartItem> cart)
    {
        return checkoutService.checkout(cart);
    }
}
