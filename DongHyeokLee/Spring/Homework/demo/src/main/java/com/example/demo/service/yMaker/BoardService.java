package com.example.demo.service.yMaker;

import com.example.demo.entity.yMaker.Board;

import java.util.List;

public interface BoardService {
    public List<Board> list();
    public void save(Board board);
    public void delete(int[] board);
}
