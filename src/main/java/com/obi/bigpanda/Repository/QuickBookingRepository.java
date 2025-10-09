package com.obi.bigpanda.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.obi.bigpanda.Entity.QuickBookingEntity;

import java.util.List;

@Repository
public interface QuickBookingRepository extends JpaRepository<QuickBookingEntity, Long> {






    // In your QuickBookingRepository
//    @Query("SELECT q FROM QuickBookingEntity q JOIN FETCH q.customer")
//    List<QuickBookingEntity> findAllWithCustomer();

    @Query("SELECT q FROM QuickBookingEntity q LEFT JOIN FETCH q.customer")

    List<QuickBookingEntity> findAllWithCustomer();
}
