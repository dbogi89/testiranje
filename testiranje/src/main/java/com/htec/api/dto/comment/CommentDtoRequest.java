package com.htec.api.dto.comment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by dbogicevic
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CommentDtoRequest {
    private String description;
}
