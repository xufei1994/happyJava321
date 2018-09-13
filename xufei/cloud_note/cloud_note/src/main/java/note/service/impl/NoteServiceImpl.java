package note.service.impl;

import note.dao.NoteDao;
import note.dao.NotebookDao;
import note.dao.StarsDao;
import note.dao.UserDao;
import note.entity.Note;
import note.entity.Stars;
import note.entity.User;
import note.exception.NoteNotFoundException;
import note.exception.NotebookNotFoundException;
import note.exception.NotebookNoteFoundExcepotion;
import note.exception.UserNotFoundException;
import note.service.NoteService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service("noteService")
@Transactional
public class NoteServiceImpl implements NoteService {

    @Resource
    private NoteDao noteDao;

    @Resource
    private UserDao userDao;
    @Resource
    private StarsDao starsDao;

    @Resource
    private NotebookDao notebookDao;

    public List<Map<String, Object>> listNotes(String notebookId)
            throws NotebookNoteFoundExcepotion {
        if(notebookId==null || notebookId.trim().isEmpty()){
            throw new NotebookNoteFoundExcepotion("ID为空");
        }
//        Notebook notebook = notebookDao
//              .findNotebookById(notebookId);
//        if(notebook==null){
//          throw new NotebookNoteFoundExcepotion("没有笔记本");
//        }
        //System.out.println("notebookId:"+notebookId);  //打桩
        int n = notebookDao.countNotebookById(notebookId);
        //System.out.println("n:"+n);  //打桩
        if(n!=1){
            throw new NotebookNoteFoundExcepotion("没有笔记本");
        }

       // return noteDao.findNotesByNotebookId(notebookId);
        return noteDao.findNotes(null,notebookId,"1");
    }

    @Override
    public Note getNote(String noteId) throws NoteNotFoundException {
        if (noteId==null||noteId.trim().isEmpty()){
            throw new NoteNotFoundException("Id为空");
        }
        Note note = noteDao.findNoteById(noteId);
    if (note==null){
        throw new NoteNotFoundException("id错误");
    }
    return note;
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
        //当前的实物，会传播到addStars方法中 整合为一个事务
        addStars(userId,5);
        return note;
    }

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
        return  noteDao.findNotes(userId,null,"0");
    }

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

    //...  []  都是数组
    @Override
    @Transactional
    public int deleteNotes(String... noteIds) throws NoteNotFoundException {
//        for (String id:noteIds){
//        int n= noteDao.deleteNoteById(id);
//        if (n!=1){
//            throw  new NoteNotFoundException("id错误");
//        }
//        }
//        return noteIds.length;
        int n=noteDao.deleteNotes(noteIds);
        if (n!=noteIds.length){
            throw new NoteNotFoundException("Id 错误");
        }
        return  n;
    }

    @Transactional
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
