

package com.obi.bigpanda.Entity;
import java.time.format.DateTimeFormatter;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "bookings_copy")
public class QuickBookingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;



    public String getFormattedCreatedAt() {
        if (createdAt == null) return "";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return createdAt.format(formatter);
    }


//    @Column(nullable = true)
//    private String userName;
//
//    @Column(nullable = true)
//    private String userEmail;



    @Column(nullable = false)
    private Integer numberOfAdults;

    @Column(nullable = false)
    private Integer numberOfChildren;

    @Column(nullable = false)
    private String tourPackage;

    @Column(nullable = false)
    private Integer duration;

    @Column(nullable = true)
    private String destination;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = true)
    private String category;

//    @ManyToOne
//    @JoinColumn(name = "customer_id")
//    private CustomersEntity customer;
//
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id")
    private CustomersEntity customer;

    public QuickBookingEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public CustomersEntity getCustomer() {
        return customer;
    }

    public void setCustomer(CustomersEntity customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "QuickBookingEntity{" +
                "id=" + id +

                ", numberOfAdults=" + numberOfAdults +
                ", numberOfChildren=" + numberOfChildren +
                ", tourPackage='" + tourPackage + '\'' +
                ", duration=" + duration +
                ", destination='" + destination + '\'' +
                ", createdAt=" + createdAt +
                ", category='" + category + '\'' +
                ", customer=" + customer +
                '}';
    }
}



