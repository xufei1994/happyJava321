package note.exception;

public class NotebookNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 4695069085162939857L;

    public NotebookNotFoundException() {
        super();
    }

    public NotebookNotFoundException(String message) {
        super(message);
    }

    public NotebookNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotebookNotFoundException(Throwable cause) {
        super(cause);
    }

    protected NotebookNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
