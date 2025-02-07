package com.lookinsure.insuranceDemo.domain.service.mapper;

import com.lookinsure.insuranceDemo.domain.model.QuoteDomain;
import com.lookinsure.insuranceDemo.domain.port.value.QuoteValue;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface QuoteDomainMapper {
    QuoteValue toValue(QuoteDomain quoteDomain);
}
