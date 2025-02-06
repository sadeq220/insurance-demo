package com.lookinsure.insuranceDemo.domain.port.inbound;

import com.lookinsure.insuranceDemo.domain.port.inbound.value.AggregateRequestValue;
import com.lookinsure.insuranceDemo.domain.port.inbound.value.AggregateResponseValue;

public interface QuotePort {
    AggregateResponseValue aggregate(AggregateRequestValue aggregateRequestValue);
}
