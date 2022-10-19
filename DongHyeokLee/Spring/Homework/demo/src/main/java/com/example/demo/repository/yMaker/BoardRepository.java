package com.example.demo.repository.yMaker;

import com.example.demo.controller.yMaker.request.BoardRequest;
import com.example.demo.entity.yMaker.Board;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Slf4j
@Repository
public class BoardRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void create (BoardRequest board) {
        String query = "insert into board (id, name, gender, country, city) " +
                "values (?, ?, ?, ?, ?)";

        jdbcTemplate.update(query, board.getId(), board.getName(), board.getGender(),
                board.getCountry(), board.getCity());
    }

    public List<Board> list() {
        List<Board> results = jdbcTemplate.query(
                "select * from board " +
                        "where board_no > 0 order by board_no desc",

                new RowMapper<Board>() {
                    @SneakyThrows
                    @Override
                    public Board mapRow(ResultSet rs, int rowNum) throws SQLException {
                        Board board = new Board();

                        board.setBoardNo(rs.getInt("board_no"));
                        board.setId(rs.getString("id"));
                        board.setName(rs.getString("name"));
                        board.setGender(rs.getString("gender"));
                        board.setCountry(rs.getString("country"));
                        board.setCity(rs.getString("city"));

                        return board;
                    }
                }
        );

        return results;
    }

    public void delete(int[] board) {
        for(int b : board) {
            String query = "delete from board where board_no = ?";

            jdbcTemplate.update(query, b);
        }
    }

    public List<Board> search(BoardRequest board){
        String query;
             query = "select * from board where id = ? " +
                    "or name = ? " + "or gender = ? " +
                    "or country = ? " + "or city = ? " +
                    "or (reg_date >= ? " + "and reg_date < ?)";

        String id = board.getId();
        String name = board.getName();
        String gender = board.getGender();
        String country = board.getCountry();
        String city = board.getCity();
        String startDate = board.getStartDate();
        String endDate =  board.getEndDate();
        log.info("start" + startDate);
        log.info("end" +endDate);

        List<Board> results = jdbcTemplate.query(query,

                new RowMapper<Board>() {
                    @SneakyThrows
                    @Override
                    public Board mapRow(ResultSet rs, int rowNum) throws SQLException {
                        Board user = new Board();

                        user.setBoardNo(rs.getInt("board_no"));
                        user.setId(rs.getString("id"));
                        user.setName(rs.getString("name"));
                        user.setGender(rs.getString("gender"));
                        user.setCountry(rs.getString("country"));
                        user.setCity(rs.getString("city"));

                        return user;
                    }
                },

                id,name,gender,country,city, startDate, endDate
        );
        log.info("results" + results);
        return results;
    }

    public void update(Board board) {
        log.info("Repository update: " + board);

        String query = "update board set id = ?, name = ?, " +
                "gender = ?, country = ?, city = ? where board_no = ?";

        jdbcTemplate.update(query, board.getId(), board.getName(),
                board.getGender(), board.getCountry(), board.getCity(), board.getBoardNo());
    }

    public Board findById(int boardNo) {
        List<Board> results = jdbcTemplate.query(
                "select * from board " +
                        "where board_no = ?",

                new RowMapper<Board>() {
                    @SneakyThrows
                    @Override
                    public Board mapRow(ResultSet rs, int rowNum) throws SQLException {
                        Board board = new Board();

                        board.setBoardNo(rs.getInt("board_no"));
                        board.setId(rs.getString("id"));
                        board.setName(rs.getString("name"));
                        board.setGender(rs.getString("gender"));
                        board.setCountry(rs.getString("country"));
                        board.setCity(rs.getString("city"));


                        return board;
                    }
                }, boardNo
        );

        return results.get(0);
    }

}
