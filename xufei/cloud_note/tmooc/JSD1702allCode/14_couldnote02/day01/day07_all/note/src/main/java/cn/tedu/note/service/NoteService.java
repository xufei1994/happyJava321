package cn.tedu.note.service;

import java.util.List;
import java.util.Map;

import cn.tedu.note.entity.Note;

public interface NoteService {
	
	List<Map<String, Object>> listNotes(String notebookId)
		throws NotebookNoteFoundExcepotion;

	Note getNote(String noteId)
			throws NoteNotFoundException;
	
	boolean update(String noteId, 
			String title, String body)
		throws NoteNotFoundException;
	
	public Note addNote(String userId, 
			String notebookId, String title)
			throws UserNotFoundException,
			NotebookNotFoundException;

	boolean moveNote(String noteId, String notebookId)
			throws NoteNotFoundException,
			NotebookNotFoundException;

	boolean deleteNote(String noteId)
			throws NoteNotFoundException;

}
