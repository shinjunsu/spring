package com.codingbox.core2.member.repository;

import com.codingbox.core2.member.dto.Member;
import com.codingbox.core2.member.dto.MemberEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import java.sql.SQLException;
import java.util.List;

@Repository
public class JpaMemberRepository implements  MemberRepository{

    private  final EntityManager em;
    public  JpaMemberRepository(EntityManager em){
        this.em=em;
    }
    @Override
    public Member save(Member member){
        em.persist(member);
        return member;
    }

    @Override
    public List<Member> findAll(){
        return em.createQuery("select m from Member m",
                Member.class).getResultList();
    }
}
