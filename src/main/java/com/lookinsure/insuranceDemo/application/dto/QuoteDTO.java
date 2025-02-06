package com.lookinsure.insuranceDemo.application.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuoteDTO {
    private Long id;
    private Long price;
    private String coverageType;
    private Long policyLimit;
}
