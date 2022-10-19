package com.example.demo.controller.yMaker;

import com.example.demo.controller.yMaker.request.BoardRequest;
import com.example.demo.entity.yMaker.Board;
import com.example.demo.service.yMaker.BoardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/board")
public class BoardController {

    @Autowired
    private BoardService boardService;

    @GetMapping("/list")
    public String list(Model model){
            log.info("list");

            BoardRequest b = new BoardRequest();
            model.addAttribute("boardRequest",b);
            model.addAttribute("list",boardService.list());


            return "yMaker/board";
    }

    @PostMapping("/save")
    public String save (BoardRequest board) {
        log.info("DB save - board 정보: " + board);

        // DB 처리
        boardService.save(board);

        return "redirect:/board/list";
    }

    @PostMapping("/delete")
    public @ResponseBody Object delete (HttpServletRequest request) {
            log.info("delete");
            String[] arrayParam = request.getParameterValues("list");

            int[] list = new int[arrayParam.length];

            for (int i = 0; i < arrayParam.length; i++) {
                list[i] = Integer.parseInt(arrayParam[i]);
            }

            boardService.delete(list);

            log.info("this");
        Map<String, Object> retVal = new HashMap<String, Object>();

        //성공했다고 처리
        retVal.put("code", "OK");
        retVal.put("message", "삭제 되었습니다.");

        return retVal;
    }

    @PostMapping("/search")
    public String search(BoardRequest board, Model model){
        log.info("search" + board.toString());

        model.addAttribute("list",boardService.search(board));

        return "yMaker/board";
    }

    @PostMapping("/modify")
    @ResponseBody
    public Object modify(Board board){
        log.info("modify" + board.toString());

        boardService.update(board);

        Map<String, Object> retVal = new HashMap<String, Object>();

        //성공했다고 처리
        retVal.put("code", "OK");
        retVal.put("message", "수정 되었습니다.");

        return retVal;
    }


}
