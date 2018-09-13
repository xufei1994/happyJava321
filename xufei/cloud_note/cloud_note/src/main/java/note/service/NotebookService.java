package note.service;

import note.exception.UserNotFoundException;

import java.util.List;
import java.util.Map;

public interface NotebookService {
    List <Map<String,Object>> listNotebooks(String userId) throws UserNotFoundException;
    List <Map<String,Object>> listNotebooks(String userId,Integer page) throws UserNotFoundException;
}
