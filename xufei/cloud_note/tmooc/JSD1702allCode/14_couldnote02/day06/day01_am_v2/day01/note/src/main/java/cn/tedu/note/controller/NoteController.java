package cn.tedu.note.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tedu.note.entity.Note;
import cn.tedu.note.service.NoteService;
import cn.tedu.note.util.JsonResult;

@Controller
@RequestMapping("/note")
public class NoteController extends AbstractController {

	@Resource
	private NoteService noteService;

	@RequestMapping("/list.do")
	@ResponseBody
	public JsonResult list(String notebookId) {
		List<Map<String, Object>> list = noteService.listNotes(notebookId);
		return new JsonResult(list);
	}

	@RequestMapping("/trash.do")
	@ResponseBody
	public JsonResult trashBin(String userId) {
		List<Map<String, Object>> list = noteService.listNotesInTrashBin(userId);
		return new JsonResult(list);
	}

	@RequestMapping("/load.do")
	@ResponseBody
	public JsonResult load(String noteId) {
		Note note = noteService.getNote(noteId);
		return new JsonResult(note);
	}

	@RequestMapping("/update.do")
	@ResponseBody
	public JsonResult update(String noteId, String title, String body) {
		boolean success = noteService.update(noteId, title, body);
		return new JsonResult(success);
	}

	@RequestMapping("/add.do")
	@ResponseBody
	public JsonResult add(String userId, String notebookId, String title) {
		Note note = noteService.addNote(userId, notebookId, title);
		return new JsonResult(note);
	}
	@RequestMapping("/move.do")
	@ResponseBody
	public JsonResult move(String noteId, String notebookId) {
		boolean b = noteService.moveNote(noteId, notebookId);
		return new JsonResult(b);
	}

	@RequestMapping("/delete.do")
	@ResponseBody
	public JsonResult delete(String noteId) {
		boolean b = noteService.deleteNote(
				noteId);
		return new JsonResult(b);
	}
	
	@RequestMapping("/replay.do")
	@ResponseBody
	public JsonResult replay(String noteId, String notebookId) {
		boolean b = noteService.replayNote(
				noteId, notebookId);
		return new JsonResult(b);
	}
	
	
}
