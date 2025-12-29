package com.iris.grocery_app.serviceImpl;

import com.iris.grocery_app.entity.CartItem;
import com.iris.grocery_app.entity.OrderItem;
import com.iris.grocery_app.entity.Product;
import com.iris.grocery_app.portPricing.PricingPort;
import com.iris.grocery_app.service.PricingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PricingServiceImpl implements PricingService {

    private final PricingPort pricingPort;

    @Override
    public List<OrderItem> priceItems(List<CartItem> cart, InventoryServiceImpl inventoryService) {

        return cart.stream()
                .filter(i -> inventoryService.isAvailable(i.getProductCode(), i.getQuantity()))
                .map(i -> new OrderItem(
                        new Product(i.getProductCode(), i.getProductCode()),
                        i.getQuantity(),
                        pricingPort.getCurrentPrice(i.getProductCode())
                ))
                .toList();
    }

}
