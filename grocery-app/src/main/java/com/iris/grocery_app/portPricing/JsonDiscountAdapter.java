package com.iris.grocery_app.portPricing;

import com.iris.grocery_app.config.DiscountConfigLoader;
import com.iris.grocery_app.entity.Discount;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class JsonDiscountAdapter implements DiscountPort
{
    private final DiscountConfigLoader loader;

    @Override
    public List<Discount> getItemDiscounts(String productCode)
    {
        return loader.getItemDiscounts(productCode);
    }

    @Override
    public List<Discount> getCartDiscounts() {
        return loader.getCartDiscounts();
    }
}


