package kg.kadyrbekov.controller;

import kg.kadyrbekov.entity.Groups;
import kg.kadyrbekov.entity.Student;
import kg.kadyrbekov.service.GroupService;
import kg.kadyrbekov.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/student")
public class StudentController {

    private final StudentService studentService;
    private final GroupService groupService;

    @Autowired
    public StudentController(StudentService studentService, GroupService groupService) {
        this.studentService = studentService;
        this.groupService = groupService;
    }

    @GetMapping("/groups")
    public List<Groups> findAllGroup() {
        return groupService.findAllGroups();
    }

    @GetMapping
    public List<Student> findAllStudent() {
        return studentService.findAllStudent();
    }


    //localhost:9090/api/student/firstName эгер сортту аты менен жасай турган болсо
    @GetMapping("/{sort}")
    public String sortStudent(@PathVariable String sort) {
        return studentService.findStudentWithSort(sort).toString();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/save")
    public Student saveStudent(@RequestBody Student student) {
        return studentService.saveStudent(student, student.getGroupId());
    }

    @PatchMapping("/update/{id}")
    public Student updateStudent(@RequestBody Student student, @PathVariable("id") Long id) {
        return studentService.updateStudent(student, id);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteStudentById(@PathVariable("id") Long id) {
        studentService.deleteStudentById(id);
    }
}
