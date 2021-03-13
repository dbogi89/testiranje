package com.htec.service;

import com.htec.api.dto.request.AitPortDtoRequest;
import com.htec.entity.Airport;

import java.util.List;

/**
 * Created by dbogicevic
 */
public interface AirPortService {
    List<Airport>  save(List<AitPortDtoRequest> aitPortDtoRequestList);
}
