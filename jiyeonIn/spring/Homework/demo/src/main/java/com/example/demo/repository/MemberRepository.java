package com.example.demo.repository;

import com.example.demo.entity.order37.Member;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;

@Repository
public class MemberRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void create(Member member) {
        String query = "insert into member (id, password, name, email) values (?, ?, ?, ?)";
        jdbcTemplate.update(query, member.getId(), member.getPassword(), member.getName(), member.getEmail());
    }

    public List<Member> list() {
        List<Member> results = jdbcTemplate.query(
                "select * from member " +
                        "where member_no > 0 order by member_no desc",

                new RowMapper<Member>() {
                    @SneakyThrows
                    @Override
                    public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
                        Member member = new Member();

                        member.setMemberNo(rs.getInt("member_no"));
                        member.setId(rs.getString("id"));
                        member.setPassword(rs.getString("password"));
                        member.setName(rs.getString("name"));
                        member.setEmail(rs.getString("email"));

                        SimpleDateFormat printDate =
                                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                        String dbDate = rs.getDate("reg_date") + " " + rs.getTime("reg_date");
                        member.setRegDate(printDate.parse(dbDate));

                        return member;
                    }
                }
        );
        return results;
    }

    public Member read(Integer memberNo) {
        List<Member> results = jdbcTemplate.query(
                "select * from member " +
                        "where member_no = ?",

                new RowMapper<Member>() {
                    @SneakyThrows
                    @Override
                    public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
                        Member member = new Member();

                        member.setMemberNo(rs.getInt("member_no"));
                        member.setId(rs.getString("id"));
                        member.setPassword(rs.getString("password"));
                        member.setName(rs.getString("name"));
                        member.setEmail(rs.getString("email"));

                        SimpleDateFormat printDate =
                                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                        String dbDate = rs.getDate("reg_date") + " " + rs.getTime("reg_date");
                        member.setRegDate(printDate.parse(dbDate));

                        return member;
                    }
                }, memberNo
        );
        return results.isEmpty() ? null : results.get(0);
    }

    public void modify(Member member) {
        String query = "update member set password = ?, email = ? where member_no = ?";
        jdbcTemplate.update(query, member.getPassword(), member.getEmail(),member.getMemberNo());
    }

    public void remove(Integer memberNo) {
        String query = "delete from member where member_no = ?";
        jdbcTemplate.update(query, memberNo);
    }

    public int findId(Member member) {
         Connection conn = null;
         PreparedStatement pstmt;
         ResultSet rs;
        
        String SQL = "SELECT password FROM member WHERE id = ?";
        jdbcTemplate.update(member.getId());
        try {
            pstmt = conn.prepareStatement(SQL);
            pstmt.setString(1, member.getId());
            rs = pstmt.executeQuery();
            if(rs.next()) {
                if(rs.getString(1).contentEquals(member.getPassword())) {
                    return 1;
                }
                else
                    return 0;
            }
            return -1;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return -2;
    }
}

