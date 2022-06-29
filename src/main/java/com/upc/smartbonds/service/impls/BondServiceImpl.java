package com.upc.smartbonds.service.impls;

import com.upc.smartbonds.entity.Bond;
import com.upc.smartbonds.exception.ResourceNotFoundException;
import com.upc.smartbonds.repository.BondRepository;
import com.upc.smartbonds.repository.UserRepository;
import com.upc.smartbonds.service.BondService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class BondServiceImpl implements BondService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BondRepository bondRepository;

    @Override
    public Page<Bond> getAllBondsByUserId(Long userId, Pageable pageable) {
        return bondRepository.findByUserId(userId, pageable);
    }

    @Override
    public Bond createBond(Long userId, Bond bond) {
        return userRepository.findById(userId).map(user -> {
            bond.setUser(user);
            return bondRepository.save(bond);
        }).orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
    }

    @Override
    public Bond updateBond(Long userId, Long bondId, Bond bondRequest) {
        if (!userRepository.existsById(userId))
            throw new ResourceNotFoundException("User", "Id", userId);
        return bondRepository.findById(bondId).map(bond -> {
            bond.setValorNominal(bondRequest.getValorNominal());
            bond.setTasa(bondRequest.getTasa());
            bond.setPeriodoPago(bondRequest.getPeriodoPago());
            bond.setVencimiento(bondRequest.getVencimiento());
            bond.setTasaNegociacion(bondRequest.getTasaNegociacion());
            bond.setMercadoPrimario(bondRequest.isMercadoPrimario());
            bond.setConvexidad(bondRequest.getConvexidad());
            bond.setTir(bondRequest.getTir());
            bond.setDuracion(bondRequest.getDuracion());
            bond.setDuracionModificada(bondRequest.getDuracionModificada());
            return bondRepository.save(bond);
        }).orElseThrow(() -> new ResourceNotFoundException("Bond", "Id", bondId));
    }

    @Override
    public ResponseEntity<?> deleteBond(Long userId, Long bondId) {
        if (!userRepository.existsById(userId))
            throw new ResourceNotFoundException("User", "Id", userId);
        return bondRepository.findById(bondId).map(bond -> {
            bondRepository.delete(bond);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("Bond", "Id", bondId));
    }
}
