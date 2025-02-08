package com.lookinsure.insuranceDemo.domain.port.value;

public record AddQuoteValue(Long providerId,
                            Long price,
                            String coverageType,
                            Long policyLimit) {
}
