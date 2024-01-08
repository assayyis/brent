package com.ibnu.brent.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "m_user")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String name;
    private String phoneNumber;
    private String address;

    @OneToOne
    @JoinColumn(name = "user_credential_id")
    private UserCredential userCredentialId;

    @OneToMany(mappedBy = "admin")
    @JsonBackReference
    private List<Issue> adminIssues;

    @OneToMany(mappedBy = "customer")
    @JsonBackReference
    private List<Issue> customerIssues;
}
