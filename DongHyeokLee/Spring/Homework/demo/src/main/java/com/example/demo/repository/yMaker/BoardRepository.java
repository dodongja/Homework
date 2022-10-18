package com.example.demo.repository.yMaker;

import com.example.demo.entity.yMaker.Board;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class BoardRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void create (Board board) {
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
                        Board user = new Board();

                        user.setBoardNo(rs.getInt("board_no"));
                        user.setId(rs.getString("name"));
                        user.setName(rs.getString("id"));
                        user.setGender(rs.getString("gender"));
                        user.setCountry(rs.getString("country"));
                        user.setCity(rs.getString("city"));

                        return user;
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


}
