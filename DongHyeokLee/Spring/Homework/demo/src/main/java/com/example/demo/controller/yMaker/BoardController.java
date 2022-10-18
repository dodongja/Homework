package com.example.demo.controller.yMaker;

import com.example.demo.entity.yMaker.Board;
import com.example.demo.service.yMaker.BoardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/board")
public class BoardController {

    @Autowired
    private BoardService boardService;

    @GetMapping("/list")
    public String list(Board board, Model model){
            log.info("list");

            model.addAttribute("list",boardService.list());

            return "yMaker/board";
    }

    @PostMapping("/save")
    public String save (Board board) {
        log.info("DB save - board 정보: " + board);

        // DB 처리
        boardService.save(board);

        return "redirect:/board/list";
    }

    @PostMapping("/delete")
    public String delete (HttpServletRequest request) {
        log.info("delete" + request);
        String[] arrayParam = request.getParameterValues("list[]");
        int[] list = new int[arrayParam.length];

        for(int i = 0; i < arrayParam.length; i++){
            list[i] = Integer.parseInt(arrayParam[i]);
        }

        boardService.delete(list);

        return "redirect:/board/list";
    }


}
