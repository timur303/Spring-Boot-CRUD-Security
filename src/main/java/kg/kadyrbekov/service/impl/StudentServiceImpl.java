package kg.kadyrbekov.service.impl;

import kg.kadyrbekov.entity.Groups;
import kg.kadyrbekov.entity.Student;
import kg.kadyrbekov.repository.GroupsRepository;
import kg.kadyrbekov.repository.StudentRepository;
import kg.kadyrbekov.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;
    private final GroupsRepository groupsRepository;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository, GroupsRepository groupsRepository) {
        this.studentRepository = studentRepository;
        this.groupsRepository = groupsRepository;
    }

    @Override
    public List<Student> findAllStudent() {
        return studentRepository.findAll();
    }

    @Override
    public Student saveStudent(Student student, Long groupId) {
        Groups groups = groupsRepository.findById(groupId).get();
        student.setGroups(groups);
        return studentRepository.save(student);
    }

    @Override
    public Optional<Student> findByIdStudent(Long id) {
        return studentRepository.findById(id);
    }

    @Override
    public Student updateStudent(Student newStudent, Long id) {
        Student student = findByIdStudent(id).get();
        student.setFirstName(newStudent.getFirstName());
        student.setLastName(newStudent.getLastName());
        student.setEmail(newStudent.getEmail());
        student.setStudyFormat(newStudent.getStudyFormat());
        student.setGroupId(newStudent.getGroupId());
        return studentRepository.save(student);
    }

    @Override
    public Long countById(Long id) {
        return studentRepository.count();
    }

    @Override
    public List<Student> findStudentWithSort(String sorting) {
        return studentRepository.findAll(Sort.by(Sort.Direction.ASC,sorting));
    }

    @Override
    public Long countByIdT(Long id) {
        return studentRepository.countByIdT(id);
    }

    @Override
    public void deleteStudentById(Long id) {
        studentRepository.deleteById(id);
    }
}
