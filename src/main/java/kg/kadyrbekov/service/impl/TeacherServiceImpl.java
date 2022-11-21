package kg.kadyrbekov.service.impl;

import kg.kadyrbekov.entity.Courses;
import kg.kadyrbekov.entity.Teacher;
import kg.kadyrbekov.exception.NotFoundException;
import kg.kadyrbekov.repository.CoursesRepository;
import kg.kadyrbekov.repository.TeacherRepository;
import kg.kadyrbekov.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository teacherRepository;
    private final CoursesRepository coursesRepository;

    @Autowired
    public TeacherServiceImpl(TeacherRepository teacherRepository, CoursesRepository coursesRepository) {
        this.teacherRepository = teacherRepository;
        this.coursesRepository = coursesRepository;
    }

    @Override
    public List<Teacher> findAllTeacher() {
        return teacherRepository.findAll();
    }

    @Override
    public Teacher saveTeacher(Teacher teacher,Long coursesId) {
        Courses courses = coursesRepository.findById(coursesId).get();
        teacher.setCourses(courses);
        return teacherRepository.save(teacher);
    }

    @Override
    public Teacher findByIdTeacher(Long id) {
        return teacherRepository.findById(id).orElseThrow(()->new NotFoundException(String.format("hfhfhf")));
    }

    @Override
    public Teacher updateTeacher(Long id, Teacher newTeacher) {
        Courses courses = coursesRepository.findById(id).get();
        Teacher teacher = findByIdTeacher(id);
        teacher.setFirstName(newTeacher.getFirstName());
        teacher.setLastName(newTeacher.getLastName());
        teacher.setEmail(newTeacher.getEmail());
        teacher.setCourseId(newTeacher.getCourseId());
        teacher.setCourses(courses);
        return teacherRepository.save(teacher);
    }

    @Override
    public void deleteByIdTeacher(Long id) {
        teacherRepository.deleteById(id);
    }
}
