package Entity.People;

import Entity.Person;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "student")
public class Student extends Person {
    String grade;
    Set<Courses> courses;
    public Student(String grade,String name){
        super(name);
        setGrade(grade);
    }

    @Column(name = "grade")
    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    @ManyToMany(mappedBy = "students",targetEntity = Courses.class)
    public Set<Courses> getCourses() {
        return courses;
    }

    public void setCourses(Set<Courses> courses) {
        this.courses = courses;
    }
}
