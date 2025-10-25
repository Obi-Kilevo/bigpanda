package com.obi.bigpanda.Entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="park_likes")
public class ParkLikesEntity {




    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;



    @Column(name="park_id", nullable=false)
    private Long parkId;

    @Column(name="customer_id")
    private Long customerId;

    @Column(name="guest_session_id", length=255)
    private String guestSessionId;

    @Column(name="created_at")
    private LocalDateTime createdAt = LocalDateTime.now();


    @Column(name="active")
    private boolean active = true;



    @Column(name = "liked")
    private Boolean liked;


    public ParkLikesEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getParkId() {
        return parkId;
    }

    public void setParkId(Long parkId) {
        this.parkId = parkId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getGuestSessionId() {
        return guestSessionId;
    }

    public void setGuestSessionId(String guestSessionId) {
        this.guestSessionId = guestSessionId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Boolean getLiked() {
        return liked;
    }

    public void setLiked(Boolean liked) {
        this.liked = liked;
    }

    @Override
    public String toString() {
        return "ParkLikesEntity{" +
                "id=" + id +
                ", parkId=" + parkId +
                ", customerId=" + customerId +
                ", guestSessionId='" + guestSessionId + '\'' +
                ", createdAt=" + createdAt +
                ", active=" + active +
                ", liked=" + liked +
                '}';
    }
}
