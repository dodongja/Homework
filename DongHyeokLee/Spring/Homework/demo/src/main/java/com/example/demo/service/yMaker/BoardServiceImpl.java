package com.example.demo.service.yMaker;

import com.example.demo.entity.yMaker.Board;
import com.example.demo.repository.yMaker.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardServiceImpl implements BoardService {

    @Autowired
    private BoardRepository repository;

    @Override
    public List<Board> list() {
        return repository.list();
    }

    @Override
    public void save(Board board) {
        repository.create(board);
    }

    @Override
    public void delete(int[] board) {
        repository.delete(board);
    }
}
