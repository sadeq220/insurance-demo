package com.lookinsure.insuranceDemo.domain.port.inbound.value;

public record QuoteValue(Long id,
                         Long price,
                         String coverageType,
                         Long policyLimit) {
}
