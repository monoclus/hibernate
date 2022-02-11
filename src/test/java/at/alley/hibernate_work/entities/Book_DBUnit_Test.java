package at.alley.hibernate_work.entities;

import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

public class Book_DBUnit_Test {

    private EntityManager getEntityManager()
    {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("BooksDbConnection");
        return Objects.requireNonNull(entityManagerFactory.createEntityManager());
    }

    @Test
    public void save_BookEntity_And_QueryTheRecord_SameEM() {

        // TODO: Populate the database with DBUnit
        //       Source: src/test/resources/datasets/books.yml

        EntityManager entityManager = getEntityManager();
        BookEntity rec_2 = entityManager.find(BookEntity.class, 2L);
        assertThat(rec_2).isNotNull();
        assertThat(rec_2.getTitle()).isEqualTo("Esofagus");
        assertThat(rec_2.getId()).isEqualTo(2L);

    }

}
