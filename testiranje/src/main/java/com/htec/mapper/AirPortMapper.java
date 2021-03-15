package com.htec.mapper;

import com.htec.api.dto.airport.AitPortDtoRequest;
import com.htec.api.dto.route.RouteDtoRequest;
import com.htec.entity.*;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by dbogicevic
 */
@Component
public class AirPortMapper {

    public Airport toAirPort(AitPortDtoRequest aitPortDtoRequest, Country country, City city){
         Airport airport = new Airport(); 
        try {
        if(!aitPortDtoRequest.getDst().equals("E") && !aitPortDtoRequest.getDst().equals("A") &&
                    !aitPortDtoRequest.getDst().equals("S") &&
            !aitPortDtoRequest.getDst().equals("O") && !aitPortDtoRequest.getDst().equals("Z")
                && !aitPortDtoRequest.getDst().equals("N") &&
                    !aitPortDtoRequest.getDst().equals("U")){
            airport.setDaylightSavingsTime(DaylightSavingsTime.U);
            }else{
                airport.setDaylightSavingsTime(DaylightSavingsTime.valueOf(aitPortDtoRequest.getDst()));
            }

            airport.setAirPortName(aitPortDtoRequest.getAirPortName());
            airport.setAltitude(aitPortDtoRequest.getAltitude());
            airport.setCityName(aitPortDtoRequest.getCityName());
            airport.setCountryName(aitPortDtoRequest.getCountryName());
            airport.setIata(aitPortDtoRequest.getIata());
            airport.setIcao(aitPortDtoRequest.getIcao());
            airport.setPoint(Point.of(aitPortDtoRequest.getLatitude(), aitPortDtoRequest.getLongitude()));
            airport.setSource(aitPortDtoRequest.getSource());

            airport.setType(aitPortDtoRequest.getType());
            airport.setTz(aitPortDtoRequest.getTz());
            System.out.println("Vvvvvvvvvvvvvvvvvvvv " + aitPortDtoRequest.getId().trim());
            airport.setId(Long.valueOf(removeUTF8BOM(aitPortDtoRequest.getId().trim())));
            airport.setCountry(country);
            airport.setCity(city);

            airport.setTimezone(Float.valueOf(aitPortDtoRequest.getTimezone()));
        }catch (Exception e){
            airport.setTimezone(-100.0f);
        }



        return airport;
    }
    private  String removeUTF8BOM(String s) {
        if (s.startsWith("\uFEFF")) {
            s = s.substring(1);
        }
        return s;
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
