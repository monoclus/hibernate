package at.alley.hibernate_work.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.parallel.ExecutionMode.SAME_THREAD;

@Execution(SAME_THREAD)
public class Book_Composite_Test {

    EntityManager entityManager;

    @BeforeEach
    protected void getEntityManager()
    {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("BooksDbConnection");
        this.entityManager = entityManagerFactory.createEntityManager();
    }

    private Long insertBook() {
        BookEntity bookEntity = new BookEntity("George Title", "George Title Leadtext");

        bookEntity.getTags().add("tag1");
        bookEntity.getTags().add("tag2");
        bookEntity.getTags().add("tag3");

        bookEntity.getWords().add("word1");
        bookEntity.getWords().add("word2");
        bookEntity.getWords().add("word3");
        bookEntity.getWords().add("word4");

        entityManager.getTransaction().begin();
        entityManager.persist(bookEntity);
        entityManager.getTransaction().commit();;

        assertThat(bookEntity.getId()).isGreaterThan(0);

        return bookEntity.getId();
    }

    @Test
    public void insert_composite_BookEntity_And_ReadTheId() {
        Long id = insertBook();
        entityManager.clear();

        entityManager.getTransaction().begin();
        BookEntity bookEntity = entityManager.find(BookEntity.class, id);
        entityManager.getTransaction().commit();

        assertThat(bookEntity).isNotNull();
        assertThat(bookEntity.getTags()).hasSize(3);
        assertThat(bookEntity.getWords()).hasSize(4);
    }

    @Test
    public void update_composite_BookEntity_And_ReadTheId() {





    }

}
