package com.example.infsystem.repositories;

import com.example.infsystem.models.Additive;
import com.example.infsystem.models.TypeRecipe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdditiveRepository extends JpaRepository<Additive, Long> {

    List<Additive> findAdditivesByTypeRecipe(TypeRecipe typeRecipe);

}
