package at.alley.hibernate_work.entities;

import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import static org.assertj.core.api.Assertions.assertThat;

public class Book_Simple_Test {

    @Test
    public void create_BookEntity_InMemory() {
        BookEntity book = new BookEntity();
        assertThat(book).isNotNull();
        book.setTitle("George");
        assertThat(book.getTitle()).isEqualTo("George");
    }

    @Test
    public void create_EntityManager() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("BooksDbConnection");
        assertThat(entityManagerFactory).isNotNull();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        assertThat(entityManager).isNotNull();
    }

    private EntityManager getEntityManager()
    {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("BooksDbConnection");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        return entityManager;
    }

    @Test
    public void save_BookEntity_To_Database() {
        EntityManager entityManager = getEntityManager();
        BookEntity bookEntity = new BookEntity("George Title", "George Title Leadtext");
        entityManager.persist(bookEntity);
    }

    @Test
    public void save_BookEntity_And_ReadTheId() {
        EntityManager entityManager = getEntityManager();
        BookEntity bookEntity = new BookEntity("George Title", "George Title Leadtext");
        entityManager.getTransaction().begin();
        entityManager.persist(bookEntity);
        entityManager.getTransaction().commit();;
        assertThat(bookEntity.getId()).isGreaterThan(0);
    }

    @Test
    public void save_BookEntity_And_QueryTheRecord_SameEM() {

        EntityManager entityManager = getEntityManager();
        BookEntity bookEntity = new BookEntity("George Title", "George Title Leadtext");
        entityManager.getTransaction().begin();
        entityManager.persist(bookEntity);
        entityManager.getTransaction().commit();
        Long oldBookId = bookEntity.getId();

        BookEntity getItAgain = entityManager.find(BookEntity.class, oldBookId);
        assertThat(getItAgain.getTitle()).isEqualTo("George Title");
        assertThat(getItAgain.getLeadtext()).isEqualTo("George Title Leadtext");
        assertThat(getItAgain.getId()).isEqualTo(oldBookId);

    }

}
