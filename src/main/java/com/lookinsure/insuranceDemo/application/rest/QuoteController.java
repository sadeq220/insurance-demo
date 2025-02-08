package com.lookinsure.insuranceDemo.application.rest;

import com.lookinsure.insuranceDemo.application.rest.dto.*;
import com.lookinsure.insuranceDemo.domain.port.inbound.QuotePort;
import com.lookinsure.insuranceDemo.domain.port.outbound.CacheQuotePort;
import com.lookinsure.insuranceDemo.domain.port.value.AddQuoteValue;
import com.lookinsure.insuranceDemo.domain.port.value.AggregateRequestValue;
import com.lookinsure.insuranceDemo.domain.port.value.AggregateResponseValue;
import com.lookinsure.insuranceDemo.domain.port.value.QuoteValue;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    public QuoteDTO createQuote(@RequestBody AddQuoteDto addQuoteDto){
        AddQuoteValue addQuoteValue = quoteDtoMapper.toValue(addQuoteDto);
        QuoteValue quoteValue = quotePort.addQuote(addQuoteValue);
        return quoteDtoMapper.toDto(quoteValue);
    }
    @GetMapping("/{id}")
    public QuoteDTO readQuote(@PathVariable("id") Long id) {
        QuoteValue quoteValue = quotePort.getQuote(id);
        return quoteDtoMapper.toDto(quoteValue);
    }
}
