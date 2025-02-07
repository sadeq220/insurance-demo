package com.lookinsure.insuranceDemo.domain.service;

import com.lookinsure.insuranceDemo.domain.port.value.AggregateRequestValue;
import com.lookinsure.insuranceDemo.domain.port.value.AggregateResponseValue;
import com.lookinsure.insuranceDemo.domain.port.inbound.QuotePort;
import com.lookinsure.insuranceDemo.domain.port.outbound.QuoteRepository;
import com.lookinsure.insuranceDemo.domain.port.value.QuoteAggregateRepositoryResponseValue;
import com.lookinsure.insuranceDemo.domain.port.value.QuoteValue;
import com.lookinsure.insuranceDemo.domain.service.ex.AggregateRequestNotValidException;
import com.lookinsure.insuranceDemo.domain.service.mapper.QuoteDomainMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class QuoteService implements QuotePort {
    private final QuoteRepository quoteRepository;
    private final QuoteDomainMapper quoteDomainMapper;

    public QuoteService(QuoteRepository quoteRepository, QuoteDomainMapper quoteDomainMapper) {
        this.quoteRepository = quoteRepository;
        this.quoteDomainMapper = quoteDomainMapper;
    }

    @Transactional
    @Override
    public AggregateResponseValue aggregate(AggregateRequestValue aggregateRequestValue) {
        validateAggregateRequest(aggregateRequestValue);
        QuoteAggregateRepositoryResponseValue aggregateResponse = quoteRepository.aggregate(aggregateRequestValue);
        List<QuoteValue> quoteValues = aggregateResponse.quoteDomains().stream().map(quoteDomain -> quoteDomainMapper.toValue(quoteDomain)).toList();
        return new AggregateResponseValue(quoteValues,
                aggregateResponse.size(),
                aggregateResponse.page(),
                aggregateResponse.sortedBy());
    }

    /**
     * check business rules
     */
    private void validateAggregateRequest(AggregateRequestValue aggregateRequestValue) {
        try {
            SortBy.valueOf(aggregateRequestValue.sortBy().toUpperCase());
        } catch (IllegalArgumentException ex) {
            throw new AggregateRequestNotValidException("QUOTE_AGGREGATE_SORT_INVALID");
        }
        if (aggregateRequestValue.size() < 1) {
            throw new AggregateRequestNotValidException("QUOTE_AGGREGATE_SIZE_INVALID");
        }
        if (aggregateRequestValue.page() < 0) {
            throw new AggregateRequestNotValidException("QUOTE_AGGREGATE_PAGE_INVALID");
        }
    }

    /**
     * accepted sort params
     */
    private enum SortBy{
        PRICE,LIMIT_PRICE
    }
}
