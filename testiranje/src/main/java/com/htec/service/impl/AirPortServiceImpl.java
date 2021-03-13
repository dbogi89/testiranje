package com.htec.service.impl;

import com.htec.api.dto.request.AitPortDtoRequest;
import com.htec.entity.Airport;
import com.htec.entity.City;
import com.htec.entity.Country;
import com.htec.mapper.AirPortMapper;
import com.htec.mapper.CityMapper;
import com.htec.repository.AirPortRepository;
import com.htec.repository.CityRepository;
import com.htec.repository.CountryRepository;
import com.htec.service.AirPortService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
