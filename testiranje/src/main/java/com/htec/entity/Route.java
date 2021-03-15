package com.htec.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Created by dbogicevic
 */
@Entity
@Table(name = "ROUTE",catalog = "testdb", schema = "PUBLIC")
@Data
@NoArgsConstructor
public class Route {
    //setuj ovo u posebnu klasu
    @EmbeddedId
   private RoutePk routePk;

    @Column(name = "AIRLINE_CODE", length = 3)
    private String airlineCode;

    @Column(name = "AIRLINE_ID")
    private Long airlineId;

    @Column(name = "CODE_SHARE")
    private Boolean codeShare;

    private Integer stops;

    @Column(length = 100)
    private String equipment;

    @Column(precision = 6, scale = 3)
    private Double price;

    @JoinColumn(name = "DESTINATION_AIRPORT_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Airport destinationAirPort;

    @JoinColumn(name = "SOURCE_AIRPORT_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Airport sourceAirPort;
}
