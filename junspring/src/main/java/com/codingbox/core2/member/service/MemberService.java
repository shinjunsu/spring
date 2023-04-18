package com.codingbox.core2.member.service;

import com.codingbox.core2.member.dto.Member;
import com.codingbox.core2.member.repository.MemberRepository;
import com.codingbox.core2.member.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import java.util.List;
@Service
@Transactional
public class MemberService {
   //MemberRepository memberRepository = new MemoryMemberRepository();

   //필드 주입: 순환참조 떄문에 권장하지 않음
   //@Autowired private MemberRepository memberRepository;
   private final MemberRepository memberRepository;
    @Autowired
    public MemberService(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }

    //수정자 주입: setter 함수는 public으롤 선언되어있어서 수정가능성이
    //의존성 주입 하지 않음

    //회원가입
    public int join(Member member) {
        memberRepository.save(member);
        return member.getId();
    }
    //전체 회원 조회
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }
}
