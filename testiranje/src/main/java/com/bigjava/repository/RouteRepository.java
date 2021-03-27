package com.bigjava.repository;

import com.bigjava.entity.Route;
import com.bigjava.entity.RoutePk;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by dbogicevic
 */
@Repository
@CacheConfig(cacheNames = "routes")
public interface RouteRepository extends JpaRepository<Route, RoutePk> {
    @Query("SELECT r from Route r WHERE  r.routePk in :routes ")
    List<Route> findByRoutePkAndDestinationCode(List<RoutePk> routes);
    @Cacheable
    @Query("select r from Route r")
    List<Route> finaAllRoute();
}
