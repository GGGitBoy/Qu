package com.myproject.entiy;

import java.util.ArrayList;
import java.util.HashMap;

public class DownLoadWord {
	
	private String title;
	private ArrayList<DownLoadQuestions> questions;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public ArrayList<DownLoadQuestions> getQuestions() {
		return questions;
	}
	public void setQuestions(ArrayList<DownLoadQuestions> questions) {
		this.questions = questions;
	}

}
