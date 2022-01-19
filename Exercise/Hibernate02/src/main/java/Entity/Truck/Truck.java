package Entity.Truck;

import Entity.Vehicles;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name="trucks")
public class Truck extends Vehicles {
    @Column(name = "load_Capacity")
   private double loadCapacity;
   public Truck(){

   }

    public Truck(String model, BigDecimal price, String fuelType, double loadCapacity) {
        super(model, price, fuelType);
        setLoadCapacity(loadCapacity);
    }

    public double getLoadCapacity() {
        return loadCapacity;
    }

    public void setLoadCapacity(double loadCapacity) {
        this.loadCapacity = loadCapacity;
    }

    @Override
    public String toString() {
        return "Car{" +super.toString()+
                "load capacity=" + loadCapacity +
                '}';
    }
}
