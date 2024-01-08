package com.ibnu.brent.repository;

import com.ibnu.brent.entity.Book;
import com.ibnu.brent.entity.IssueDetail;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
@Transactional
public class IssueDetailRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public List<IssueDetail> findAll() {
        return entityManager.createNativeQuery("SELECT * FROM t_issue_detail", IssueDetail.class)
                .getResultList();
    }

    public List<IssueDetail> findByIssueId(String issueId) {
        return entityManager.createNativeQuery("SELECT * FROM t_issue_detail WHERE issue_id = ?", IssueDetail.class)
                .setParameter(1, issueId)
                .getResultList();
    }

    public List<IssueDetail> saveAll(List<IssueDetail> issueDetails) {
        issueDetails.forEach(issueDetail -> {
            entityManager.createNativeQuery("INSERT INTO t_issue_detail (id, issue_id, book_id) VALUES (?, ?, ?)")
                    .setParameter(1, UUID.randomUUID().toString())
                    .setParameter(2, issueDetail.getIssue().getId())
                    .setParameter(3, issueDetail.getBook().getId())
                    .executeUpdate();
        });
        return findByIssueId(issueDetails.get(0).getIssue().getId());
    }
}
