package com.bigjava.api.dto.document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author dbogicevic
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Response<T> {

    private int code;
    private String description;
    private T content;

}
