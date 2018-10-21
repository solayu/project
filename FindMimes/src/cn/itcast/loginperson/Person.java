package cn.itcast.loginperson;

import java.io.Serializable;

import javax.swing.AbstractAction;

public class Person implements Serializable{
	
	private String id;
	private String name;
	private String password;
	private String score;
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getScore() {
		return score;
	}
	public void setScore(String score) {
		this.score = score;
	}
	
	public Person(String id, String name, String password, String score) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
		this.score = score;
	}
	public Person() {
		
	}
	
	
}
