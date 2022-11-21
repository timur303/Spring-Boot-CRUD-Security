package kg.kadyrbekov.service;

import kg.kadyrbekov.entity.Student;

import java.util.List;
import java.util.Optional;

public interface StudentService {

    List<Student> findAllStudent();

    Student saveStudent(Student student, Long groupId);

    Optional<Student> findByIdStudent(Long id);

    Student updateStudent(Student student,Long id);

    Long countById(Long id);
    Long countByIdT(Long id);
    List<Student> findStudentWithSort(String sorting);
    void deleteStudentById(Long id);

}
