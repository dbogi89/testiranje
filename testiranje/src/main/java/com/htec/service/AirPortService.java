package com.htec.service;

import com.htec.api.dto.request.AitPortDtoRequest;
import com.htec.api.dto.request.RouteDtoRequest;
import com.htec.entity.Airport;
import com.htec.entity.Route;

import java.util.List;

/**
 * Created by dbogicevic
 */
public interface AirPortService {
    List<Airport>  save(List<AitPortDtoRequest> aitPortDtoRequestList);

    List<Route> saveRoute(List<RouteDtoRequest> routeDtoRequests);
}
