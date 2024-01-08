package com.ibnu.brent.repository;

import com.ibnu.brent.entity.Review;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
@Transactional
public class ReviewRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public Review save(Review review) {
        review.setId(UUID.randomUUID().toString());
        entityManager.createNativeQuery("INSERT INTO t_review (id, return_id, review) VALUES (?, ?, ?)")
                .setParameter(1, review.getId())
                .setParameter(2, review.getIssueReturn().getId())
                .setParameter(3, review.getReview())
                .executeUpdate();
        return review;
    }
}
