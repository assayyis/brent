package com.ibnu.brent.repository;

import com.ibnu.brent.constant.EBookStatus;
import com.ibnu.brent.entity.Book;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

//List<Book> findAll();
//List<Book> findByName(String name);
//Book findById(String id);
//
//Book create(Book book);
//Book update(Book book);
//void delete(String id);
@Repository
@Transactional
public class BookRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public Book save(Book book) {
        if (book.getId() == null) {
            entityManager.createNativeQuery("INSERT INTO m_book (title, description, image, author, price, status, id) VALUES (?, ?, ?, ?, ?, ?, ?)")
                    .setParameter(1, book.getTitle())
                    .setParameter(2, book.getDescription())
                    .setParameter(3, book.getImage())
                    .setParameter(4, book.getAuthor())
                    .setParameter(5, book.getPrice())
                    .setParameter(6, book.getStatus().name())
                    .setParameter(7, UUID.randomUUID().toString())
                    .executeUpdate();
        } else {
            entityManager.createNativeQuery("UPDATE m_book SET title = ?, description = ?, image = ?, author = ?, price = ?, status = ? WHERE id = ?")
                    .setParameter(1, book.getTitle())
                    .setParameter(2, book.getDescription())
                    .setParameter(3, book.getImage())
                    .setParameter(4, book.getAuthor())
                    .setParameter(5, book.getPrice())
                    .setParameter(6, book.getStatus().name())
                    .setParameter(7, book.getId())
                    .executeUpdate();
        }
        return book;
    }

    public Book findById(String id) {
        List<Book> books = entityManager.createNativeQuery("SELECT * FROM m_book WHERE id = ?", Book.class)
                .setParameter(1, id)
                .getResultList();
        return books.isEmpty() ? null : books.get(0);
    }

    public List<Book> findByTitle(String title) {
        return entityManager.createNativeQuery("SELECT * FROM m_book WHERE title LIKE ?", Book.class)
                .setParameter(1, "%" + title + "%")
                .getResultList();
    }

    public List<Book> findAll() {
        return entityManager.createNativeQuery("SELECT * FROM m_book", Book.class)
                .getResultList();
    }

    public void delete(String id) {
        entityManager.createNativeQuery("DELETE FROM m_book WHERE id = ?")
                .setParameter(1, id)
                .executeUpdate();
    }
}
