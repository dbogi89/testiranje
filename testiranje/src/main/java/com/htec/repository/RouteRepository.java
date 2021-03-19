package com.htec.repository;

import com.htec.entity.Route;
import com.htec.entity.RoutePk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by dbogicevic
 */
@Repository
public interface RouteRepository extends JpaRepository<Route, RoutePk> {
    List<Route> findByRouteDestinationCodeIn(List<String> routes);
}
