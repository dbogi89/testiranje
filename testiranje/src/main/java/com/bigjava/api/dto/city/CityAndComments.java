package com.bigjava.api.dto.city;

import com.bigjava.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created by dbogicevic
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class CityAndComments {

    private CityDtoResponse city;
    private List<Comment> comments;

}
