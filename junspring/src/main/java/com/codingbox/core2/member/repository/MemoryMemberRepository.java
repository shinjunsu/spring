package com.codingbox.core2.member.repository;

import com.codingbox.core2.member.dto.Member;
import com.codingbox.core2.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@Repository
public class MemoryMemberRepository implements MemberRepository{

    //메모리 사용
    private static Map<Integer,Member> store = new HashMap<>();
    private static int sequence = 0;

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(),member);
        return null;
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }
}
