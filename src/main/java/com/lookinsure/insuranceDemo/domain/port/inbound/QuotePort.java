package com.lookinsure.insuranceDemo.domain.port.inbound;

import com.lookinsure.insuranceDemo.application.dto.QuoteAggregateOutputDto;

public interface QuotePort {
    AggregateResponseValue aggregate(AggregateRequestValue aggregateRequestValue);
}
