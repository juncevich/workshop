package hello;

/**
 * Created by alex
 * on 03 Jan 2017.
 */
public class Greeting {
    private final long id;
    private final String content;

    Greeting(long id, String content) {
        this.id = id;
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }
}
