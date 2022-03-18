package com.example.examfinal.repository;

import com.example.examfinal.model.entity.CityEntity;
import com.example.examfinal.model.entity.PlaceEntity;
import com.example.examfinal.model.entity.enums.NameType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlaceRepository extends JpaRepository<PlaceEntity, Long> {
    Optional<PlaceEntity> findByName(String name);
    Optional<PlaceEntity> findById(String cityId);
}
