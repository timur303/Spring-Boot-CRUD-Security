package kg.kadyrbekov.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "groups")
public class Groups {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String groupName;
    private String dateOfStart;
    private String dateOfFinish;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH})
    @JoinTable(name = "groups_courses", joinColumns = @JoinColumn(name = "groups_id")
            , inverseJoinColumns = @JoinColumn(name = "courses_id"))
    @JsonIgnore
    private List<Courses> courses;
    @Transient
    private Long courseId;

    @OneToMany(fetch = FetchType.EAGER,cascade = {CascadeType.REFRESH},mappedBy = "groups")
    private List<Student> students;

    @Override
    public String toString() {
        return "Groups{" +
                "id=" + id;
    }
}
