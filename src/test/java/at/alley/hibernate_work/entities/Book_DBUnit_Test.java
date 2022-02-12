package at.alley.hibernate_work.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.EntityManager;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class Book_DBUnit_Test {

    @Mock
    private EntityManager entityManager;

    @BeforeEach
    void setUp() {
        Map<Long, BookEntity> booksDB = new TestDataLoader().loadData("datasets/books.yml");
        when(entityManager.find(eq(BookEntity.class), anyLong())).
                then(invocation ->
                        booksDB.get(invocation.getArguments()[1]));
    }

    @Test
    public void save_BookEntity_And_QueryTheRecord_SameEM() {
        BookEntity rec_2 = entityManager.find(BookEntity.class, 2L);
        assertThat(rec_2).isNotNull();
        assertThat(rec_2.getTitle()).isEqualTo("Esofagus");
        assertThat(rec_2.getId()).isEqualTo(2L);
    }

}
