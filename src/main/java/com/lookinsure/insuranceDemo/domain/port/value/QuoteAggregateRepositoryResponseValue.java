package com.lookinsure.insuranceDemo.domain.port.value;

import com.lookinsure.insuranceDemo.domain.model.QuoteDomain;

import java.util.List;

public record QuoteAggregateRepositoryResponseValue(List<QuoteDomain> quoteDomains,
                                                    Integer size,
                                                    Integer page,
                                                    String sortedBy) {
}
