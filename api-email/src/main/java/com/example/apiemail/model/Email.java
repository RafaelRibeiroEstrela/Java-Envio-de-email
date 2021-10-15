package com.example.apiemail.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Email implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Set<String> to = new HashSet<>();
	private String from;
	private String password;
	private Set<String> cc = new HashSet<>();
	private String subject;
	private String text;
	private String html;
	private Set<String> attachments = new HashSet<>();
	
	
	public Set<String> getTo() {
		return to;
	}

	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Set<String> getCc() {
		return cc;
	}
	
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getHtml() {
		return html;
	}
	public void setHtml(String html) {
		this.html = html;
	}
	public Set<String> getAttachments() {
		return attachments;
	}
	
	
	
	
	

}
