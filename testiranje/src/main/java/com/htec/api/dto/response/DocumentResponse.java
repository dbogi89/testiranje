package com.htec.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author dbogicevic
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class DocumentResponse<T> {
    private int code;
    private String description;
    private T content;





}