package com.example.myapplication;

public class Course {
    private int id;
    private String name;
    private String time;
    private String site;
    private String teacher;

    public Course(String name, String time, String site, String teacher) {
    }


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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public Course(int id, String name, String time, String site, String teacher) {
        this.id = id;
        this.name = name;
        this.time = time;
        this.site = site;
        this.teacher = teacher;
    }

    public Course() {
    }
}
