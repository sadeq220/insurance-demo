package com.lookinsure.insuranceDemo.domain.port.inbound.value;

public record AggregateRequestValue(String sortBy,
                                    Integer size,
                                    Integer page) {
}
