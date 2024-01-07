package com.ibnu.brent.repository;

import com.ibnu.brent.entity.Role;
import com.ibnu.brent.entity.UserCredential;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Transactional
public class UserCredentialRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public UserCredential save(UserCredential userCredential) {
        if (userCredential.getId() == null) {
            entityManager.persist(userCredential);
            return userCredential;
        } else {
            return entityManager.merge(userCredential);
        }
    }

    public Optional<UserCredential> findById(String id) {
        return Optional.ofNullable(entityManager.find(UserCredential.class, id));
    }

    public Optional<UserCredential> findByUsername(String username) {
        return entityManager
                .createNativeQuery("SELECT * FROM m_user_credential WHERE username = :username", UserCredential.class)
                .setParameter("username", username)
                .getResultList()
                .stream()
                .findFirst();
    }

//    Optional<UserCredential> findByUsername(String username);
//
//    @Query(value = "SELECT * FROM user_credentials WHERE username = :username", nativeQuery = true)
//    Optional<UserCredential> findByUsernameNativeQuery(@Param("username") String username);
}
