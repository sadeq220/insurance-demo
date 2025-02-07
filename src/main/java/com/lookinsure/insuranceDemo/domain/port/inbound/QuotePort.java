package com.lookinsure.insuranceDemo.domain.port.inbound;

import com.lookinsure.insuranceDemo.domain.port.value.AggregateRequestValue;
import com.lookinsure.insuranceDemo.domain.port.value.AggregateResponseValue;

public interface QuotePort {
    AggregateResponseValue aggregate(AggregateRequestValue aggregateRequestValue);
}
