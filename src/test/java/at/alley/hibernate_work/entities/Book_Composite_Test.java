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

    @Test
    public void save_composite_BookEntity_And_ReadTheId() {

        BookEntity bookEntity = new BookEntity("George Title", "George Title Leadtext");
        bookEntity.getTags().add("tag1");
        bookEntity.getTags().add("tag2");
        bookEntity.getTags().add("tag3");

        entityManager.getTransaction().begin();
        entityManager.persist(bookEntity);
        entityManager.getTransaction().commit();;

        assertThat(bookEntity.getId()).isGreaterThan(0);

        long id = bookEntity.getId();

        entityManager.getTransaction().begin();
        bookEntity = entityManager.find(BookEntity.class, id);
        assertThat(bookEntity.getTags()).hasSize(3);

    }

}
