package com.lookinsure.insuranceDemo.domain.port.inbound;

public record QuoteValue(Long id,
                         Long price,
                         String coverageType,
                         Long policyLimit) {
}
