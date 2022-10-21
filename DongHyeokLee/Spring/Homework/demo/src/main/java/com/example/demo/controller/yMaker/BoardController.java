package com.example.demo.controller.yMaker;

import com.example.demo.controller.yMaker.request.BoardRequest;
import com.example.demo.entity.yMaker.Board;
import com.example.demo.service.yMaker.BoardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/board")
public class BoardController {

    @Autowired
    private BoardService boardService;

    @GetMapping("/main")
    public String domain(){
        log.info("main");

        return "/yMaker/board";
    }

    @ResponseBody
    @GetMapping(value = "/list")
    public List<Board> list(){
            log.info("list");

            return boardService.list();
    }

    @ResponseBody
    @PostMapping("/save")
    public Object save (BoardRequest board) {
        log.info("DB save - board 정보: " + board);

        // DB 처리
        boardService.save(board);

        Map<String, Object> retVal = new HashMap<String, Object>();

        //성공했다고 처리
        retVal.put("code", "OK");
        retVal.put("message", "저장 되었습니다.");

        return retVal;
    }

    @PostMapping("/delete")
    public @ResponseBody Object delete (HttpServletRequest request) {
            log.info("delete");
            String[] arrayParam = request.getParameterValues("list");

            boardService.delete(arrayParam);


        Map<String, Object> retVal = new HashMap<String, Object>();

        //성공했다고 처리
        retVal.put("code", "OK");
        retVal.put("message", "삭제 되었습니다.");

        return retVal;
    }

    @ResponseBody
    @PostMapping("/search")
    public Object search(BoardRequest board, Model model){
        log.info("search" + board.toString());

        return boardService.search(board);

    }

    //@ResponseBody
    @ResponseBody
    @PostMapping("/modify")
    public Object modify(BoardRequest boardRequest, Model model){
        log.info("modify");

        boardService.modify(boardRequest);

        Map<String, Object> retVal = new HashMap<String, Object>();

        //성공했다고 처리
        retVal.put("code", "OK");
        retVal.put("message", "저장 되었습니다.");

        return retVal;
    }


    @GetMapping("/modify")
    public Object modify(){
        log.info("getModify");


        return "/yMaker/modify";
    }


}
