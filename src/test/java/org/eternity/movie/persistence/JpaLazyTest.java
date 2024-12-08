package org.eternity.movie.persistence;

import jakarta.persistence.EntityManager;
import org.eternity.movie.generic.Money;
import org.eternity.movie.reservation.domain.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest(showSql = false)
public class JpaLazyTest {
	@Autowired
	private EntityManager em;

	@Test
	public void add_discount_condition() {
		DiscountPolicy policy =
				new PercentDiscountPolicy(0.1,
					Set.of(new SequenceCondition(1)),
					Set.of(Money.wons(1000)));
		em.persist(policy);
		em.flush();
		em.clear();

		DiscountPolicy loadedPolicy = em.find(DiscountPolicy.class, policy.getId());
		em.flush();
	}
}
