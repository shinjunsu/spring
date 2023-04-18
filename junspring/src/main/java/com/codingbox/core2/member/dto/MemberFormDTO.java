package com.codingbox.core2.member.dto;

public class MemberFormDTO {
    private Long id;
    private String name;

    public MemberFormDTO() {
    }

    public MemberFormDTO(String name) {
        this.name = name;
    }

    public MemberFormDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
