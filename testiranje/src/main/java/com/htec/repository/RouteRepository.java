package com.htec.repository;

import com.htec.entity.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by dbogicevic
 */
@Repository
public interface RouteRepository extends JpaRepository<Route,Long> {
}
