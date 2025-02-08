package com.lookinsure.insuranceDemo.application.rest.dto;

import com.lookinsure.insuranceDemo.domain.port.value.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface QuoteDtoMapper {
    AggregateRequestValue toValue(QuoteAggregateInputDto quoteAggregateInputDto);

    @Mapping(target = "quotes", source = "quoteValues")
    QuoteAggregateOutputDto toDto(AggregateResponseValue aggregateResponseValue);

    @Mapping(target = "insuranceProvider", source = "insuranceProviderValue")
    QuoteDTO toDto(QuoteValue quoteValue);

    AddQuoteValue toValue(AddQuoteDto addQuoteDto);

    UpdateQuoteValue toValue(UpdateQuoteDto updateQuoteDto);
}
