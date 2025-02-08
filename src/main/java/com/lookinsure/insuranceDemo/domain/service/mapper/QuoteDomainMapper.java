package com.lookinsure.insuranceDemo.domain.service.mapper;

import com.lookinsure.insuranceDemo.domain.model.QuoteDomain;
import com.lookinsure.insuranceDemo.domain.port.value.QuoteValue;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface QuoteDomainMapper {

    @Mapping(target = "insuranceProviderValue",source = "insuranceProvider")
    QuoteValue toValue(QuoteDomain quoteDomain);
}
