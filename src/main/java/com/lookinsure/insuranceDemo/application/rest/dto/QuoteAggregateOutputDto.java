package com.lookinsure.insuranceDemo.application.rest.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class QuoteAggregateOutputDto {
    private List<QuoteDTO> quotes;
    private Integer size;
    private Integer page;
    private String sortedBy;
}
