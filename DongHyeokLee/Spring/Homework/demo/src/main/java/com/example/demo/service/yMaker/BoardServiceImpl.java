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
    public void delete(String[] board) {
        repository.delete(board);
    }

    @Override
    public List<Board> search(BoardRequest board) {
        return repository.search(board);
    }

    @Override
    public void modify(BoardRequest board) {
        repository.modify(board);
    }
}
