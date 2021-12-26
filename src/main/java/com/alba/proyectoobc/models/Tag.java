package com.alba.proyectoobc.models;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "tag")
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column
    private String name;

    @ManyToMany(mappedBy = "tags", fetch = FetchType.LAZY)
    private List<Student> students;

    //Getters and Setters

    public void addStudent (Student student){

        if(!this.students.contains(student)){

            student.addTag(this);
            this.students.add(student);
        }
    }

    public void removeStudent (Student student){

        if(!this.students.contains(student)){

            student.removeTag(this);
            this.students.remove(student);
        }
    }
}
