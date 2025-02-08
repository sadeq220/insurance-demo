package com.lookinsure.insuranceDemo.application.rest.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuoteDTO {
    private InsuranceProviderDto insuranceProvider;
    private Long id;
    private Long price;
    private String coverageType;
    private Long policyLimit;
}
