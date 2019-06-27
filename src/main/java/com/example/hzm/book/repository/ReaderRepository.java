package com.example.hzm.book.repository;

import com.example.hzm.book.entity.Reader;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReaderRepository
        extends JpaRepository<Reader, String> {
}
