package com.obi.bigpanda.Entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "big_panda_three") // Explicitly map to your table
public class PandaThreeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", nullable = false, length = 100)
    private String firstName;

    @Column(nullable = false, unique = true, length = 255)
    private String email;

    @Column(nullable = false, length = 200)
    private String subject;

    @Column(name = "registration_date", nullable = false)
    private LocalDate registrationDate; // Changed to LocalDate to match table

    @Column(columnDefinition = "TEXT")
    private String notes;

    public PandaThreeEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public String toString() {
        return "PandaThreeEntity{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", email='" + email + '\'' +
                ", subject='" + subject + '\'' +
                ", registrationDate=" + registrationDate +
                ", notes='" + notes + '\'' +
                '}';
    }
}







//@Entity
//public class PandaThreeEntity {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @Column(nullable = false, length = 100)
//    private String firstName;
//
//    @Column(nullable = false, unique = true, length = 255)
//    private String email;
//
//    @Column(nullable = false, length = 200)
//    private String subject;
//
//    @Column(nullable = false)
////    private LocalDate registrationDate;
//    private LocalDateTime registrationDate;
//
//    @Column(columnDefinition = "TEXT")
//    private String notes;
//
//
//    public PandaThreeEntity() {
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getFirstName() {
//        return firstName;
//    }
//
//    public void setFirstName(String firstName) {
//        this.firstName = firstName;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public String getSubject() {
//        return subject;
//    }
//
//    public void setSubject(String subject) {
//        this.subject = subject;
//    }
//
//    public LocalDateTime getRegistrationDate() {
//        return registrationDate;
//    }
//
//    public void setRegistrationDate(LocalDateTime registrationDate) {
//        this.registrationDate = registrationDate;
//    }
//
//    public String getNotes() {
//        return notes;
//    }
//
//    public void setNotes(String notes) {
//        this.notes = notes;
//    }
//
//    @Override
//    public String toString() {
//        return "PandaThreeEntity{" +
//                "id=" + id +
//                ", firstName='" + firstName + '\'' +
//                ", email='" + email + '\'' +
//                ", subject='" + subject + '\'' +
//                ", registrationDate=" + registrationDate +
//                ", notes='" + notes + '\'' +
//                '}';
//    }



