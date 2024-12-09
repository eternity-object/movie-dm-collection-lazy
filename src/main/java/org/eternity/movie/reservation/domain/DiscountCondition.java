package org.eternity.movie.reservation.domain;

import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "condition_type")
public abstract class DiscountCondition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="POLICY_ID")
    private DiscountPolicy policy;

    public void setDiscountPolicy(DiscountPolicy policy) {
        this.policy = policy;
    }
}
