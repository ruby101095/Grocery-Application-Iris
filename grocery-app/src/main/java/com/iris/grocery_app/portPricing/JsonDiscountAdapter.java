package com.iris.grocery_app.portPricing;

import com.iris.grocery_app.config.DiscountConfigLoader;
import com.iris.grocery_app.entity.Discount;
import org.springframework.stereotype.Component;

@Component
public class JsonDiscountAdapter implements DiscountPort {

    private final DiscountConfigLoader loader;

    public JsonDiscountAdapter(DiscountConfigLoader loader) {
        this.loader = loader;
    }

    @Override
    public Discount getDiscount(String productCode) {
        return loader.getDiscount(productCode);
    }
}


