package note.dao;



import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

public interface NotebookDao {
    List<Map<String,Object>>  findNotebooksByUserId(String userId);

    int countNotebookById(String notebookId);

    List<Map<String,Object>>findNotebooksByPage(
      @Param("userId") String userId,
      @Param("start") int start,
      @Param("pageSize") int pageSize,
      @Param("table") String table
    );
}
