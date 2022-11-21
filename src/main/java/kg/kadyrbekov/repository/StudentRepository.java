package kg.kadyrbekov.repository;

import kg.kadyrbekov.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {

    @Override
    List<Student> findAll();

    @Override
    <S extends Student> S save(S entity);

    @Override
    Optional<Student> findById(Long aLong);
//
    @Query("SELECT COUNT(s.groups.id) FROM Student s WHERE s.groups.id=?1")
    Long countByIdT(Long id);

    @Override
    long count();

    @Override
    void deleteById(Long aLong);
}
