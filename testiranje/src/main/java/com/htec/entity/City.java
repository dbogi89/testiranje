package com.htec.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dbogicevic
 */
@Entity
@Table(name = "CITY",catalog = "testdb", schema = "PUBLIC")
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@ToString(exclude = {"airports","country", "comments"})
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Long id;

    @NonNull
    @Basic(optional = false)
    @Column(nullable = false, length = 80, name = "CITY_NAME")
    private String cityName;

    @Basic(optional = false)
    @Column(length = 150)
    private String description;

    public City(String cityName, String description, Country country) {
        this.cityName = cityName;
        this.description = description;
        this.country = country;
    }

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "city")
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "city", fetch = FetchType.LAZY)
    private List<Airport> airports;

    @NonNull
    @JoinColumn(name = "COUNTRY_ID", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Country country;

    public void addComment(Comment comment) {
        comments.add(comment);
        comment.setCity(this);
    }

    public void removeComment(Comment comment) {
        comments.remove(comment);
        comment.setCity(null);
    }
}

