package com.example.infsystem.repositories;

import com.example.infsystem.models.AdditiveOrderPosition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdditiveOrderPositionRepository extends JpaRepository<AdditiveOrderPosition, Long> {

}
