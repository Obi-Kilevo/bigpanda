package com.obi.bigpanda.Repository;

import com.obi.bigpanda.Entity.BookingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface BookingRepository extends JpaRepository<BookingEntity, Long> {

//    @Query(value = "SELECT b.*, t.name as tour_name, t.price as tour_price " +
//            "FROM bookings b JOIN admin_tours_entity t ON b.tour_id = t.id",
//            nativeQuery = true)
//    List<Object[]> findAllBookingsWithTourInfo();

    @Query(value = "SELECT b.*, t.name as tour_name, t.price as tour_price " +
            "FROM bookings b JOIN admin_tours_entity t ON b.tour_id = t.id " +
            "WHERE t.deleted = false",  // ← This line is CRITICAL
            nativeQuery = true)
    List<Object[]> findAllBookingsWithTourInfo();
}