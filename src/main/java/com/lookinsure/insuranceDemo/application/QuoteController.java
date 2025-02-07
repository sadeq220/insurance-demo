package com.lookinsure.insuranceDemo.application;

import com.lookinsure.insuranceDemo.application.dto.QuoteAggregateInputDto;
import com.lookinsure.insuranceDemo.application.dto.QuoteAggregateOutputDto;
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
    @GetMapping("aggregate")
    public QuoteAggregateOutputDto aggregateQuotes(@RequestBody QuoteAggregateInputDto quoteAggregateInputDto){
        QuoteAggregateOutputDto quoteAggregateOutputDto = new QuoteAggregateOutputDto();
        return quoteAggregateOutputDto;
    }
}
