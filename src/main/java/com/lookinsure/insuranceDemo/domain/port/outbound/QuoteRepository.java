package com.lookinsure.insuranceDemo.domain.port.outbound;

import com.lookinsure.insuranceDemo.domain.model.QuoteDomain;
import com.lookinsure.insuranceDemo.domain.port.value.AggregateRequestValue;
import com.lookinsure.insuranceDemo.domain.port.value.QuoteAggregateRepositoryResponseValue;

public interface QuoteRepository {
    QuoteAggregateRepositoryResponseValue aggregate(AggregateRequestValue aggregateRequestValue);

    QuoteDomain saveQuote(QuoteDomain quoteDomain);
}
