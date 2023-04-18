package com.codingbox.core2.member.repository;

import com.codingbox.core2.member.dto.Member;

import java.sql.SQLException;
import java.util.List;

public interface MemberRepository {
    //회원저장
    Member save(Member member);
    //전체조회
    List<Member> findAll();
}
