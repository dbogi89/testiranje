package com.htec.mapper;

import com.htec.api.dto.airport.AitPortDtoRequest;
import com.htec.api.dto.route.RouteDtoRequest;
import com.htec.entity.Airport;
import com.htec.entity.Point;
import com.htec.entity.Route;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by dbogicevic
 */
@Component
public class AirPortMapper {

    public Airport toAirPort(AitPortDtoRequest aitPortDtoRequest){
        Airport airport = new Airport();
        airport.setAirPortName(aitPortDtoRequest.getAirPortName());
        airport.setAltitude(aitPortDtoRequest.getAltitude());
        airport.setCityName(aitPortDtoRequest.getCityName());
        airport.setCountryName(aitPortDtoRequest.getCountryName());
        airport.setIata(aitPortDtoRequest.getIata());
        airport.setIcao(aitPortDtoRequest.getIcao());
        airport.setPoint(Point.of(aitPortDtoRequest.getLatitude(), aitPortDtoRequest.getLongitude()));
        airport.setSource(aitPortDtoRequest.getSource());
        airport.setTimezone(aitPortDtoRequest.getTimezone());
        airport.setType(aitPortDtoRequest.getType());
        airport.setTz(aitPortDtoRequest.getTz());
        airport.setId((long) aitPortDtoRequest.getId());

        return airport;
    }

    public List<Airport> toAirPortDto(List<Airport> airPorts) {
        return null;
    }

    public Route toRoute(RouteDtoRequest r) {
        Route route = new Route();
        route.setAirlineCode(r.getAirlineCode());
        route.setAirlineId(r.getAirlineId());
        route.setCodeShare(r.getCodeShare());
        route.setEquipment(r.getEquipment());
        route.setPrice(r.getPrice());
        route.setStops(r.getStops());
        return route;
    }
}
