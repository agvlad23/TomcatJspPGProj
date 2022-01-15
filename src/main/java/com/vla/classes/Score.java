package com.vla.classes;

import java.util.Date;

public class Score {
    protected Integer id;
    protected Date date;
    protected double score;
    protected Integer idUser;
    protected Integer idSubject;
    protected String nameUser;
    protected String nameSubject;

    public Score(Date date, double score, String nameUser, String nameSubject) {
        this.date = date;
        this.score = score;
        this.nameUser = nameUser;
        this.nameSubject = nameSubject;
    }

    public Score(Integer id, Date date, double score, String nameUser, String nameSubject) {
        this.id = id;
        this.date = date;
        this.score = score;
        this.nameUser = nameUser;
        this.nameSubject = nameSubject;
    }

    public Score(int id, Double score) {
        this.id = id;
        this.score = score;
    }

    public String getNameUser() {
        return nameUser;
    }

    public Score(Integer id) {
        this.id = id;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    public String getNameSubject() {
        return nameSubject;
    }

    public void setNameSubject(String nameSubject) {
        this.nameSubject = nameSubject;
    }

    public Score(Date date, double score, Integer idUser, Integer idSubject, String nameUser, String nameSubject, Integer idSubject1) {
        this.date = date;
        this.score = score;
        this.idUser = idUser;
        this.idSubject = idSubject;
        this.nameUser = nameUser;
        this.nameSubject = nameSubject;
        this.idSubject = idSubject1;
    }

    public Score() {
    }

    public Score(Date date, double score, Integer idUser, Integer idSubject) {
        this.date = date;
        this.score = score;
        this.idUser = idUser;
        this.idSubject = idSubject;
    }

    public Score(Integer id, Date date, double score, Integer idUser, Integer idSubject) {
        this.id = id;
        this.date = date;
        this.score = score;
        this.idUser = idUser;
        this.idSubject = idSubject;
    }

    public Score(Integer id, double score, Integer idUser, Integer idSubject) {
        this.id = id;
        this.score = score;
        this.idUser = idUser;
        this.idSubject = idSubject;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public Integer getIdSubject() {
        return idSubject;
    }

    public void setIdSubject(Integer idSubject) {
        this.idSubject = idSubject;
    }

}
