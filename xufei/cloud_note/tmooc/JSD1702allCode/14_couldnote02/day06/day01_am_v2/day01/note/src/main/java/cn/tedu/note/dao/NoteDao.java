package cn.tedu.note.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cn.tedu.note.entity.Note;

public interface NoteDao {
	List<Map<String,Object>> 
		findNotesByNotebookId(
		String notebookId);
	
	Note findNoteById(String noteId);
	
	int updateNote(Note note);
	
	int addNote(Note note);

	List<Map<String, Object>> 
		findDeleteNotesByUserId(String userId);
	
	int deleteNoteById(String noteId);
	
	int deleteNotes(
		@Param("ids") String... ids);
	
	List<Map<String, Object>> findNotes(
		@Param("userId") String userId, 
		@Param("notebookId") String notebookId, 
		@Param("statusId") String statusId);
}




