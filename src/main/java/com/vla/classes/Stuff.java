package com.vla.classes;

import java.util.*;

public class Stuff {
    protected int id;
    protected String name;
    protected RoleUser role;
    protected Double avgScore;
    protected Map<String,Double> subjects=new HashMap<>();
    


    public Stuff addToSubjects(String sub,Double val){
        subjects.put(sub,val);

        return this;
    }
    public Map<String, Double> getSubjects() {
        return subjects;
    }

    public void setSubjects(Map<String, Double> subjects) {
        this.subjects = subjects;
    }



    public Double getAvgScore() {
        return avgScore;
    }

    public void setAvgScore(Double avgScore) {
        this.avgScore = avgScore;
    }

    public Stuff(int id, String name, RoleUser role, Double avgScore) {
        this.id = id;
        this.name = name;
        this.role = role;
        this.avgScore = avgScore;
    }

    public Stuff(int id, String name, RoleUser role, Double avgScore,String subjectName) {
        this.id = id;
        this.name = name;
        this.role = role;
        this.avgScore = avgScore;
        subjects.putIfAbsent(subjectName,avgScore);
    }

    public Stuff(int id) {
        this.id = id;
    }

    public Stuff(int id, String name, RoleUser role) {
        this.id = id;
        this.name = name;
        this.role = role;
    }

    public Stuff(String name, RoleUser role) {
        this.name = name;
        this.role = role;
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

    public RoleUser getRole() {
        return role;
    }

    public void setRole(RoleUser role) {
        this.role = role;
    }



}

