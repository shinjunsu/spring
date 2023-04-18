package com.codingbox.core2.member.dto;

import net.bytebuddy.dynamic.loading.InjectionClassLoader;

import javax.persistence.*;

@Entity
@Table(name = "member2")
public class MemberEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name="mySequence",sequenceName = "member_seq", allocationSize = 1)
    private Long id;
    @Column(name="username")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
