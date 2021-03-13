package com.htec.mapper;

import com.htec.api.dto.request.CityDtoRequest;
import com.htec.api.dto.request.CommentDtoRequest;
import com.htec.api.dto.response.CityDtoResponse;
import com.htec.entity.City;
import com.htec.entity.Comment;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by dbogicevic
 */
@Component
public class CityMapper {

    public City toCity(CityDtoRequest cityDtoRequest){
        City city = new City();
        city.setCityName(cityDtoRequest.getCityName());
        city.setDescription(cityDtoRequest.getDescription());
        return city;
    }

    public CityDtoResponse toCityResponse(City city) {
        return CityDtoResponse.builder()
                .idCity(city.getId())
                .cityName(city.getCityName())
                .countryName(city.getCountry().getCountryName())
                .build();

    }


    public Comment toComment(CommentDtoRequest commentDtoRequest) {
        Comment comment = new Comment();
        comment.setDescription(commentDtoRequest.getDescription());
        return comment;
    }
}
