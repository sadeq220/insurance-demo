package com.lookinsure.insuranceDemo.domain.model;

import com.lookinsure.insuranceDemo.domain.model.ex.QuoteCoverageNotValidException;
import com.lookinsure.insuranceDemo.domain.model.ex.QuotePolicyLimitNotValidException;
import com.lookinsure.insuranceDemo.domain.model.ex.QuotePriceNotValidException;
import com.lookinsure.insuranceDemo.domain.port.value.AddQuoteValue;
import com.lookinsure.insuranceDemo.domain.port.value.UpdateQuoteValue;
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

    public void setPrice(Long price) {
        if (price < 1) {
            throw new QuotePriceNotValidException();
        }
        this.price = price;
    }

    public void setPolicyLimit(Long policyLimit) {
        if (policyLimit < 1) {
            throw new QuotePolicyLimitNotValidException();
        }
        this.policyLimit = policyLimit;
    }

    public void update(UpdateQuoteValue updateQuoteValue){
        String coverageTypeString = updateQuoteValue.coverageType();
        try {
            CoverageType coverageType = CoverageType.valueOf(coverageTypeString);
            this.setPolicyLimit(updateQuoteValue.policyLimit());
            this.setPrice(updateQuoteValue.price());
            this.setCoverageType(coverageType);
        }catch (IllegalArgumentException ex){
            throw new QuoteCoverageNotValidException();
        }
    }

    public static QuoteDomain create(AddQuoteValue addQuoteValue){
        String coverageTypeString = addQuoteValue.coverageType();
        try {
            CoverageType coverageType = CoverageType.valueOf(coverageTypeString);
            QuoteDomain quoteDomain = new QuoteDomain();
            quoteDomain.setCoverageType(coverageType);
            quoteDomain.setPrice( addQuoteValue.price());
            quoteDomain.setPolicyLimit(addQuoteValue.policyLimit());
            return quoteDomain;
        } catch (IllegalArgumentException ex){
            throw new QuoteCoverageNotValidException();
        }

    }
}
