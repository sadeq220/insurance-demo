package com.lookinsure.insuranceDemo.domain.port.inbound;

import com.lookinsure.insuranceDemo.domain.port.value.*;

public interface QuotePort {
    AggregateResponseValue aggregate(AggregateRequestValue aggregateRequestValue);
    QuoteValue addQuote(AddQuoteValue addQuoteValue);
    QuoteValue getQuote(Long id);
    QuoteValue removeQuote(Long id);
    QuoteValue updateQuote(Long id, UpdateQuoteValue updateQuoteValue);
}
