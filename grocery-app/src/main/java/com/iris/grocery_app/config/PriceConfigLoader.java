package com.iris.grocery_app.config;

import com.iris.grocery_app.dto.PriceConfigRoot;
import com.iris.grocery_app.dto.PriceVersionConfig;
import com.iris.grocery_app.dto.ProductPriceConfig;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class PriceConfigLoader
{

    private final Map<String, List<PriceVersionConfig>> priceMap;

    public PriceConfigLoader(ObjectMapper mapper) throws IOException
    {
        InputStream is = new ClassPathResource("prices.json").getInputStream();
        PriceConfigRoot root = mapper.readValue(is, PriceConfigRoot.class);

        this.priceMap = root.getProducts().stream()
                .collect(Collectors.toMap(
                        ProductPriceConfig::getProductCode,
                        ProductPriceConfig::getPriceVersions
                ));
    }

    public List<PriceVersionConfig> getVersions(String productCode) {
        return priceMap.get(productCode);
    }
}

