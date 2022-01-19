import Entity.Car.Car;
import Entity.Plane.Plane;
import Entity.Truck.Truck;
import Entity.Vehicles;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import javax.persistence.*;
import java.math.BigDecimal;

public class main {
    public static void main(String[] args) {
        EntityManagerFactory emf=Persistence.createEntityManagerFactory("vehicles");
        EntityManager em= emf.createEntityManager();
        em.getTransaction().begin();
        Car car =new Car("Audi A8", new BigDecimal(50000),"Gasoline",5);
        Truck truck=new Truck("Daf GX+",new BigDecimal(100000),"Diesel",10);
        Plane plane=new Plane("Messi",new BigDecimal(23423423),"Gasoline",324);

        em.persist(car);
        em.persist(truck);
        em.persist(plane);
        em.getTransaction().commit();

        em.getTransaction().begin();

        Vehicles found=em.find(Vehicles.class,1L);
        Vehicles found2=em.find(Vehicles.class,2L);
        Vehicles found3=em.find(Vehicles.class,3L);
        em.getTransaction().commit();
        System.out.println(found.toString());
        System.out.println(found2.toString());
        System.out.println(found3.toString());
    }
}
