package com.aerospike.spring.boot.example.domain;

import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotNull;


public class Product {


    @Id
    private Integer id;
    @NotNull
    private String name;

    private String abbreviation;

    private String person;

    private String num;
    
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAbbreviation() {
		return abbreviation;
	}
	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}
	public String getPerson() {
		return person;
	}
	public void setPerson(String person) {
		this.person = person;
	}
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
}
