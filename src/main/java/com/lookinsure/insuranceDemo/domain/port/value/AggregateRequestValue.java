package com.lookinsure.insuranceDemo.domain.port.value;

public record AggregateRequestValue(String sortBy,
                                    Integer size,
                                    Integer page) {
}
