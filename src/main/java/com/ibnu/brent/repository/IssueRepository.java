package com.ibnu.brent.repository;

import com.ibnu.brent.entity.Issue;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
@Transactional
public class IssueRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Issue> findAll() {
        return entityManager.createNativeQuery("SELECT * FROM t_issue", Issue.class)
                .getResultList();
    }

    public Issue findById(String id) {
        List<Issue> issues = entityManager.createNativeQuery("SELECT * FROM t_issue WHERE id = ?", Issue.class)
                .setParameter(1, id)
                .getResultList();
        return issues.isEmpty() ? null : issues.get(0);
    }

    public Issue save(Issue issue) {
        if (issue.getId() == null) {
            issue.setId(UUID.randomUUID().toString());
            entityManager.createNativeQuery("INSERT INTO t_issue (id, admin_id, customer_id, sub_total, issue_date, return_date) VALUES (?, ?, ?, ?, ?, ?)")
                    .setParameter(1, issue.getId())
                    .setParameter(2, issue.getAdmin().getId())
                    .setParameter(3, issue.getCustomer().getId())
                    .setParameter(4, issue.getSubTotal())
                    .setParameter(5, issue.getIssueDate())
                    .setParameter(6, issue.getReturnDate())
                    .executeUpdate();
        } else {
            entityManager.createNativeQuery("UPDATE t_issue SET admin_id = ?, customer_id = ?, sub_total = ?, issue_date = ?, return_date = ? WHERE id = ?")
                    .setParameter(1, issue.getAdmin().getId())
                    .setParameter(2, issue.getCustomer().getId())
                    .setParameter(3, issue.getSubTotal())
                    .setParameter(4, issue.getIssueDate())
                    .setParameter(5, issue.getReturnDate())
                    .setParameter(6, issue.getId())
                    .executeUpdate();
        }
        return issue;
    }
}
