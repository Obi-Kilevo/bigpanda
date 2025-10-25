package com.obi.bigpanda.Repository;

import com.obi.bigpanda.Entity.ParkLikesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParkLikesRepository extends JpaRepository<ParkLikesEntity, Long> {

    long countByParkId(Long parkId);


    @Query("SELECT pl.parkId, COUNT(pl) FROM ParkLikesEntity pl GROUP BY pl.parkId")
    List<Object[]> getLikeCountsByPark();




    // Optional: Count likes from UNREGISTERED users that are INACTIVE
    long countByParkIdAndCustomerIdIsNullAndActiveFalse(Long parkId);

    // Count likes from UNREGISTERED users only (where customer_id is null)
    long countByParkIdAndCustomerIdIsNull(Long parkId);

    long countByParkIdAndCustomerIdIsNullAndLikedTrue(Long parkId);
    long countByParkIdAndCustomerIdIsNullAndLikedFalse(Long parkId);



    // Add to ParkLikesRepository
    ParkLikesEntity findByParkIdAndGuestSessionId(Long parkId, String guestSessionId);



//registered customers only
    ParkLikesEntity findByCustomerIdAndParkId(Long customerId, Long parkId);

    Long countByCustomerIdNotNullAndLikedTrue();
    Long countByCustomerIdNotNullAndLikedFalse();

    Long countByParkIdAndCustomerIdNotNullAndLikedTrue(Long parkId);
    Long countByParkIdAndCustomerIdNotNullAndLikedFalse(Long parkId);

}
