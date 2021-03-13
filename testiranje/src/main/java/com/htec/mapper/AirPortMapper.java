package com.htec.mapper;

import com.htec.api.dto.request.AitPortDtoRequest;
import com.htec.entity.Airport;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by dbogicevic
 */
@Component
public class AirPortMapper {

    public Airport toAirPort(AitPortDtoRequest aitPortDtoRequest){
        Airport airport = new Airport();

        return airport;
    }

    public List<Airport> toAirPortDto(List<Airport> airPorts) {
        return null;
    }
}
