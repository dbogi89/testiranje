package com.htec.service.impl;

import com.htec.api.dto.city.CityDtoRequest;
import com.htec.api.dto.city.CitySerach;
import com.htec.api.dto.comment.CommentDtoRequest;
import com.htec.api.dto.city.CityDtoResponse;
import com.htec.entity.City;
import com.htec.entity.Comment;
import com.htec.entity.Country;
import com.htec.exception.CityException;
import com.htec.exception.CommnetException;
import com.htec.mapper.CityMapper;
import com.htec.repository.CityRepository;
import com.htec.repository.CommentRepository;
import com.htec.repository.CountryRepository;
import com.htec.service.CityService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
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
public class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;
    private final CountryRepository countryRepository;
    private final CityMapper cityMapper;
    private final CommentRepository commentRepository;

    @Override
    @Transactional
    public CityDtoResponse createCity(CityDtoRequest cityDtoRequest) {
        Optional<Country> countryDataBase = countryRepository.findByCountryName(cityDtoRequest.getCountryName());
        Optional<City> cityDatabase = cityRepository.findByCityName(cityDtoRequest.getCityName());
        if (cityDatabase.isPresent()) {
            throw new CityException("City exist");
        }
        City city = cityMapper.toCity(cityDtoRequest);
        if (!countryDataBase.isPresent()) {
            Country country = new Country();
            country.setCountryName(cityDtoRequest.getCountryName());
            countryRepository.save(country);
            city.setCountry(country);
        } else {
            city.setCountry(countryDataBase.get());
        }
        return cityMapper.toCityResponse(cityRepository.save(city));
    }

    @Override
    @Transactional(readOnly = true)
    public List<CityDtoResponse> sreachCity(CitySerach citySerach, Integer limit) {
        List<CityDtoResponse> cityDtoResponses = new ArrayList<>();
        List<City> cities = cityRepository.findByCityNameContaining(citySerach.getCityName());
        if (limit == 0) {
            cityDtoResponses = cities.stream()
                    .map(cityMapper::toCityResponse)
                    .collect(Collectors.toList());

        } else {
            for (City city : cities) {
                CityDtoResponse cityDtoResponse = cityMapper.toCityResponse(city);
                List<Comment> comments = commentRepository.findByCity(city, PageRequest.of(0, limit));
                cityDtoResponse.setComments(comments);
                cityDtoResponses.add(cityDtoResponse);

            }

        }

        return cityDtoResponses;
    }

    private Comment findByComment(Long idComment) {
        return commentRepository.findById(idComment)
                .orElseThrow(() -> new CommnetException("Comment not exist"));
    }

    @Transactional
    public void updateComment(Long idComment, CommentDtoRequest commentDtoRequest) {
        Comment comment = findByComment(idComment);
        comment.setDescription(commentDtoRequest.getDescription());
        commentRepository.save(comment);

    }

    @Transactional
    public void deleteComment(Long idComment) {
        Comment comment = findByComment(idComment);
        commentRepository.delete(comment);
    }

    @Override
    @Transactional
    public Comment createComment(Long idCity, CommentDtoRequest commentDtoRequest) {
        City city = cityRepository.findById(idCity)
                .orElseThrow(() -> new CityException("City not exist"));
        Comment comment = cityMapper.toComment(commentDtoRequest);
        city.addComment(comment);
        return commentRepository.save(comment);

    }

}
