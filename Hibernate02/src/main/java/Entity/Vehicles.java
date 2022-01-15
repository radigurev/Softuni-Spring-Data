package Entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "vehicles")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "type")
public abstract class Vehicles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String model;
    private BigDecimal price;
    @Column(name = "fuel_type")
    private String fuelType;
    public Vehicles(){

    }
    public Vehicles(String model, BigDecimal price, String fuelType) {
        this.model = model;
        this.price = price;
        this.fuelType = fuelType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vehicles vehicles = (Vehicles) o;
        return Objects.equals(id, vehicles.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Vehicles{" +
                "id=" + id +
                ", model='" + model + '\'' +
                ", price=" + price +
                ", fuelType='" + fuelType + '\'' +
                '}';
    }
}
