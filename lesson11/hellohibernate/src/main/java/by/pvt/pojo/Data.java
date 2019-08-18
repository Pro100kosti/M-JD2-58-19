package by.pvt.pojo;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "data")
public class Data implements Serializable {

    private static final long serialVersionUID = 11L;

    @Id
 //   @GeneratedValue
    @GenericGenerator(name = "id", strategy = "increment")
    private int id;

    @OneToOne
    private Person personId;

    @Column
    private String phone;
    @Column
    private String car;
    @Column
    private String number;

    public Data() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Person getPersonId() {
        return personId;
    }

    public void setPersonId(Person personId) {
        this.personId = personId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCar() {
        return car;
    }

    public void setCar(String car) {
        this.car = car;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Data data = (Data) o;

        if (id != data.id) return false;
        if (phone != null ? !phone.equals(data.phone) : data.phone != null) return false;
        if (car != null ? !car.equals(data.car) : data.car != null) return false;
        return number != null ? number.equals(data.number) : data.number == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (car != null ? car.hashCode() : 0);
        result = 31 * result + (number != null ? number.hashCode() : 0);
        return result;
    }
}