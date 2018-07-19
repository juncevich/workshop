package bookmarks;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by alex on 10.01.17.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String userId) {
        super("could nor find user '" + userId + "'.");
    }
}
