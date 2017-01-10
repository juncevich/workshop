package bookmarks;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by alex on 10.01.17.
 */
@Entity
public class Account {

    public String username;
    @JsonIgnore
    public String password;
    @OneToMany(mappedBy = "account")
    private Set<Bookmark> bookmarks = new HashSet();
    @Id
    @GeneratedValue
    private Long id;

    public Account() {
    }

    public Account(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Bookmark> getBookmarks() {
        return bookmarks;
    }

    public Long getId() {
        return id;
    }
}
