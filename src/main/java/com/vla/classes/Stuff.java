package com.vla.classes;

import lombok.*;

import javax.persistence.*;
import java.util.*;

@Getter
@EqualsAndHashCode(of ={"name","role"})
@ToString(of={"name","role"})
@NoArgsConstructor
@Entity
@Table(name ="users")
public class Stuff {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int id;

    @Column (length = 50,nullable = false)
    protected String name;

    @Column(length = 2,nullable = false)
    @Setter
    protected RoleUser role= RoleUser.ERROR;

    @Column(nullable = false)
    @OneToMany
    protected List<Score> avgScore=new ArrayList<>();

    @ManyToMany(cascade = CascadeType.MERGE,fetch = FetchType.LAZY )
    @JoinTable(name = "user_subject",joinColumns = {@JoinColumn(name = "id_user")},
            inverseJoinColumns = {@JoinColumn(name = "id_subject")})
    protected List<Subject> subjects=new ArrayList<>();


    public Stuff addToSubjects(String sub,Double val){


        return this;
    }






    public Stuff(int id, String name, RoleUser role, Double avgScore) {
        this.id = id;
        this.name = name;
        this.role = role;
        //this.avgScore = avgScore;
    }

    public Stuff(int id, String name, RoleUser role, Double avgScore,String subjectName) {
        this.id = id;
        this.name = name;
        this.role = role;
      //  this.avgScore = avgScore;
        //subjects.putIfAbsent(subjectName,avgScore);
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

