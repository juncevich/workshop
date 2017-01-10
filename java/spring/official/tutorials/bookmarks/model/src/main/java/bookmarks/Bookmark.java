package bookmarks;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * Created by alex on 10.01.17.
 */
@Entity
public class Bookmark {

    public String uri;
    public String decription;
    @JsonIgnore
    @ManyToOne
    private Account account;
    @Id
    @GeneratedValue
    private Long id;

    public Bookmark() {
    }

    public Bookmark(Account account, String uri, String decription) {
        this.account = account;
        this.uri = uri;
        this.decription = decription;
    }

    public Account getAccount() {
        return account;
    }

    public Long getId() {
        return id;
    }

    public String getUri() {
        return uri;
    }

    public String getDecription() {
        return decription;
    }
}
