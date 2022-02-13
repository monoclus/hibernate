package at.alley.hibernate_work.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "author")
public class AuthorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String leadtext;

    @ManyToMany(targetEntity = BookEntity.class, mappedBy = "authors")
    private Set<BookEntity> books = new HashSet<>();

    public AuthorEntity() {
    }

    public AuthorEntity(String name, String leadtext) {
        this.name = name;
        this.leadtext = leadtext;
    }

    public String getLeadtext() {
        return leadtext;
    }

    public void setLeadtext(String leadtext) {
        this.leadtext = leadtext;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<BookEntity> getBooks() {
        return books;
    }

    public void setBooks(Set<BookEntity> books) {
        this.books = books;
    }
}
