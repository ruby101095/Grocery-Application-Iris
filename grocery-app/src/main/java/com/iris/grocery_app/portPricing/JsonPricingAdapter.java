package com.iris.grocery_app.portPricing;

import com.iris.grocery_app.config.PriceConfigLoader;
import com.iris.grocery_app.dto.PriceVersionConfig;
import com.iris.grocery_app.entity.PriceSnapshot;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JsonPricingAdapter implements PricingPort
{
    private final PriceConfigLoader loader;

    @Override
    public PriceSnapshot getCurrentPrice(String productCode)
    {

        List<PriceVersionConfig> versions = loader.getVersions(productCode);

        PriceVersionConfig latest = versions.stream()
                .filter(pv -> !pv.getEffectiveFrom().isAfter(LocalDateTime.now()))
                .max(Comparator.comparing(PriceVersionConfig::getEffectiveFrom))
                .orElseThrow(() -> new RuntimeException("Price not found"));

        return new PriceSnapshot(
                productCode,
                latest.getVersion(),
                latest.getPrice()
        );
    }
}

