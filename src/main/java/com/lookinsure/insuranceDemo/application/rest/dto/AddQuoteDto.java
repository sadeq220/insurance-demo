package com.lookinsure.insuranceDemo.application.rest.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddQuoteDto {
    private Long providerId;
    private Long price;
    private String coverageType;
    private Long policyLimit;
}
