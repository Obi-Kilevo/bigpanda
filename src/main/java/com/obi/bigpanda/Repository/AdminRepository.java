package com.obi.bigpanda.Repository;

import com.obi.bigpanda.Entity.AdminEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AdminRepository extends JpaRepository<AdminEntity, Long> {


    @Transactional
    @Modifying
    @Query("UPDATE AdminEntity SET deleted = true WHERE id = :id")
    void softDeleteById(Long id);


    @Query("SELECT a FROM AdminEntity a WHERE a.deleted = false")
    List<AdminEntity> findAllActiveTours();



    @Transactional
    @Modifying
    @Query("UPDATE AdminEntity SET deleted = false WHERE id = :id")
    void restoreById(@Param("id") Long id);



//    viewwing deleted ones

    @Query("SELECT a FROM AdminEntity a WHERE a.deleted = true")
    List<AdminEntity> findAllDeletedTours();
}