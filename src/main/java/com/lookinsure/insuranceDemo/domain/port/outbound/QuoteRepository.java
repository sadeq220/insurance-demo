package com.lookinsure.insuranceDemo.domain.port.outbound;

import com.lookinsure.insuranceDemo.domain.model.QuoteDomain;
import com.lookinsure.insuranceDemo.domain.port.value.AggregateRequestValue;
import com.lookinsure.insuranceDemo.domain.port.value.QuoteAggregateRepositoryResponseValue;

import java.util.List;
import java.util.Optional;

public interface QuoteRepository {
    QuoteAggregateRepositoryResponseValue aggregate(AggregateRequestValue aggregateRequestValue);

    QuoteDomain saveQuote(QuoteDomain quoteDomain);

    Optional<QuoteDomain> getQuote(Long id);

    void removeQuote(QuoteDomain quoteDomain);

    List<QuoteDomain> listAllQuotes();
}
