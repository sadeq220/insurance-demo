package com.lookinsure.insuranceDemo.domain.port.inbound;

import com.lookinsure.insuranceDemo.domain.port.value.AddQuoteValue;
import com.lookinsure.insuranceDemo.domain.port.value.AggregateRequestValue;
import com.lookinsure.insuranceDemo.domain.port.value.AggregateResponseValue;
import com.lookinsure.insuranceDemo.domain.port.value.QuoteValue;

public interface QuotePort {
    AggregateResponseValue aggregate(AggregateRequestValue aggregateRequestValue);
    QuoteValue addQuote(AddQuoteValue addQuoteValue);
}
