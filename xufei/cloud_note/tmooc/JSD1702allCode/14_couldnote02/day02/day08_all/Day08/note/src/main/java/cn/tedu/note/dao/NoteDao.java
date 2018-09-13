package cn.tedu.note.dao;

import java.util.List;
import java.util.Map;

import cn.tedu.note.entity.Note;

public interface NoteDao {
	List<Map<String,Object>> 
		findNotesByNotebookId(
		String notebookId);
	
	Note findNoteById(String noteId);
	
	int updateNote(Note note);
	
	int addNote(Note note);

	List<Map<String, Object>> findDeleteNotesByUserId(String userId);

}
