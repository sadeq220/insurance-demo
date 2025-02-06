package com.lookinsure.insuranceDemo.domain.port.inbound;

public record AggregateRequestValue(String sortBy,
                                    Integer size,
                                    Integer page) {
}
