package com.iris.grocery_app.config;

import com.iris.grocery_app.entity.Discount;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class DiscountConfigLoader
{

    private final Map<String, List<Discount>> discountMap;

    public DiscountConfigLoader(ObjectMapper mapper) throws IOException {

        InputStream is = getClass()
                .getClassLoader()
                .getResourceAsStream("discounts.json");

        if (is == null) {
            throw new IllegalStateException("discounts.json not found");
        }

        DiscountConfig config = mapper.readValue(is, DiscountConfig.class);

        this.discountMap = config.getDiscounts()
                .stream()
                .collect(Collectors.groupingBy(
                        d -> d.getProductCode() == null ? "CART" : d.getProductCode()
                ));
    }

    @PostConstruct
    void logLoadedDiscounts() {
        discountMap.forEach((k, v) ->
                System.out.println("Loaded discounts for: " + k + " → " + v.size())
        );
    }

    public List<Discount> getItemDiscounts(String productCode) {
        return discountMap.getOrDefault(productCode, List.of());
    }

    public List<Discount> getCartDiscounts() {
        return discountMap.getOrDefault("CART", List.of());
    }
}
