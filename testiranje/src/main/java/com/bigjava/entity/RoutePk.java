package com.bigjava.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * Created by dbogicevic
 */
@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoutePk implements Serializable {
    @NonNull
    private String sourceCode;
    @NonNull
    private String destinationCode;
}
