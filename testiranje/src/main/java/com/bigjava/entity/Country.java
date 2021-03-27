package com.bigjava.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

/**
 * Created by dbogicevic
 */
@Entity
@Table(name = "COUNTRY",catalog = "testdb", schema = "PUBLIC")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"airports","cities"})
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Long id;

    @NonNull
    @Basic(optional = false)
    @Column(nullable = false, length = 50, name = "COUNTRY_NAME")
    private String countryName;

    public Country(String countryName) {
        this.countryName = countryName;
    }

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "country")
    private List<Airport> airports;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "country")
    private List<City> cities;
}
