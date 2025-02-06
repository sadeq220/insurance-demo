package com.lookinsure.insuranceDemo.application.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuoteAggregateInputDto {
    private String sortBy;
    private Integer size;
    private Integer page;
}
