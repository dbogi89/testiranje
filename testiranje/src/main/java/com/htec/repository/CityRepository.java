package com.htec.repository;

import com.htec.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Created by dbogicevic
 */
@Repository
public interface CityRepository extends JpaRepository<City, Long> {
    Optional<City> findByCityName(String cityName);
    List<City>findByCityNameContaining(String cityName);

}
