package com.codingbox.core2.member.repository;

import com.codingbox.core2.member.dto.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.liquibase.DataSourceClosingSpringLiquibase;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//@Repository
public class JdbcMemberRepository implements MemberRepository{


    private final DataSource dataSource;
    public JdbcMemberRepository(DataSource dataSource){
        this.dataSource = dataSource;
    }

    Connection conn = null;
    PreparedStatement pstm = null;
    ResultSet rs = null;
    @Override
    public Member save(Member member){
        String sql = "INSERT INTO MEMBER values(member_seq.nextval, ?)";

        try{
            conn = dataSource.getConnection();
            String generatedColums[]={"ID"};
            pstm = conn.prepareStatement(sql,generatedColums);
            pstm.setString(1,member.getName());
            pstm.executeUpdate();
            rs = pstm.getGeneratedKeys();

            if(rs.next()){
                member.setId(rs.getInt(1));
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally{
            try {
                rs.close();
                pstm.close();
                conn.close();
            }catch(SQLException e) {
                e.printStackTrace();
            }
        }
        return member;
    }
//    public void AllClose (ResultSet rs, Connection conn, PreparedStatement pstm){
//        rs.close();
//        pstm.close();
//        conn.close();
//    }
    @Override
    public List<Member> findAll(){
        String sql = "select * from member";
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        List<Member> members = new ArrayList<>();
        try{
            conn = dataSource.getConnection();
            pstm = conn.prepareStatement(sql);
            rs = pstm.executeQuery();


            while(rs.next()){
                Member member = new Member();
                member.setId(rs.getInt("id"));
                member.setName(rs.getString("name"));
                members.add(member);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally{
            try {
                rs.close();
                pstm.close();
                conn.close();
            }catch(SQLException e) {
                e.printStackTrace();
            }
        }
        return members;
    }
}
