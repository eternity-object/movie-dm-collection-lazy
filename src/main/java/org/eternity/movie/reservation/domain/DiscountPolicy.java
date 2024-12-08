package org.eternity.movie.reservation.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.eternity.movie.generic.Money;

import java.util.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="policy_type")
@NoArgsConstructor @Getter
public abstract class DiscountPolicy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="POLICY_ID")
    private Set<DiscountCondition> conditions = new HashSet<>();

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "POLICY_PRICES", joinColumns = @JoinColumn(name="POLICY_ID"))
    private Set<Money> prices = new HashSet<>();

    public DiscountPolicy(Set<DiscountCondition> conditions,
                          Set<Money> prices) {
        this.conditions = conditions;
        this.prices = prices;
    }
}
