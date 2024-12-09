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

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "policy")
    private Collection<DiscountCondition> conditions = new ArrayList<>();

    public DiscountPolicy(Set<DiscountCondition> conditions) {
        this.conditions = conditions;
        this.conditions.forEach(condition -> condition.setDiscountPolicy(this));
    }

    public void addDiscountCondition(DiscountCondition condition) {
        this.conditions.add(condition);
        condition.setDiscountPolicy(this);
    }
}
