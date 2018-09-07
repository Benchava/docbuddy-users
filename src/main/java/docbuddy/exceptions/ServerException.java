package docbuddy.exceptions;

import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@NoArgsConstructor
@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Server Error")
public class ServerException extends RuntimeException {
    public ServerException(Exception e) {
        super(e);
    }
}
