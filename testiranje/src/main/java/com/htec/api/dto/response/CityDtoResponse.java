package com.htec.api.dto.response;

import com.htec.entity.Comment;
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
public class CityDtoResponse {
    private Long idCity;
    private String cityName;
    private String countryName;
    private String description;
    private List<Comment> comments;

}
