package com.lookinsure.insuranceDemo.infrastructure.cache;

import com.lookinsure.insuranceDemo.domain.port.inbound.QuotePort;
import com.lookinsure.insuranceDemo.domain.port.outbound.CacheQuotePort;
import com.lookinsure.insuranceDemo.domain.port.value.AggregateRequestValue;
import com.lookinsure.insuranceDemo.domain.port.value.AggregateResponseValue;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

/**
 * spring cache abstraction, adapter
 * Each time a targeted method is invoked, cache abstraction checks for key existence.
 * If that key exists, the cached result is returned without having to invoke the actual method.
 */
@Component
public class CacheQuoteLayer implements CacheQuotePort {
    private final QuotePort quotePort;

    public CacheQuoteLayer(QuotePort quotePort) {
        this.quotePort = quotePort;
    }

    @Cacheable(cacheNames = "quoteAggregate")
    @Override
    public AggregateResponseValue aggregate(AggregateRequestValue aggregateRequestValue) {
        return quotePort.aggregate(aggregateRequestValue);
    }

    @CacheEvict(cacheNames = "quoteAggregate", allEntries = true)
    @Override
    public void clearAggregateCache() {

    }
}
