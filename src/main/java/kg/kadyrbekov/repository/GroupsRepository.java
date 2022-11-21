package kg.kadyrbekov.repository;

import kg.kadyrbekov.entity.Groups;
import kg.kadyrbekov.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GroupsRepository extends JpaRepository<Groups, Long> {

    @Query("SELECT s FROM Student s WHERE s.firstName LIKE %?1% " +
            "OR s.lastName LIKE %?1%")
    List<Student> findByStudents(String search);

    @Override
    List<Groups> findAll();

    @Override
    <S extends Groups> S save(S entity);

    @Override
    Optional<Groups> findById(Long aLong);

    @Override
    void deleteById(Long aLong);
}
