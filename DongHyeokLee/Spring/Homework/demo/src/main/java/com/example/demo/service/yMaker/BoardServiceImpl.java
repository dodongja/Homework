package com.example.demo.service.yMaker;

import com.example.demo.controller.yMaker.request.BoardRequest;
import com.example.demo.entity.yMaker.Board;
import com.example.demo.repository.yMaker.BoardRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class BoardServiceImpl implements BoardService {

    @Autowired
    private BoardRepository repository;

    @Override
    public List<Board> list() {
        return repository.list();
    }

    @Override
    public void save(BoardRequest board) {
        repository.create(board);
    }

    @Override
    public void delete(int[] board) {
        repository.delete(board);
    }

    @Override
    public List<Board> search(BoardRequest board) {
        return repository.search(board);
    }

   /* @Override
    public void update(Board board) {
        int boardNo = board.getBoardNo();
        Board b = repository.findById(boardNo);
        log.info("findByID" + b.toString());
        //일단 이래 짜놓긴 했는데 ajax랑 html에서 데이터 주고받는거 좀 더 공부해야할듯
        if(!board.getName().equals("")) b.setName(board.getName());
        if(!board.getId().equals("")) b.setId(board.getId());
        if(board.getGender()!=null) b.setGender(board.getGender());
        if(!board.getCountry().equals("")) b.setCountry(board.getCountry());
        if(!board.getCity().equals("")) b.setCity(board.getCity());

        log.info(b.toString());
        repository.update(b);
    }*/
}
