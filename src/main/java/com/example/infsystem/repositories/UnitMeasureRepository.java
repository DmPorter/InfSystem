package com.example.infsystem.repositories;

import com.example.infsystem.models.UnitMeasurement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UnitMeasureRepository extends JpaRepository<UnitMeasurement, Long> {
}
