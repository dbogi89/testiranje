package com.bigjava.service;

import com.bigjava.api.dto.airport.AitPortDtoRequest;
import com.bigjava.api.dto.document.Response;
import com.bigjava.api.dto.route.RouteDtoRequest;


import java.util.List;

/**
 * Created by dbogicevic
 */
public interface AirPortService {
    Response save(List<AitPortDtoRequest> aitPortDtoRequestList);

    Response saveRoute(List<RouteDtoRequest> routeDtoRequests);
}
