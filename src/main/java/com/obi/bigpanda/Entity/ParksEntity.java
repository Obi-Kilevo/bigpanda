package com.obi.bigpanda.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import jakarta.persistence.Column;

@Entity
@Table(name="parks")
public class ParksEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="name", nullable=false, unique=true, length=255)
    private String name;


    @Column(name = "park_id") // Map to the 'park_id' column if it's not the primary key
    private Long parkId; // Add this field

    public ParksEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getParkId() {
        return parkId;
    }

    public void setParkId(Long parkId) {
        this.parkId = parkId;
    }

    @Override
    public String toString() {
        return "ParksEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", parkId=" + parkId +
                '}';
    }
}
