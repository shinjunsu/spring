package com.codingbox.core2.member.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "member")
public class MemberEntity {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@SequenceGenerator(name = "mySequence",sequenceName = "member_seq", allocationSize = 1)
	private int id;
	
	@Column(name = "username")
	private String name;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
