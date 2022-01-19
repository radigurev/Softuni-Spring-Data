import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class main {
    public static void main(String[] args) {
        EntityManager em= Persistence
                .createEntityManagerFactory("test")
                .createEntityManager();
    }
}
