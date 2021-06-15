package org.gm;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "student")
public class Student {

    @Id
    @GeneratedValue
    private long id;

    @Column(name = "name",length = 100)
    private String name;

    @Column(name = "age")
    private int age;

    @Column(name="major",length = 50)
    private String major;

    public long getId() {
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }
}
