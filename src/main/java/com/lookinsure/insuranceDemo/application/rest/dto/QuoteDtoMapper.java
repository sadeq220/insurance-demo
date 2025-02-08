package com.lookinsure.insuranceDemo.application.rest.dto;

import com.lookinsure.insuranceDemo.domain.port.value.AggregateRequestValue;
import com.lookinsure.insuranceDemo.domain.port.value.AggregateResponseValue;
import com.lookinsure.insuranceDemo.domain.port.value.QuoteValue;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface QuoteDtoMapper {
    AggregateRequestValue toValue(QuoteAggregateInputDto quoteAggregateInputDto);

    @Mapping(target = "quotes", source = "quoteValues")
    QuoteAggregateOutputDto toDto(AggregateResponseValue aggregateResponseValue);

    @Mapping(target = "insuranceProvider", source = "insuranceProviderValue")
    QuoteDTO toDto(QuoteValue quoteValue);
}
