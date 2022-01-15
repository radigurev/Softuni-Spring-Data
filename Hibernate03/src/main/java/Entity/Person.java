package Entity;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Person extends BaseEntity {
    private String name;

    protected Person(){

    }
    protected Person(String name){
        setName(name);
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
