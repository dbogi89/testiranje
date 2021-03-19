package com.htec.service;

import com.htec.api.dto.airport.TravelDtoResponse;
import com.htec.api.dto.city.CityDtoRequest;
import com.htec.api.dto.city.CitySerach;
import com.htec.api.dto.comment.CommentDtoRequest;
import com.htec.api.dto.city.CityDtoResponse;
import com.htec.api.dto.document.Response;
import com.htec.entity.Comment;

import java.util.List;

/**
 * Created by dbogicevic
 */
public interface CityService {

     CityDtoResponse createCity(CityDtoRequest cityDtoRequest);

     List<CityDtoResponse> sreachCity(CitySerach citySerach, Integer limit);

     void updateComment(Long idComment, CommentDtoRequest commentDtoRequest);

     void deleteComment(Long idComment);

     Comment createComment(Long idCity, CommentDtoRequest commentDtoRequest);

    Response findByCheapestFlight(String from, String to);
}
