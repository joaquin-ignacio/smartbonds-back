package com.upc.smartbonds.repository;

import com.upc.smartbonds.entity.Bond;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BondRepository extends JpaRepository<Bond, Long> {
    Page<Bond> findByUserId(Long userId, Pageable pageable);
}
