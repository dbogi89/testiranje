package com.bigjava.mapper;

import com.bigjava.api.dto.city.CityDtoRequest;
import com.bigjava.api.dto.comment.CommentDtoRequest;
import com.bigjava.api.dto.city.CityDtoResponse;
import com.bigjava.entity.City;
import com.bigjava.entity.Comment;
import org.springframework.stereotype.Component;

/**
 * Created by dbogicevic
 */
@Component
public class CityMapper {

    public City toCity(CityDtoRequest cityDtoRequest) {
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
                .description(city.getDescription())
                .comments(city.getComments())
                .build();

    }

    public Comment toComment(CommentDtoRequest commentDtoRequest) {
        Comment comment = new Comment();
        comment.setDescription(commentDtoRequest.getDescription());
        return comment;
    }
}
