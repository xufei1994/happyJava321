package note.exception;

public class NotebookNoteFoundExcepotion extends RuntimeException {

    public NotebookNoteFoundExcepotion() {
        super();
    }

    public NotebookNoteFoundExcepotion(String message) {
        super(message);
    }

    public NotebookNoteFoundExcepotion(String message, Throwable cause) {
        super(message, cause);
    }

    public NotebookNoteFoundExcepotion(Throwable cause) {
        super(cause);
    }

    protected NotebookNoteFoundExcepotion(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

