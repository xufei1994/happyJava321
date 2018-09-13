package note.dao;

import note.entity.Note;
import note.service.NotebookService;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

public interface NoteDao {
    List<Map<String,Object>> findNotesByNotebookId(String notebookId);

    Note findNoteById(String noteId);

    int addNote(Note note);

    int updateNote(Note note);

    List<Map<String, Object>> findDeleteNotesByUserId(String userId);
    int deleteNoteById(String noteId);

    List<Map<String,Object>> findNotes(
            @Param("userId") String userId,
            @Param("notebookId") String notebookId,
            @Param("statusId") String statusId
    );
    int deleteNotes(
            @Param("ids") String... ids);



    /*
    工作原理   调用 findNotes("tom",null,"0")
     <select id="findNotes" parameterType="map" >
        select
        cn_note_id as id ,
        cn_note_title as title
        from
        cn_note
        where
        <if test="userId!=null">
            cn_user_id=#{userId} and
        </if>
        <if test="notebookId!=null">
            cn_notebook_id = #{notebookId} and
        </if>
        cn_note_status_id=#{statusId}
        order by
        cn_note_last_modify_time desc
    </select>
     */
}
