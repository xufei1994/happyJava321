package cn.tedu.note.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.tedu.note.dao.NoteDao;
import cn.tedu.note.dao.NotebookDao;
import cn.tedu.note.service.NoteService;
import cn.tedu.note.service.NotebookNoteFoundExcepotion;

@Service("noteService")
public class NoteServiceImpl 
	implements NoteService{
	
	@Resource
	private NoteDao noteDao;
	
	@Resource
	private NotebookDao notebookDao;
	
	public List<Map<String, Object>> listNotes(
			String notebookId) 
			throws NotebookNoteFoundExcepotion {
		if(notebookId==null || notebookId.trim().isEmpty()){
			throw new NotebookNoteFoundExcepotion("ID为空");
		}
		//Notebook notebook = notebookDao
		//		.findNotebookById(notebookId);
		//if(notebook==null){
		//	throw new NotebookNoteFoundExcepotion("没有笔记本");
		//}
		int n = notebookDao.countNotebookById(
				notebookId);
		if(n!=1){
			throw new NotebookNoteFoundExcepotion("没有笔记本");
		}
		
		return noteDao.findNotesByNotebookId(notebookId);
	}
}



