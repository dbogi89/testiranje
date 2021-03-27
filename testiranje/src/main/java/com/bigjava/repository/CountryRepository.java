package com.bigjava.repository;

import com.bigjava.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Created by dbogicevic
 */
@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {
    Optional<Country>findByCountryName(String countryName);


}
