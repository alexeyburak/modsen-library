package com.modsen.book.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "registries")
public class BookRegistry {

    @Id
    private UUID id;

    @ManyToOne(fetch = FetchType.EAGER)
    private Book book;

    @Column(name = "borrowed_at", nullable = false)
    private LocalDate borrowedAt;

    @Column(name = "return_at", nullable = false)
    private LocalDate returnAt;
}
