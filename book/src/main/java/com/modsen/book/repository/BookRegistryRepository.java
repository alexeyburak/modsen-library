package com.modsen.book.repository;

import com.modsen.book.model.BookRegistry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BookRegistryRepository extends JpaRepository<BookRegistry, UUID> {
}
