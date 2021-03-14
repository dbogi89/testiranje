package com.htec.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;

/**
 * Created by dbogicevic
 */

@Embeddable
@Data
public class Timestamps {
    @Column(name = "CREATED", nullable = false, updatable = false)
    private LocalDateTime created;

    @Column(name = "UPDATED", nullable = false, updatable = false)
    private LocalDateTime updated;

    @PrePersist
    public void prePersist() {
        created = LocalDateTime.now();
        updated = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        updated = LocalDateTime.now();
    }
}