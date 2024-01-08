package com.ibnu.brent.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.ibnu.brent.constant.EBookStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "m_book")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String title;
    private String description;
    private String image;
    private String author;
    private Long price;

    @Enumerated(EnumType.STRING)
    private EBookStatus status;

    @OneToMany(mappedBy = "book")
    @JsonBackReference
    private List<IssueDetail> issueDetail;
}
