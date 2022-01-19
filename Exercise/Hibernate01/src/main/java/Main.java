import Entity.Student;

import org.hibernate.*;
import org.hibernate.cfg.*;

public class Main {
    public static void main(String[] args) {
        Session s=new Configuration()
                .configure()
                .buildSessionFactory()
                .openSession();
        s.beginTransaction();
        s.createQuery("FROM Student",Student.class).list()
                .forEach(System.out::println);

        s.getTransaction().commit();
        s.close();
    }
}
