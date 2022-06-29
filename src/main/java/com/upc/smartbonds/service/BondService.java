package com.upc.smartbonds.service;

import com.upc.smartbonds.entity.Bond;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface BondService {
    Page<Bond> getAllBondsByUserId(Long userId, Pageable pageable);

    Bond createBond(Long userId, Bond bond);

    Bond updateBond(Long userId, Long bondId, Bond bondRequest);

    ResponseEntity<?> deleteBond(Long userId, Long bondId);
}
