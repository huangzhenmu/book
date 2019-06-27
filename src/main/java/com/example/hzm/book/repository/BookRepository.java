package com.example.hzm.book.repository;

import com.example.hzm.book.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book,Long> {
    List<Book> findByReader(String reader);
}
