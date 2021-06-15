package org.gm;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class StudentRepository implements PanacheRepository<Student> {

    public Student findByNameAge(String name, int age) {
        Student s = student("SELECT m FROM Student m WHERE m.name = ? & m.age=?",name,age);
        return s;

        return list("SELECT m FROM Movie m WHERE m.country = ?1 ORDER BY id DESC", country);
    }
}
