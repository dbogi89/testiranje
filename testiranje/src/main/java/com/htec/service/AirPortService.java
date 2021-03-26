package com.htec.service;

import com.htec.api.dto.airport.AitPortDtoRequest;
import com.htec.api.dto.document.Response;
import com.htec.api.dto.route.RouteDtoRequest;


import java.util.List;

/**
 * Created by dbogicevic
 */
public interface AirPortService {
    Response save(List<AitPortDtoRequest> aitPortDtoRequestList);

    Response saveRoute(List<RouteDtoRequest> routeDtoRequests);
}
