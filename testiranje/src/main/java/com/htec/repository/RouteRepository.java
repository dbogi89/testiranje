package com.htec.repository;

import com.htec.entity.Route;
import com.htec.entity.RoutePk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by dbogicevic
 */
@Repository
public interface RouteRepository extends JpaRepository<Route, RoutePk> {
    @Query("SELECT r from Route r WHERE  r.routePk in :routes ")
    List<Route> findByRoutePkAndDestinationCode(List<RoutePk> routes);
}
