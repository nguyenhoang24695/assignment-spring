package com.t1708e.springassignment.entity;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Size(min = 7, max = 7, message = "Roll Number length must be 7 characters.")
    private String rollNumber;
    @NotEmpty(message = "This can not be null or empty!")
    private String name;
    @NotEmpty(message = "This can not be null or empty!")
    @Email(message = "Invalid email")
    private String email;
    @NotEmpty(message = "This can not be null or empty!")
    private String password;
    @ManyToMany(mappedBy = "studentSet",cascade = {CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH},fetch = FetchType.LAZY)

    private Set<ClassRoom> classRoomSet;

    public Student() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRollNumber() {
        return rollNumber;
    }

    public void setRollNumber(String rollNumber) {
        this.rollNumber = rollNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<ClassRoom> getClassRoomSet() {
        return classRoomSet;
    }

    public void setClassRoomSet(Set<ClassRoom> classRoomSet) {
        this.classRoomSet = classRoomSet;
    }
}
