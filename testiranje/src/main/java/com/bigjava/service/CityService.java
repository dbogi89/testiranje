package com.bigjava.service;

import com.bigjava.api.dto.city.CityDtoRequest;
import com.bigjava.api.dto.city.CitySerach;
import com.bigjava.api.dto.comment.CommentDtoRequest;
import com.bigjava.api.dto.city.CityDtoResponse;
import com.bigjava.api.dto.document.Response;
import com.bigjava.entity.Comment;

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
