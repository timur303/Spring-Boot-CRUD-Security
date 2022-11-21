package kg.kadyrbekov.service;

import kg.kadyrbekov.entity.Company;
import kg.kadyrbekov.entity.Courses;

import java.util.List;
import java.util.Optional;

public interface CourserService {

    List<Courses> findAllCourses();

    Optional<Courses> findById(Long id);

    Courses saveCourses(Courses courses, Long companyId);

    Courses updateCourses(Courses newCourses, Long id);

    void deleteById(Long id);
}
