package com.obi.bigpanda.Repository;

import com.obi.bigpanda.Entity.NationalParksEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NationalParksRepository extends JpaRepository<NationalParksEntity, Long> {
}