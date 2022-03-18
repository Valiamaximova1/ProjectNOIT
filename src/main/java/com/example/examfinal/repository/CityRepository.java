package com.example.examfinal.repository;

import com.example.examfinal.model.entity.CityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CityRepository extends JpaRepository<CityEntity, Long> {
    Optional<CityEntity> findByName(String name);

    Optional<CityEntity> findById(String cityId);
}
