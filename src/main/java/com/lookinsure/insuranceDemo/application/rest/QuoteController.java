package com.lookinsure.insuranceDemo.application.rest;

import com.lookinsure.insuranceDemo.application.rest.dto.QuoteAggregateInputDto;
import com.lookinsure.insuranceDemo.application.rest.dto.QuoteAggregateOutputDto;
import com.lookinsure.insuranceDemo.application.rest.dto.QuoteDtoMapper;
import com.lookinsure.insuranceDemo.domain.port.inbound.QuotePort;
import com.lookinsure.insuranceDemo.domain.port.outbound.CacheQuotePort;
import com.lookinsure.insuranceDemo.domain.port.value.AggregateRequestValue;
import com.lookinsure.insuranceDemo.domain.port.value.AggregateResponseValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * restful API adapter
 */
@RestController
@RequestMapping("/v1/quotes")
public class QuoteController {
    private final CacheQuotePort cacheQuotePort;
    private final QuotePort quotePort;
    private final QuoteDtoMapper quoteDtoMapper;

    public QuoteController(CacheQuotePort cacheQuotePort, QuotePort quotePort, QuoteDtoMapper quoteDtoMapper) {
        this.cacheQuotePort = cacheQuotePort;
        this.quotePort = quotePort;
        this.quoteDtoMapper = quoteDtoMapper;
    }

    @GetMapping("aggregate")
    public QuoteAggregateOutputDto aggregateQuotes(@RequestBody QuoteAggregateInputDto quoteAggregateInputDto){
        AggregateRequestValue aggregateRequestValue = quoteDtoMapper.toValue(quoteAggregateInputDto);
        AggregateResponseValue aggregateResponse = cacheQuotePort.aggregate(aggregateRequestValue);
        return quoteDtoMapper.toDto(aggregateResponse);
    }
}
