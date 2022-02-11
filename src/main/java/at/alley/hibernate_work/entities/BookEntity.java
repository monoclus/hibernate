package at.alley.hibernate_work.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class BookEntity {

    @Id
    @GeneratedValue
    private Long id;

    private String title;

    private String leadtext;

    public BookEntity() {
    }

    public BookEntity(String title, String leadtext) {
        this.title = title;
        this.leadtext = leadtext;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
}
