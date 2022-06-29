package com.upc.smartbonds.controller;

import com.upc.smartbonds.entity.Bond;
import com.upc.smartbonds.entity.User;
import com.upc.smartbonds.resource.BondResource;
import com.upc.smartbonds.resource.SaveBondResource;
import com.upc.smartbonds.resource.SaveUserResource;
import com.upc.smartbonds.resource.UserResource;
import com.upc.smartbonds.service.BondService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*", maxAge = 3600)
public class BondController {

    @Autowired
    private BondService bondService;

    @Autowired
    private ModelMapper mapper;

    @GetMapping("/users/{userId}/bonds")
    public Page<BondResource> getAllBondsByUserId(@PathVariable Long userId, Pageable pageable) {
        Page<Bond> bondPage = bondService.getAllBondsByUserId(userId, pageable);
        List<BondResource> resources = bondPage.getContent()
                .stream()
                .map(this::convertToResource)
                .collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    @PostMapping("/users/{userId}/bonds")
    public BondResource createBond(@PathVariable Long userId, @Valid @RequestBody SaveBondResource resource) {
        return convertToResource(bondService.createBond(userId, convertToEntity(resource)));
    }

    @PutMapping("/users/{userId}/bonds/{bondId}")
    public BondResource updateBond(@PathVariable Long userId, @PathVariable Long bondId, @Valid @RequestBody SaveBondResource resource) {
        return convertToResource(bondService.updateBond(userId, bondId, convertToEntity(resource)));
    }

    @DeleteMapping("/users/{userId}/bonds/{bondId}")
    public ResponseEntity<?> deleteBond(@PathVariable Long userId, @PathVariable Long bondId) {
        return bondService.deleteBond(userId, bondId);
    }

    private Bond convertToEntity(SaveBondResource resource) {
        return mapper.map(resource, Bond.class);
    }

    private BondResource convertToResource(Bond entity) {
        return mapper.map(entity, BondResource.class);
    }
}
