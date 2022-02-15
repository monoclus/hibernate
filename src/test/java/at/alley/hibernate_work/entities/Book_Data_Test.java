package at.alley.hibernate_work.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.*;

import static org.assertj.core.api.Assertions.assertThat;

public class Book_Data_Test {

    EntityManager entityManager;

    @BeforeEach
    public void createEntityManager() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("BooksDbConnection");
        assertThat(entityManagerFactory).isNotNull();
        this.entityManager = entityManagerFactory.createEntityManager();
        assertThat(entityManager).isNotNull();
    }

    private Long createBook() {
        entityManager.getTransaction().begin();
        BookEntity oldBookEntity = new BookEntity("Book 3", "Leadtext 3");
        entityManager.persist(oldBookEntity);
        entityManager.getTransaction().commit();
        return oldBookEntity.getId();
    }

    @Test
    public void create_Book_And_Reload_It() {

        Long bookId = createBook();
        entityManager.clear();

        entityManager.getTransaction().begin();
        BookEntity newBookEntity = entityManager.find(BookEntity.class, bookId);
        assertThat(newBookEntity).isNotNull();
        assertThat(newBookEntity.getId()).isEqualTo(bookId);
        entityManager.getTransaction().commit();

    }

    @Test
    public void test_JPQL() {
        createBook();

        Query query = entityManager.createQuery("select count(b) from BookEntity b");
        Long count = (Long) query.getSingleResult();
        assertThat(count).isGreaterThan(0);
    }
}
