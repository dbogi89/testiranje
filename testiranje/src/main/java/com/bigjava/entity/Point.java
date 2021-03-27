package com.bigjava.entity;

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

    public static Point of(BigDecimal latitude, BigDecimal longitude) {

        return new Point(latitude, longitude);
    }

    public static Double calculateDistance(Point start, Point end) {
        // The math module contains a function
        // named toRadians which converts from
        // degrees to radians.
        double lon1 = Math.toRadians(start.longitude.doubleValue());
        double lon2 = Math.toRadians(end.longitude.doubleValue());
        double lat1 = Math.toRadians(start.latitude.doubleValue());
        double lat2 = Math.toRadians(start.latitude.doubleValue());
        // Haversine formula
        double dlon = lon2 - lon1;
        double dlat = lat2 - lat1;
        double a = Math.pow(Math.sin(dlat / 2), 2)
                + Math.cos(lat1) * Math.cos(lat2)
                * Math.pow(Math.sin(dlon / 2), 2);

        double c = 2 * Math.asin(Math.sqrt(a));
        double r = 6371; //km
        // calculate the result
        return new Double(c * r);
    }

}



