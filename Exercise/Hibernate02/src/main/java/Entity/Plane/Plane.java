package Entity.Plane;

import Entity.Vehicles;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name="planes")
public class Plane extends Vehicles {
    @Column(name = "passanger_Capacity")
   private int passengerCapacity;
   public Plane(){

   }

    public Plane(String model, BigDecimal price, String fuelType, int passengerCapacity) {
        super(model, price, fuelType);
        setPassengerCapacity(passengerCapacity);
    }

    public int getPassengerCapacity() {
        return passengerCapacity;
    }

    public void setPassengerCapacity(int loadCapacity) {
        this.passengerCapacity = loadCapacity;
    }

    @Override
    public String toString() {
        return "Car{" +super.toString()+
                "load capacity=" + passengerCapacity +
                '}';
    }
}
