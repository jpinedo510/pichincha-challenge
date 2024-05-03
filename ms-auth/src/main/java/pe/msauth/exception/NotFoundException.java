package pe.msauth.exception;

public class NotFoundException extends RuntimeException {

    private static final long serialVersionUID = -5669337199585645702L;

    public NotFoundException(String message) {
        super(message);
    }
}
