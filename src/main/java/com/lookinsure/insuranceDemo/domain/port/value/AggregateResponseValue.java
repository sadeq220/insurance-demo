package com.lookinsure.insuranceDemo.domain.port.value;

import java.util.List;

public record AggregateResponseValue(List<QuoteValue> quoteValues,
                                     Integer size,
                                     Integer page,
                                     String sortedBy) {
}
