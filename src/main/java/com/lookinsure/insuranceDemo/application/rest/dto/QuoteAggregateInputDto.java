package com.lookinsure.insuranceDemo.application.rest.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuoteAggregateInputDto {
    private String sortBy="price";//default value
    private Integer size=10;//default value
    private Integer page=0;//default value
}
