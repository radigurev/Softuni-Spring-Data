package Entity.Car;

import Entity.Vehicles;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name="cars")
public class Car extends Vehicles {
   private int seats;
   public Car(){

   }

    public Car(String model, BigDecimal price, String fuelType, int seats) {
        super(model, price, fuelType);
        setSeats(seats);
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    @Override
    public String toString() {
        return "Car{" +super.toString()+
                "seats=" + seats +
                '}';
    }
}
