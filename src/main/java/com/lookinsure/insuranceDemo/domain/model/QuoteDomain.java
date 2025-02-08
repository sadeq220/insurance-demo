package com.lookinsure.insuranceDemo.domain.model;

import com.lookinsure.insuranceDemo.domain.port.value.AddQuoteValue;
import com.lookinsure.insuranceDemo.domain.service.ex.AddQuoteRequestNotValidException;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "quote")
@Table(name = "QUOTE")
@Getter
@Setter
public class QuoteDomain {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long price;
    @Enumerated(EnumType.STRING)
    private CoverageType coverageType;
    private Long policyLimit;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PROVIDER_ID")
    private InsuranceProviderDomain insuranceProvider;

    @Override
    public int hashCode() {
        return 1; // to ensure consistent hash code between entity state transitions, because id is null when entity is in transient state
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof QuoteDomain)) {
            return false;
        }
        QuoteDomain quoteDomain = (QuoteDomain) obj;
        return this.id != null && this.id.equals(quoteDomain.id);
    }
    public static QuoteDomain create(AddQuoteValue addQuoteValue){
        String coverageTypeString = addQuoteValue.coverageType();
        try {
            CoverageType coverageType = CoverageType.valueOf(coverageTypeString);
            QuoteDomain quoteDomain = new QuoteDomain();
            quoteDomain.coverageType=coverageType;
            quoteDomain.price= addQuoteValue.price();
            quoteDomain.policyLimit= addQuoteValue.policyLimit();
            return quoteDomain;
        } catch (IllegalArgumentException ex){
            throw new AddQuoteRequestNotValidException("ADD_QUOTE_COVERAGE_INVALID");
        }

    }
}
