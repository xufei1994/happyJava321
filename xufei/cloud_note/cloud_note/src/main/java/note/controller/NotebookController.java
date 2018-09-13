package note.controller;

import note.service.NotebookService;
import note.util.JsonResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RequestMapping("/notebook")
@Controller
public class NotebookController extends AbstractController {

    @Resource
    private NotebookService notebookService;

    @RequestMapping("list.do")
    @ResponseBody
    public JsonResult list(String userId){
        List<Map<String,Object>> list =notebookService.listNotebooks(userId);
        return new JsonResult(list);
    }

    @RequestMapping("/page.do")
    @ResponseBody
    public JsonResult page(String userId,
                           Integer page){
        List<Map<String, Object>> list=
                notebookService.listNotebooks(
                        userId, page);
        return new JsonResult(list);
    }





}
