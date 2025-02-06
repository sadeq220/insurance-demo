package com.lookinsure.insuranceDemo.domain.port.inbound;

import java.util.List;

public record AggregateResponseValue(List<QuoteValue> quoteValues,
                                     Integer size,
                                     Integer page,
                                     String sortedBy) {
}
