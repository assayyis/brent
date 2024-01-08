package com.ibnu.brent.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.ibnu.brent.constant.EBookStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "t_issue")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class Issue {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private Long subTotal;
    private Date issueDate;
    private Date returnDate;

    @ManyToOne
    @JoinColumn(name = "admin_id")
    private User admin;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private User customer;

    @OneToMany(mappedBy = "issue")
    @JsonBackReference
    private List<IssueDetail> issueDetail;

    @OneToOne(mappedBy = "issue")
    @JsonBackReference
    private Return issueReturn;
}
