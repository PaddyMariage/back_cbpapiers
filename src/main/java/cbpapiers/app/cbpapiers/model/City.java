package cbpapiers.app.cbpapiers.model;

import cbpapiers.app.cbpapiers.jsonview.MyJsonView;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "CITY")
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @JsonView(MyJsonView.Customer.class)
    @Column(name = "CT_Ville")
    private String name;

    @JsonView(MyJsonView.Customer.class)
    @Column(name = "CT_CodePostal")
    private String postalCode;

    public City(String ct_ville, String ct_codePostal) {
        postalCode = ct_codePostal;
        name = ct_ville;
    }

    @Override
    public String toString() {
        return "City{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", postalCode='" + postalCode + '\'' +
                '}';
    }

    public int compareTo(City o) {
        return this.getName().compareTo(o.getName());
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        City city = (City) o;
        return Objects.equals(getName(), city.getName()) &&
                Objects.equals(getPostalCode(), city.getPostalCode());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getPostalCode());
    }
}
