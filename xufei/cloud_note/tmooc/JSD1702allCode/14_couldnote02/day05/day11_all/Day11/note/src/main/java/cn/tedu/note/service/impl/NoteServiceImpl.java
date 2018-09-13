package cn.tedu.note.service.impl;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.tedu.note.dao.NoteDao;
import cn.tedu.note.dao.NotebookDao;
import cn.tedu.note.dao.StarsDao;
import cn.tedu.note.dao.UserDao;
import cn.tedu.note.entity.Note;
import cn.tedu.note.entity.Stars;
import cn.tedu.note.entity.User;
import cn.tedu.note.service.NoteNotFoundException;
import cn.tedu.note.service.NoteService;
import cn.tedu.note.service.NotebookNotFoundException;
import cn.tedu.note.service.NotebookNoteFoundExcepotion;
import cn.tedu.note.service.UserNotFoundException;

@Service("noteService")
//@Transactional
public class NoteServiceImpl 
	implements NoteService{
	
	@Resource
	private NoteDao noteDao;
	
	@Resource
	private UserDao userDao;
	
	@Resource
	private NotebookDao notebookDao;
	
	@Transactional
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
		
		//return noteDao.findNotesByNotebookId(notebookId);
		return noteDao.findNotes(null, notebookId, "1");
	}
	
	@Transactional
	public Note getNote(String noteId)
			throws NoteNotFoundException {
		if(noteId==null||noteId.trim().isEmpty()){
			throw new NoteNotFoundException("ID空");
		}
		Note note = noteDao.findNoteById(noteId);
		if(note==null){
			throw new NoteNotFoundException("id错误");
		}
		return note;
	}
	
	@Transactional(isolation=Isolation.READ_COMMITTED)
	public boolean update(String noteId, String title, 
			String body) throws NoteNotFoundException {
		if(noteId==null || noteId.trim().isEmpty()){
			throw new NoteNotFoundException("ID不能空");
		}
		Note note = noteDao.findNoteById(noteId);
		if(note==null){
			throw new NoteNotFoundException("没有对应的笔记");
		} 
		Note data = new Note();
		if(title!=null && !title.equals(note.getTitle())){
			data.setTitle(title);
		}
		if(body!=null && !body.equals(note.getBody())){
			data.setBody(body);
		}
		data.setId(noteId);
		data.setLastModifyTime(System.currentTimeMillis());
		System.out.println(data); 
		int n = noteDao.updateNote(data);
		return n==1;
		 
	}
	@Transactional
	public Note addNote(String userId, 
			String notebookId, String title)
			throws UserNotFoundException, 
			NotebookNotFoundException {
	
		if(userId==null||userId.trim().isEmpty()){
			throw new UserNotFoundException("ID空");
		}
		User user=userDao.findUserById(userId);
		if(user==null){
			throw new UserNotFoundException("木有人");
		}
		if(notebookId==null||notebookId.trim().isEmpty()){
			throw new NotebookNotFoundException("ID空");
		}
		int n=notebookDao.countNotebookById(notebookId);
		if(n!=1){
			throw new NotebookNotFoundException("没有笔记本");
		}
		if(title==null || title.trim().isEmpty()){
			title="葵花宝典";
		}
		String id = UUID.randomUUID().toString();
		String statusId = "1";
		String typeId = "1";
		String body = "";
		long time=System.currentTimeMillis();
		Note note = new Note(id, notebookId,
			userId, statusId, typeId, title, 
			body, time, time);
		n = noteDao.addNote(note);
		if(n!=1){
			throw new NoteNotFoundException("保存失败");
		}
		//当前的事务, 会传播到 addStart方法中
		//整合为一个事务!
		addStars(userId, 5);
		
		return note;
	}
	@Transactional
	public boolean moveNote(String noteId, String notebookId)
			throws NoteNotFoundException, NotebookNotFoundException {
		if(noteId==null || noteId.trim().isEmpty()){
			throw new NoteNotFoundException("ID不能空");
		}
		Note note = noteDao.findNoteById(noteId);
		if(note==null){
			throw new NoteNotFoundException("没有对应的笔记");
		} 
		if(notebookId==null||notebookId.trim().isEmpty()){
			throw new NotebookNotFoundException("ID空");
		}
		int n=notebookDao.countNotebookById(notebookId);
		if(n!=1){
			throw new NotebookNotFoundException("没有笔记本");
		}
		
		Note data = new Note();
		data.setId(noteId);
		data.setNotebookId(notebookId);
		data.setLastModifyTime(System.currentTimeMillis());
		
		n = noteDao.updateNote(data);
		
		return n==1;
	}
	@Transactional
	public boolean deleteNote(String noteId) throws NoteNotFoundException {
		if(noteId==null || noteId.trim().isEmpty()){
			throw new NoteNotFoundException("ID不能空");
		}
		Note note = noteDao.findNoteById(noteId);
		if(note==null){
			throw new NoteNotFoundException("没有对应的笔记");
		} 
		
		Note data = new Note();
		data.setId(noteId);
		data.setStatusId("0");
		data.setLastModifyTime(System.currentTimeMillis());
		
		int n = noteDao.updateNote(data);
		
		return n==1;
	}
	@Transactional(readOnly=true)
	public List<Map<String, Object>> listNotesInTrashBin(
			String userId) throws UserNotFoundException {
		if(userId==null||userId.trim().isEmpty()){
			throw new UserNotFoundException("ID空");
		}
		User user=userDao.findUserById(userId);
		if(user==null){
			throw new UserNotFoundException("木有人");
		}
		//return noteDao.findDeleteNotesByUserId(userId);
		return noteDao.findNotes(userId, null, "0");
	}
	@Transactional
	public boolean replayNote(String noteId, String notebookId)
			throws NoteNotFoundException, NotebookNotFoundException {
		if(noteId==null || noteId.trim().isEmpty()){
			throw new NoteNotFoundException("ID不能空");
		}
		Note note = noteDao.findNoteById(noteId);
		if(note==null){
			throw new NoteNotFoundException("没有对应的笔记");
		} 
		if(notebookId==null||notebookId.trim().isEmpty()){
			throw new NotebookNotFoundException("ID空");
		}
		int n=notebookDao.countNotebookById(notebookId);
		if(n!=1){
			throw new NotebookNotFoundException("没有笔记本");
		}
		
		Note data = new Note();
		data.setId(noteId);
		data.setStatusId("1");
		data.setNotebookId(notebookId);
		data.setLastModifyTime(System.currentTimeMillis());
		
		n = noteDao.updateNote(data);
		
		return n==1;
	}
	
	//NoteServiceImpl
	@Transactional
	public int deleteNotes(String... noteIds) 
			throws NoteNotFoundException {
		//for(String id: noteIds){
		//	int n=noteDao.deleteNoteById(id);
		//	if(n!=1){
		//		throw new NoteNotFoundException("ID错误"); 
		//	}
		//}
		//return noteIds.length;
		int n = noteDao.deleteNotes(noteIds);
		if(n!=noteIds.length){
			throw new NoteNotFoundException("ID错误"); 
		}
		return n;
	}
	
	//NoteServiceImpl
	
	@Resource
	private StarsDao starsDao;
	
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public boolean addStars(String userId, int stars) 
			throws UserNotFoundException {
		if(userId==null||userId.trim().isEmpty()){
			throw new UserNotFoundException("ID空");
		}
		User user=userDao.findUserById(userId);
		if(user==null){
			throw new UserNotFoundException("木有人");
		}
		//检查是否已经有星了
		Stars st=starsDao.findStarsByUserId(userId);
		if(st==null){//如果没有星星
			String id = UUID.randomUUID().toString();
			st = new Stars(id, userId, stars);
			int n = starsDao.insertStars(st);
			if(n!=1){
				throw new RuntimeException("失败");
			}
		}else{//如果有星星,就在现有星星数量上增加
			int n = st.getStars()+stars;
			if(n<0){
				// n = 0; 
				throw new RuntimeException("扣分太多!");
			}
			st.setStars(n);
			n = starsDao.updateStars(st);
			if(n!=1){
				throw new RuntimeException("失败");
			}
		}
		return true;
	}
	
}













