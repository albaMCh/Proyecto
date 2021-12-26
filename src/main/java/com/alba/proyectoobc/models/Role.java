package com.alba.proyectoobc.models;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true)
    private String name;

    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
    private List<User> users;

    //Getters and Setters

    public void addUser(User user){

        if(!this.users.contains(user)){

            user.addRoles(this);
            this.users.add(user);
        }
    }

    public void removeUser(User user){

        if(!this.users.contains(user)){

            user.removeRoles(this);
            this.users.remove(user);
        }
    }
}
