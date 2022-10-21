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
import java.util.ArrayList;
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
        log.info("this");
        List<Board> results = jdbcTemplate.query(
                "select * from board ",

                new RowMapper<Board>() {
                    @SneakyThrows
                    @Override
                    public Board mapRow(ResultSet rs, int rowNum) throws SQLException {
                        Board board = new Board();

                        board.setId(rs.getString("id"));
                        board.setName(rs.getString("name"));
                        board.setGender(rs.getString("gender"));
                        board.setCountry(rs.getString("country"));
                        board.setCity(rs.getString("city"));

                        return board;
                    }
                }
        );
        log.info(results.toString());
        return results;
    }

    public void delete(int[] board) {
        for(int b : board) {
            String query = "delete from board where board_no = ?";

            jdbcTemplate.update(query, b);
        }
    }
    //동적 쿼리? 정적 쿼리?
    // IF , CHOOSE
    // Xml
    //Map<String, String>
    public List<Board> search(BoardRequest board){
        StringBuilder querySQL = new StringBuilder("select * from board where 1=1 ");
        List<String> queryArgs = new ArrayList<>();

        if(!board.getName().equals("")){
            querySQL.append("and name = ? ");
            queryArgs.add(board.getName());
        }
        if(!board.getId().equals("")){
            querySQL.append("and id = ? ");
            queryArgs.add(board.getId());
        }
        if(board.getGender()!=null){
            querySQL.append("and gender = ? ");
            queryArgs.add(board.getGender());
        }
        if(!board.getCountry().equals("")){
            querySQL.append("and country = ? ");
            queryArgs.add(board.getCountry());
        }
        if(!board.getCity().equals("")){
            querySQL.append("and city = ? ") ;
            queryArgs.add(board.getCity());
        }
        log.info(board.getStartDate());
        if(!board.getStartDate().equals("")){
            querySQL.append("and reg_date >= ? ") ;
            queryArgs.add(board.getStartDate());
        }

        if(!board.getStartDate().equals("")){
            querySQL.append("and reg_date < ?") ;
            queryArgs.add(board.getEndDate());
        }



        log.info(querySQL.toString());
        log.info("arr" + queryArgs.toArray());

        String query;
             query = querySQL.toString();



        List<Board> results = jdbcTemplate.query(query,

                new RowMapper<Board>() {
                    @SneakyThrows
                    @Override
                    public Board mapRow(ResultSet rs, int rowNum) throws SQLException {
                        Board user = new Board();

                        user.setId(rs.getString("id"));
                        user.setName(rs.getString("name"));
                        user.setGender(rs.getString("gender"));
                        user.setCountry(rs.getString("country"));
                        user.setCity(rs.getString("city"));

                        return user;
                    }
                },

                queryArgs.toArray()
        );
        log.info("results" + results);
        return results;
    }

    /*public void update(Board board) {
        log.info("Repository update: " + board);

        String query = "update board set id = ?, name = ?, " +
                "gender = ?, country = ?, city = ? where board_no = ?";

        jdbcTemplate.update(query, board.getId(), board.getName(),
                board.getGender(), board.getCountry(), board.getCity(), board.getBoardNo());
    }*/

    public Board findById(int boardNo) {
        List<Board> results = jdbcTemplate.query(
                "select * from board " +
                        "where board_no = ?",

                new RowMapper<Board>() {
                    @SneakyThrows
                    @Override
                    public Board mapRow(ResultSet rs, int rowNum) throws SQLException {
                        Board board = new Board();

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

    public String searchSQL(BoardRequest board) {
         StringBuilder query = new StringBuilder("select * from board where");

        if(!board.getName().equals("")) query.append(" name = ? ") ;
        if(!board.getId().equals("")) query.append("and id = ? ") ;
        if(board.getGender()!=null) query.append("and gender = ? ") ;
        if(!board.getCountry().equals("")) query.append("and country = ? ") ;
        if(!board.getCity().equals("")) query.append("and city = ?") ;

        log.info(query.toString());

        return query.toString();

    }

}
