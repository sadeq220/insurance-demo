package com.lookinsure.insuranceDemo.domain.port.value;

public record UpdateQuoteValue(Long price,
                               String coverageType,
                               Long policyLimit) {
}
