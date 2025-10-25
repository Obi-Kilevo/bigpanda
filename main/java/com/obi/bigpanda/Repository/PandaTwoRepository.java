package com.obi.bigpanda.Repository;

import com.obi.bigpanda.Entity.PandaTwoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PandaTwoRepository extends JpaRepository<PandaTwoEntity, Integer> {
}
