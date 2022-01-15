package Entity.People;

import javax.persistence.*;
import java.util.Set;
import Entity.*;
@Entity
@Table(name = "courses")
public class Courses extends BaseEntity {
    String name;
    Set<Student> students;
    Teacher teacher;

    public Courses(String name) {
        setName(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @ManyToMany
    @JoinTable(name = "course_students", joinColumns = @JoinColumn(name = "course_id",referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "student_id",referencedColumnName = "id")
    )
    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }
    @ManyToOne
    @JoinColumn(name = "teacher_id",referencedColumnName = "id")
    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }
}
