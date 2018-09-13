package cn.tedu.note.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tedu.note.service.NoteService;
import cn.tedu.note.util.JsonResult;

@Controller
@RequestMapping("/note")
public class NoteController extends AbstractController{

	 @Resource 
	 private NoteService noteService;

	 @RequestMapping("/list.do")
	 @ResponseBody
	 public JsonResult list(String notebookId){
		 List<Map<String, Object>> list= 
		 	noteService.listNotes(notebookId);
		 return new JsonResult(list);
	 }
	 
}






