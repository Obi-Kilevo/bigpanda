package com.obi.bigpanda.Repository;

import com.obi.bigpanda.Entity.PandaOneEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PandaOneRepository extends JpaRepository<PandaOneEntity, Integer> {
}
