package at.alley.hibernate_work.entities;

import com.github.database.rider.core.DBUnitRule;
import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.core.util.EntityManagerProvider;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static com.github.database.rider.core.util.EntityManagerProvider.clear;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author ibinissac
 * @createdOn 12/02/22
 * @email services@impezar.com
 * @company Impezar Private Limited Kochi India
 */

@RunWith(JUnit4.class)
public class Book_DBUnit_Test {
    @Rule
    public EntityManagerProvider emProvider = EntityManagerProvider.instance("booksDB");

    @Rule
    public DBUnitRule dbUnitRule = DBUnitRule.instance(emProvider.connection());


    @Test
    @DataSet(value="books.yml")
    public void save_BookEntity_And_QueryTheRecord_SameEM() {
        BookEntity firstBook = (BookEntity) clear("booksDB").
                em().createQuery("select b from BookEntity b  where b.id = 1").
                getSingleResult();
        BookEntity secondBook = (BookEntity) clear("booksDB").
                em().createQuery("select b from BookEntity b  where b.id = 2").
                getSingleResult();
        BookEntity thirdBook = (BookEntity) clear("booksDB").
                em().createQuery("select b from BookEntity b  where b.id = 3").
                getSingleResult();
        assertThat(firstBook).isNotNull();
        assertThat(firstBook.getId()).isEqualTo(1l);
        assertThat(firstBook.getTitle()).isEqualTo("Mama Mia");
        assertThat(firstBook.getLeadtext()).isEqualTo("Mama Mia Long text");

        assertThat(secondBook).isNotNull();
        assertThat(secondBook.getId()).isEqualTo(2l);
        assertThat(secondBook.getTitle()).isEqualTo("Esofagus");
        assertThat(secondBook.getLeadtext()).isEqualTo("Esofagus Long text");


        assertThat(thirdBook).isNotNull();
        assertThat(thirdBook.getId()).isEqualTo(3l);
        assertThat(thirdBook.getTitle()).isEqualTo("Manduras");
        assertThat(thirdBook.getLeadtext()).isEqualTo("Manduras Long text");
    }

}
