package com.myproject.pojo;

import java.util.Date;
import java.util.List;

import com.myproject.entiy.BaseEntity;

public class questionnaire extends BaseEntity{
    private String questionnaireid;

    private String questionnairenumber;

    private String title;

    private String secondtitle;

    private String image;

    private String author;

    private Integer click;

    private String digest;

    private Date starttime;

    private Date endtime;

    private String status;
    
    public List<question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<question> questions) {
		this.questions = questions;
	}

	private List<question> questions;

    public String getQuestionnaireid() {
        return questionnaireid;
    }

    public void setQuestionnaireid(String questionnaireid) {
        this.questionnaireid = questionnaireid == null ? null : questionnaireid.trim();
    }

    public String getQuestionnairenumber() {
        return questionnairenumber;
    }

    public void setQuestionnairenumber(String questionnairenumber) {
        this.questionnairenumber = questionnairenumber == null ? null : questionnairenumber.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getSecondtitle() {
        return secondtitle;
    }

    public void setSecondtitle(String secondtitle) {
        this.secondtitle = secondtitle == null ? null : secondtitle.trim();
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image == null ? null : image.trim();
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author == null ? null : author.trim();
    }

    public Integer getClick() {
        return click;
    }

    public void setClick(Integer click) {
        this.click = click;
    }

    public String getDigest() {
        return digest;
    }

    public void setDigest(String digest) {
        this.digest = digest == null ? null : digest.trim();
    }

    public Date getStarttime() {
        return starttime;
    }

    public void setStarttime(Date starttime) {
        this.starttime = starttime;
    }

    public Date getEndtime() {
        return endtime;
    }

    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

}