package Entity.People;

import Entity.Person;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table(name = "teacher")
public class Teacher extends Person {
    private String speciality;
    private Set<Courses> course;
    public Teacher(String speciality, String name) {
        super(name);
        setSpeciality(speciality);
    }

    public Teacher() {

    }
    @Column(name = "speciality",length = 60)
    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    @OneToMany(mappedBy = "teacher", targetEntity = Courses.class)
    public Set<Courses> getCourse() {
        return course;
    }

    public void setCourse(Set<Courses> course) {
        this.course = course;
    }
}
