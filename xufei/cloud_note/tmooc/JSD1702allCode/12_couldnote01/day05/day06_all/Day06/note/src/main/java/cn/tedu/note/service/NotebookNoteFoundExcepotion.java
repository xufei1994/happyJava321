package cn.tedu.note.service;

public class NotebookNoteFoundExcepotion extends RuntimeException {
	private static final long serialVersionUID = 8307029916454948448L;

	public NotebookNoteFoundExcepotion() {
		super();
	}

	public NotebookNoteFoundExcepotion(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public NotebookNoteFoundExcepotion(String message, Throwable cause) {
		super(message, cause);
	}

	public NotebookNoteFoundExcepotion(String message) {
		super(message);
	}

	public NotebookNoteFoundExcepotion(Throwable cause) {
		super(cause);
	}
	
}
