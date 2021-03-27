package com.bigjava.service.impl;

import com.bigjava.api.dto.airport.AitPortDtoRequest;
import com.bigjava.api.dto.document.Response;
import com.bigjava.api.dto.route.RouteDtoRequest;
import com.bigjava.constants.Constants;
import com.bigjava.entity.Airport;
import com.bigjava.entity.City;
import com.bigjava.entity.Country;
import com.bigjava.entity.Route;
import com.bigjava.mapper.AirPortMapper;
import com.bigjava.repository.AirPortRepository;
import com.bigjava.repository.CityRepository;
import com.bigjava.repository.CountryRepository;
import com.bigjava.repository.RouteRepository;
import com.bigjava.service.AirPortService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
    public Response save(List<AitPortDtoRequest> aitPortDtoRequestList) {
        airPortRepository.saveAll(
                aitPortDtoRequestList.stream().map(a -> {
                    Country country = countryRepository.findByCountryName(a.getCountryName()).
                            orElseGet(() -> countryRepository.save(new Country(a.getCountryName())));
                    City city = cityRepository.findByCityName(a.getCityName()).
                            orElseGet(() -> cityRepository.save(new City(a.getCityName(), "", country)));

                    return airPortMapper.toAirPort(a, country, city);
                }).collect(Collectors.toList()));

        return Response.builder()
                .description("Upload file!!")
                .code(Constants.OK)
                .content("Ok")
                .build();
    }

    @Override
    @Transactional
    public Response saveRoute(List<RouteDtoRequest> routeDtoRequests) {
        List<Route> routes = new ArrayList<>();
        for (RouteDtoRequest r : routeDtoRequests) {
            Optional<Airport> sourceAirPort = airPortRepository.findById(airPortMapper.isNumeric(r.getSourceAirPortId()));
            Optional<Airport> destinationAirPort = airPortRepository.findById(airPortMapper.isNumeric(r.getDestinationAirPortId()));
            if (sourceAirPort.isPresent() && destinationAirPort.isPresent()) {
                Route route = airPortMapper.toRoute(r);
                route.setSourceAirPort(sourceAirPort.get());
                route.setDestinationAirPort(destinationAirPort.get());
                routes.add(route);
            }
        }
        routeRepository.saveAll(routes);
        return Response.builder()
                .description("Upload file!!")
                .code(Constants.OK)
                .content("Ok")
                .build();
    }

}
