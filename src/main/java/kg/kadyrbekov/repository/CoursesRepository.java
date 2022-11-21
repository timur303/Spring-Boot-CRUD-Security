package kg.kadyrbekov.repository;

import kg.kadyrbekov.entity.Courses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CoursesRepository extends JpaRepository<Courses, Long> {

    @Override
    List<Courses> findAll();

    @Override
    <S extends Courses> S save(S entity);

    @Override
    Optional<Courses> findById(Long aLong);

    @Override
    void deleteById(Long aLong);
}
