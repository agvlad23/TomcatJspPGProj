package com.vla.classes;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@EqualsAndHashCode(of ={"date","score","nameUser","nameSubject"})
@ToString(of={"date","score","nameUser","nameSubject"})
@NoArgsConstructor
@Entity
public class Score {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;
    @Column(nullable = false)
    protected LocalDateTime date;
    @Column(nullable = false)
    protected double score;

    @ManyToOne
    protected Stuff nameUser;
    @ManyToOne
    protected Subject nameSubject;

    public Score(Date date, double score, String nameUser, String nameSubject) {

        this.score = score;

    }

    public Score(Integer id, Date date, double score, String nameUser, String nameSubject) {
        this.id = id;

        this.score = score;

    }

    public Score(int id, Double score) {
        this.id = id;
        this.score = score;
    }



    public Score(Integer id) {
        this.id = id;
    }




    public Score(Date date, double score, Integer idUser, Integer idSubject, String nameUser, String nameSubject, Integer idSubject1) {

        this.score = score;

    }


    public Score(Date date, double score, Integer idUser, Integer idSubject) {

        this.score = score;

    }

    public Score(Integer id, Date date, double score, Integer idUser, Integer idSubject) {
        this.id = id;

        this.score = score;

    }

    public Score(Integer id, double score, Integer idUser, Integer idSubject) {
        this.id = id;
        this.score = score;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDate() {
       return null;
    }

    public void setDate(Date date) {

    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public Integer getIdUser() {
        return null;
    }

    public void setIdUser(Integer idUser) {

    }

    public Integer getIdSubject() {
        return null;
    }

    public void setIdSubject(Integer idSubject) {

    }

}
