package com.htec.service;

import com.htec.api.dto.request.CityDtoRequest;
import com.htec.api.dto.request.CitySerach;
import com.htec.api.dto.request.CommentDtoRequest;
import com.htec.api.dto.response.CityDtoResponse;
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
}
