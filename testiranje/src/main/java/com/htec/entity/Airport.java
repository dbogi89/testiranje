package com.htec.entity;

import lombok.*;
import javax.persistence.*;
import java.util.List;

/**
 * Created by dbogicevic
 */
@Entity
@Table(name = "AIR_PORT",catalog = "testdb", schema = "PUBLIC")
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString(exclude = {"destinationRoutes", "sourceRoutes"})
public class Airport {
    @Id
    private Long id;
    @NonNull
    @Basic(optional = false)
    @Column(name = "NAME_AIRPORT")
    private String airPortName;

    @NonNull
    @Basic(optional = false)
    @Column(name = "CITY_NAME", nullable = false, length = 80)
    private String cityName;

    @NonNull
    @Basic(optional = false)
    @Column(name = "COUNTRY_NAME", nullable = false, length = 50)
    private String countryName;

    @Column(length = 3, name = "IATA")
    private String iata;

    @Column(length = 4,name = "ICAO")
    private String icao;

    @Embedded
    private Point point;

    @NonNull
    private String altitude;

    @NonNull
    private Float timezone;

    @Enumerated(EnumType.STRING)
    private DaylightSavingsTime daylightSavingsTime;

    @NonNull
    @Column(length = 50)
    private String tz;

    @NonNull
    @Basic(optional = false)
    @Column(name = "TYPE", nullable = false)
    private String type;

    @NonNull
    @Basic(optional = false)
    @Column(name = "SOURCE", nullable = false)
    private String source;

    @OneToMany(mappedBy = "destinationAirPort")
    private List<Route> destinationRoutes;

    @OneToMany(mappedBy = "sourceAirPort")
    private List<Route> sourceRoutes;

    @JoinColumn(name = "CITY_ID", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private City city;

    @JoinColumn(name = "COUNTRY_ID", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Country country;
}
