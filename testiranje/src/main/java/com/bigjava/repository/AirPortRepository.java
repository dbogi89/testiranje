package com.bigjava.repository;

import com.bigjava.entity.Airport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by dbogicevic
 */
@Repository
public interface AirPortRepository extends JpaRepository<Airport, Long> {
}
