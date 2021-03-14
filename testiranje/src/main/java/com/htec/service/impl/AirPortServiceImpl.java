package com.htec.service.impl;

import com.htec.api.dto.airport.AitPortDtoRequest;
import com.htec.api.dto.route.RouteDtoRequest;
import com.htec.entity.Airport;
import com.htec.entity.City;
import com.htec.entity.Country;
import com.htec.entity.Route;
import com.htec.mapper.AirPortMapper;
import com.htec.repository.AirPortRepository;
import com.htec.repository.CityRepository;
import com.htec.repository.CountryRepository;
import com.htec.repository.RouteRepository;
import com.htec.service.AirPortService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by dbogicevic
 */
@Service
@AllArgsConstructor
public class AirPortServiceImpl implements AirPortService {
    private final CityRepository cityRepository;
    private final CountryRepository countryRepository;
    private final AirPortRepository airPortRepository;
    private final AirPortMapper airPortMapper;
    private final RouteRepository routeRepository;

    @Override
    @Transactional
    public List<Airport> save(List<AitPortDtoRequest> aitPortDtoRequestList) {
           List<Airport> airPorts =  airPortRepository.saveAll(
           aitPortDtoRequestList.stream().map(a -> {
            Country country = countryRepository.findByCountryName(a.getCountryName()).
                    orElseGet(() -> countryRepository.save(new Country(a.getCountryName())));
            a.setCountId(country.getId());
            City city = cityRepository.findByCityName(a.getCityName()).
                    orElseGet(() -> cityRepository.save(new City(a.getCityName(), country)));
            a.setCityId(city.getId());

            return airPortMapper.toAirPort(a);
        }).collect(Collectors.toList()));
        return airPortMapper.toAirPortDto(airPorts);
    }

    @Override
    @Transactional
    public List<Route> saveRoute(List<RouteDtoRequest> routeDtoRequests) {
        List<Route>routes = new ArrayList<>();
        for(RouteDtoRequest r: routeDtoRequests) {
            Airport sourceAirPort = airPortRepository.findById(r.getSourceAirPortId()).get();
            Airport destinationAirPort  = airPortRepository.findById(r.getDestinationAirPortId()).get();
            if(sourceAirPort != null && destinationAirPort != null){
                Route route = airPortMapper.toRoute(r);
                route.setSourceAirPort(sourceAirPort);
                route.setDestinationAirPort(destinationAirPort);
                routes.add(route);
            }
        }
        routeRepository.saveAll(routes);
        return null;
    }
}
