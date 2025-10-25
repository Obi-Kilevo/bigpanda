package com.obi.bigpanda.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.Table;
import jakarta.persistence.ElementCollection;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "big_panda_two")
public class PandaTwoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "contact_info", columnDefinition = "TEXT")
    private String contactInfo;

    @ElementCollection
    @Column(name = "pictures")
    private List<String> pictures;

    @Column(name = "travel_strategy", columnDefinition = "TEXT")
    private String travelStrategy;

    @Column(columnDefinition = "TEXT")
    private String adventure;

    @Column(name = "travel_insights", columnDefinition = "TEXT")
    private String travelInsights;

    @Column(name = "why_we_exist", columnDefinition = "TEXT")
    private String whyWeExist;

    @Column(name = "plan_with_us", columnDefinition = "TEXT")
    private String planWithUs;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    public PandaTwoEntity() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    public List<String> getPictures() {
        return pictures;
    }

    public void setPictures(List<String> pictures) {
        this.pictures = pictures;
    }

    public String getTravelStrategy() {
        return travelStrategy;
    }

    public void setTravelStrategy(String travelStrategy) {
        this.travelStrategy = travelStrategy;
    }

    public String getAdventure() {
        return adventure;
    }

    public void setAdventure(String adventure) {
        this.adventure = adventure;
    }

    public String getTravelInsights() {
        return travelInsights;
    }

    public void setTravelInsights(String travelInsights) {
        this.travelInsights = travelInsights;
    }

    public String getWhyWeExist() {
        return whyWeExist;
    }

    public void setWhyWeExist(String whyWeExist) {
        this.whyWeExist = whyWeExist;
    }

    public String getPlanWithUs() {
        return planWithUs;
    }

    public void setPlanWithUs(String planWithUs) {
        this.planWithUs = planWithUs;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "PandaTwoEntity{" +
                "id=" + id +
                ", contactInfo='" + contactInfo + '\'' +
                ", pictures=" + pictures +
                ", travelStrategy='" + travelStrategy + '\'' +
                ", adventure='" + adventure + '\'' +
                ", travelInsights='" + travelInsights + '\'' +
                ", whyWeExist='" + whyWeExist + '\'' +
                ", planWithUs='" + planWithUs + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
