package com.obi.bigpanda.Entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "bookings")
public class BookingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tour_id", nullable = false)
    private Long tourId;

    @Column(name = "user_name", nullable = false, length = 255)
    private String userName;

    @Column(name = "user_email", nullable = false, length = 255)
    private String userEmail;

    @Column(name = "number_of_adults", nullable = false)
    private Integer numberOfAdults;

    @Column(name = "number_of_children", nullable = false)
    private Integer numberOfChildren;

    @Column(name = "tour_package", nullable = false, length = 255)
    private String tourPackage;

    @Column(name = "duration", nullable = false)
    private Integer duration;

//    @Column(name = "total_budget", nullable = false, precision = 10, scale = 2)
//    private BigDecimal totalBudget;

    @Column(name = "destination", nullable = true, length = 255)
    private String destination;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;


    @Transient
    private CouponEntity activeCoupon;

    public CouponEntity getActiveCoupon() { return activeCoupon; }
    public void setActiveCoupon(CouponEntity activeCoupon) { this.activeCoupon = activeCoupon; }



    @Column(name = "category")
    private String category;


    public BookingEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTourId() {
        return tourId;
    }

    public void setTourId(Long tourId) {
        this.tourId = tourId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public Integer getNumberOfAdults() {
        return numberOfAdults;
    }

    public void setNumberOfAdults(Integer numberOfAdults) {
        this.numberOfAdults = numberOfAdults;
    }

    public Integer getNumberOfChildren() {
        return numberOfChildren;
    }

    public void setNumberOfChildren(Integer numberOfChildren) {
        this.numberOfChildren = numberOfChildren;
    }

    public String getTourPackage() {
        return tourPackage;
    }

    public void setTourPackage(String tourPackage) {
        this.tourPackage = tourPackage;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "BookingEntity{" +
                "id=" + id +
                ", tourId=" + tourId +
                ", userName='" + userName + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", numberOfAdults=" + numberOfAdults +
                ", numberOfChildren=" + numberOfChildren +
                ", tourPackage='" + tourPackage + '\'' +
                ", duration=" + duration +
                ", destination='" + destination + '\'' +
                ", createdAt=" + createdAt +
                ", category='" + category + '\'' +
                '}';
    }
}