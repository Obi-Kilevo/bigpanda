package com.obi.bigpanda.Repository;

import com.obi.bigpanda.Entity.ParksEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParksRepository extends JpaRepository<ParksEntity, Long> {

    long countByParkId(Long parkId);
}
