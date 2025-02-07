package com.lookinsure.insuranceDemo.domain.port.value;

public record QuoteValue(InsuranceProviderValue insuranceProviderValue,
                         Long id,
                         Long price,
                         String coverageType,
                         Long policyLimit) {
}
