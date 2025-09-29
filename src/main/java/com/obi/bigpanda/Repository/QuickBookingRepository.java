package com.obi.bigpanda.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.obi.bigpanda.Entity.QuickBookingEntity;

@Repository
public interface QuickBookingRepository extends JpaRepository<QuickBookingEntity, Long> {
}
