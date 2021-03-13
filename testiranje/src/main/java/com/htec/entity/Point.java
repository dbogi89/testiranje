package com.htec.entity;

import lombok.*;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.math.BigDecimal;

/**
 * Created by dbogicevic
 */
@Data
@Embeddable
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Point {
    @NonNull
    @Basic(optional = false)
    @Column(nullable = false, precision = 12, scale = 6)
    private BigDecimal latitude;

    @NonNull
    @Basic(optional = false)
    @Column(nullable = false, precision = 12, scale = 6)
    private BigDecimal longitude;

    public static Point of(BigDecimal latitude, BigDecimal longitude){

        return new Point(latitude, longitude);
    }


}
