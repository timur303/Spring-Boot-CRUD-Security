package kg.kadyrbekov.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "companies")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String companyName;
    private String locatedCountry;
    @OneToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.REFRESH}, mappedBy = "company")
    @JsonIgnore
    private List<Courses> courses;

}
