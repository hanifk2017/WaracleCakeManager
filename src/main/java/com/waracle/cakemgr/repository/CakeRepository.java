package com.waracle.cakemgr.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.waracle.cakemgr.model.CakeEntity;


public interface CakeRepository extends JpaRepository<CakeEntity, Integer> {

}
