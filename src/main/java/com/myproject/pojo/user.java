package com.myproject.pojo;

import java.util.Date;

import com.myproject.entiy.BaseEntity;

public class user extends BaseEntity{
    private String userid;

    private String icon;

    private String username;

    private String password;

    private String nickname;

    private String major;    //专业

    private String studentnumber;

    private String name;

    private Boolean sex;

    private String graduationyear;

    private String correspondenceaddress;

    private String wechat;

    private String phone;

    private String email;

    private String qqnumber;

    private String graduationplane;

    private String graduationplanesituation;

    private Boolean canlogin;

    private String usertypenum;

    private String remark;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid == null ? null : userid.trim();
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon == null ? null : icon.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major == null ? null : major.trim();
    }

    public String getStudentnumber() {
        return studentnumber;
    }

    public void setStudentnumber(String studentnumber) {
        this.studentnumber = studentnumber == null ? null : studentnumber.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Boolean getSex() {
        return sex;
    }

    public void setSex(Boolean sex) {
        this.sex = sex;
    }

    public String getGraduationyear() {
        return graduationyear;
    }

    public void setGraduationyear(String graduationyear) {
        this.graduationyear = graduationyear == null ? null : graduationyear.trim();
    }

    public String getCorrespondenceaddress() {
        return correspondenceaddress;
    }

    public void setCorrespondenceaddress(String correspondenceaddress) {
        this.correspondenceaddress = correspondenceaddress == null ? null : correspondenceaddress.trim();
    }

    public String getWechat() {
        return wechat;
    }

    public void setWechat(String wechat) {
        this.wechat = wechat == null ? null : wechat.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getQqnumber() {
        return qqnumber;
    }

    public void setQqnumber(String qqnumber) {
        this.qqnumber = qqnumber == null ? null : qqnumber.trim();
    }

    public String getGraduationplane() {
        return graduationplane;
    }

    public void setGraduationplane(String graduationplane) {
        this.graduationplane = graduationplane == null ? null : graduationplane.trim();
    }

    public String getGraduationplanesituation() {
        return graduationplanesituation;
    }

    public void setGraduationplanesituation(String graduationplanesituation) {
        this.graduationplanesituation = graduationplanesituation == null ? null : graduationplanesituation.trim();
    }

    public Boolean getCanlogin() {
        return canlogin;
    }

    public void setCanlogin(Boolean canlogin) {
        this.canlogin = canlogin;
    }

    public String getUsertypenum() {
        return usertypenum;
    }

    public void setUsertypenum(String usertypenum) {
        this.usertypenum = usertypenum == null ? null : usertypenum.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    @Override
    public String toString() {
        return "user{" +
                "userid='" + userid + '\'' +
                ", icon='" + icon + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", nickname='" + nickname + '\'' +
                ", major='" + major + '\'' +
                ", studentnumber='" + studentnumber + '\'' +
                ", name='" + name + '\'' +
                ", sex=" + sex +
                ", graduationyear='" + graduationyear + '\'' +
                ", correspondenceaddress='" + correspondenceaddress + '\'' +
                ", wechat='" + wechat + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", qqnumber='" + qqnumber + '\'' +
                ", graduationplane='" + graduationplane + '\'' +
                ", graduationplanesituation='" + graduationplanesituation + '\'' +
                ", canlogin=" + canlogin +
                ", usertypenum='" + usertypenum + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}