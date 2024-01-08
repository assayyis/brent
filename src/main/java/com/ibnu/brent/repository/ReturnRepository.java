package com.ibnu.brent.repository;

import com.ibnu.brent.entity.Issue;
import com.ibnu.brent.entity.Return;
import jakarta.persistence.EntityManager;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.UUID;

@Repository
@Transactional
public class ReturnRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public Return save(Return issueReturn) {
        if (issueReturn.getId() == null) {
            issueReturn.setId(UUID.randomUUID().toString());
            entityManager.createNativeQuery("INSERT INTO t_return (id, penalty, return_date, issue_id) VALUES (?, ?, ?, ?)")
                    .setParameter(1, issueReturn.getId())
                    .setParameter(2, issueReturn.getPenalty())
                    .setParameter(3, issueReturn.getReturnDate())
                    .setParameter(4, issueReturn.getIssue().getId())
                    .executeUpdate();
        } else {
            entityManager.createNativeQuery("UPDATE t_return SET penalty = ?, return_date = ?, issue_id = ? WHERE id = ?")
                    .setParameter(1, issueReturn.getPenalty())
                    .setParameter(2, issueReturn.getReturnDate())
                    .setParameter(3, issueReturn.getIssue().getId())
                    .setParameter(4, issueReturn.getId())
                    .executeUpdate();
        }
        return issueReturn;
    }
}
