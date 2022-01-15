package Entity;
import java.util.*;
public class Student {
    private int id;
    private String name;
    private Date regDate;

    public Student(String name) {
        this.name = name;
        regDate=new Date();
    }
    public  Student(){
        regDate=new Date();
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

    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", regDate=" + regDate +
                '}';
    }
}
