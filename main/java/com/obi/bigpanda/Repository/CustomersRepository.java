package com.obi.bigpanda.Repository;

import com.obi.bigpanda.Entity.CustomersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomersRepository extends JpaRepository<CustomersEntity, Long> {
    CustomersEntity findByNickname(String nickname);


//    public interface CustomersRepository extends JpaRepository<CustomersEntity, Long> {
        boolean existsByNickname(String nickname);



    boolean existsByPasswordHash(String passwordHash);
    }


