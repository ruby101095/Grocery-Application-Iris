package com.iris.grocery_app.service;

import com.iris.grocery_app.entity.CartItem;
import com.iris.grocery_app.entity.Receipt;

import java.util.List;

public interface CheckoutService
{
    Receipt checkout(List<CartItem> cart);
}
