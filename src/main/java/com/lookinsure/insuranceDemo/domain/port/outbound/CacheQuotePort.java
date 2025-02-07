package com.lookinsure.insuranceDemo.domain.port.outbound;

import com.lookinsure.insuranceDemo.domain.port.value.AggregateRequestValue;
import com.lookinsure.insuranceDemo.domain.port.value.AggregateResponseValue;

public interface CacheQuotePort {
    AggregateResponseValue aggregate(AggregateRequestValue aggregateRequestValue);
    void clearAggregateCache();
}
