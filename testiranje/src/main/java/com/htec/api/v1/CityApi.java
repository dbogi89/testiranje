package com.htec.api.v1;

import com.htec.api.dto.city.CityDtoRequest;
import com.htec.api.dto.city.CitySerach;
import com.htec.api.dto.comment.CommentDtoRequest;
import com.htec.api.dto.city.CityDtoResponse;
import com.htec.api.dto.document.Response;
import com.htec.constants.Constants;
import com.htec.entity.Comment;
import com.htec.exception.CityException;
import com.htec.service.CityService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


/**
 * Created by dbogicevic
 */
@RestController
@RequestMapping("api/v1/cities")
@AllArgsConstructor
public class CityApi {

    private final CityService cityService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
     public CityDtoResponse addCity(@RequestBody CityDtoRequest cityDtoRequest){
         return cityService.createCity(cityDtoRequest);
     }

     @GetMapping
     @ResponseStatus(HttpStatus.OK)
     public List<CityDtoResponse> findAllCities(@RequestParam(defaultValue = "0",required = false)Integer limit){
        if(limit < 0 )throw new CityException("Limit must be zero or greater");
       return cityService.sreachCity(new CitySerach(""),limit);


     }

     @PutMapping("/comments/{idComment}")
     public ResponseEntity<Response> updateComment(@PathVariable Long idComment, @RequestBody CommentDtoRequest commentDtoRequest){
      cityService.updateComment(idComment, commentDtoRequest);
      return new ResponseEntity<>(Response.builder().code(Constants.OK).content("Update comment").description("Id comment == "+idComment).build(),HttpStatus.OK);

    }

    @DeleteMapping("/comments/{idComment}")
    public ResponseEntity<Response> deleteComment(@PathVariable Long idComment){
        cityService.deleteComment(idComment);
        return new ResponseEntity<>(Response.builder().code(Constants.OK).content("Delete comment").description("Id comment == "+idComment).build(),HttpStatus.OK);

    }

    @PostMapping("/{idCity}/comments")
    @ResponseStatus(HttpStatus.CREATED)
    public Comment addComment(@PathVariable Long idCity, @RequestBody CommentDtoRequest commentDtoRequest){
        return cityService.createComment(idCity, commentDtoRequest);
    }


    @PostMapping("/citySerach")
    @ResponseStatus(HttpStatus.OK)
    public List<CityDtoResponse> findByNameCities(@RequestParam(defaultValue = "0")Integer limit, @RequestBody @Valid CitySerach citySerach){
        if(limit < 0 )throw new CityException("Limit must be zero or greater");
        return cityService.sreachCity(citySerach,limit);


    }
}
