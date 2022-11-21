package kg.kadyrbekov.controller;

import kg.kadyrbekov.entity.Courses;
import kg.kadyrbekov.entity.Teacher;
import kg.kadyrbekov.service.CourserService;
import kg.kadyrbekov.service.StudentService;
import kg.kadyrbekov.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/teacher")
public class TeacherController {

    private final CourserService courserService;
    private final TeacherService teacherService;
    private final StudentService studentService;

    @GetMapping("/courses")
    public List<Courses> findAllCourses() {
        return courserService.findAllCourses();
    }

    @GetMapping
    public List<Teacher> findAllTeacher() {
        return teacherService.findAllTeacher();
    }

    @GetMapping("/{id}")
    public Teacher findByIdTeacher(@PathVariable("id") Long id) {
        return teacherService.findByIdTeacher(id);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/save")
    public Teacher saveTeacher(@RequestBody Teacher teacher) {
        return teacherService.saveTeacher(teacher, teacher.getCourseId());
    }

    @PatchMapping("/update/{id}")
    public Teacher updateTeacher(@RequestBody Teacher teacher, @PathVariable("id") Long id) {
        return teacherService.updateTeacher(id, teacher);
    }
    //localhost:9090/api/teacher/count/1  бир мугалимдин канча студенти бар айди менен мугалимдин
    @GetMapping("/count/{id}")
    public Long countByIdT(@PathVariable("id") Long id) {
        teacherService.findByIdTeacher(id);
        return studentService.countByIdT(id);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteTeacherById(@PathVariable("id") Long id) {
        teacherService.deleteByIdTeacher(id);
    }
}
