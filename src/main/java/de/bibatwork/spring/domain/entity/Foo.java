package de.bibatwork.spring.domain.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.GenerationType;
import lombok.*;

@Entity
@Value
@EqualsAndHashCode(of = "id")
@RequiredArgsConstructor
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
public class Foo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false)
    String title;

    @Column(nullable = false)
    String body;

    @Override
    public String toString() {
        return "Foo [id=" + id + ", title=" + title + "]";
    }
}
