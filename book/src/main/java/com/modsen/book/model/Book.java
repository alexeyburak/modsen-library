package com.modsen.book.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "books")
public class Book {

    @Id
    private UUID id;

    @Column(name = "title", unique = true, nullable = false)
    private String title;

    @Column(name = "isbn", unique = true, nullable = false)
    private String isbn;

    @Column(name = "genre", nullable = false)
    private String genre;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "author", nullable = false)
    private String author;

    @OneToMany(mappedBy = "book", fetch = FetchType.EAGER)
    private Set<BookRegistry> bookRegistries;
}
