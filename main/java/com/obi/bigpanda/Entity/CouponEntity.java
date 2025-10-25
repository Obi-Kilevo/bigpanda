package com.obi.bigpanda.Entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.math.BigDecimal;

@Entity
@Table(name = "coupons")
public class CouponEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "park_id")
    private Long parkId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "park_id", insertable = false, updatable = false)
    private AdminEntity park;

    @Column(name = "discount_percent")
    private BigDecimal discountPercent;

    @Column(name = "expiry_date")
    private LocalDateTime expiryDate;





    @Column(name = "is_active", nullable = false)
    private boolean isActive = true;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Transient
    private long daysUntilExpiry;



    @Column(name = "status_override")
    private String statusOverride;



    @Transient
    private CouponEntity activeCoupon;


    public CouponEntity() {}

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

    public AdminEntity getPark() {
        return park;
    }

    public void setPark(AdminEntity park) {
        this.park = park;
    }

    public BigDecimal getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(BigDecimal discountPercent) {
        this.discountPercent = discountPercent;
    }

    public LocalDateTime getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDateTime expiryDate) {
        this.expiryDate = expiryDate;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

//    public long getDaysUntilExpiry() {
//        return daysUntilExpiry;
//    }

    public void setStatusOverride(String statusOverride) {
        this.statusOverride = statusOverride;
    }

    public void setDaysUntilExpiry(long daysUntilExpiry) {
        this.daysUntilExpiry = daysUntilExpiry;
    }

    public String getStatusOverride() {
        return statusOverride;
    }


    public String getDaysUntilExpiry() {
        System.out.println("=== DEBUG getDaysUntilExpiry ===");
        System.out.println("statusOverride: " + statusOverride);
        System.out.println("expiryDate: " + expiryDate);

        // First check if admin set custom override text
        if (statusOverride != null && !statusOverride.trim().isEmpty()) {
            System.out.println("Returning statusOverride: " + statusOverride);
            return statusOverride;
        }

        if (expiryDate == null) {
            System.out.println("Returning 'No expiry' because expiryDate is null");
            return "No expiry";
        }

        long days = java.time.Duration.between(LocalDateTime.now(), expiryDate).toDays();
        System.out.println("Days calculated: " + days);

        if (days < 0) {
            System.out.println("Returning 'Expired'");
            return "Expired";
        } else {
            System.out.println("Returning days left: " + days);
            return days + " days left";
        }
    }



    @Override
    public String toString() {
        return "CouponEntity{" +
                "id=" + id +
                ", parkId=" + parkId +
                ", park=" + park +
                ", discountPercent=" + discountPercent +
                ", expiryDate=" + expiryDate +
                ", isActive=" + isActive +
                ", createdAt=" + createdAt +
                ", description='" + description + '\'' +
                ", daysUntilExpiry=" + daysUntilExpiry +
                ", statusOverride='" + statusOverride + '\'' +
                '}';
    }
}