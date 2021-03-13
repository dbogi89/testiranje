package com.htec.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

/**
 * Created by dbogicevic
 */
@Entity
@Table(name = "COMMENT", catalog = "testdb", schema = "PUBLIC")
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Comment {
    @Id
    @NonNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Long id;

    @NonNull
    @Basic(optional = false)
    @Column(name = "DESCRIPTION", nullable = false, length = 1500)
    private String description;

    @Embedded
    private Timestamps timestamps = new Timestamps();

    @JoinColumn(name = "CITY_ID", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JsonIgnore
    private City city;


}
