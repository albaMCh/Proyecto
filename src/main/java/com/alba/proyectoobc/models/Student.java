package com.alba.proyectoobc.models;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "student")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(length = 70)
    private String name;

    @Column(length = 80)
    private String last_name;

    @Column(length = 13, unique = true, nullable = false)
    private String phone_number;

    @Column(unique = true, length = 110, nullable = false)
    private String email;

    @Column(length = 55)
    private String country;

    @Column(length = 55)
    private String city;

    @Column
    private Boolean move = false;

    @Column(length = 20)
    private String presence;

    @Column
    private String path_to_image;

    @Column
    private String path_to_cv;

    @Column(length = 55)
    private String username;

    @Column
    private String password;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "student_id", referencedColumnName = "id")
    private User user;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "students_to_tags", joinColumns = @JoinColumn(name = "users_id"),
            inverseJoinColumns = @JoinColumn(name = "tags_id"),
            uniqueConstraints = {@UniqueConstraint(columnNames = {"users_id","tags_id"})})
    private List<Tag> tags;

    //Setters and Getters

    public void addTag (Tag tag){

        if(!this.tags.contains(tag)){

            tag.addStudent(this);
            this.tags.add(tag);
        }
    }

    public void removeTag(Tag tag){

        if(!this.tags.contains(tag)){

            tag.removeStudent(this);
            this.tags.remove(tag);
        }
    }
}
