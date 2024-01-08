package com.ibnu.brent.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "t_return")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class Return {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private Long penalty;
    private Date returnDate;

    @OneToOne
    @JoinColumn(name = "issue_id")
    private Issue issue;

    @OneToOne(mappedBy = "issueReturn")
    @JsonBackReference
    private Review review;
}
