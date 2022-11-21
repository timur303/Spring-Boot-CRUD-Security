package kg.kadyrbekov.controller;

import kg.kadyrbekov.entity.Courses;
import kg.kadyrbekov.entity.Groups;
import kg.kadyrbekov.entity.Student;
import kg.kadyrbekov.service.CourserService;
import kg.kadyrbekov.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/groups")
public class GroupController {

    private final CourserService courserService;
    private final GroupService groupService;

    @Autowired
    public GroupController(CourserService courserService, GroupService groupService) {
        this.courserService = courserService;
        this.groupService = groupService;
    }

    @GetMapping("courses")
    public List<Courses> getAllCourses() {
        return courserService.findAllCourses();
    }

    @GetMapping
    public List<Groups> getAllGroups() {
        return groupService.findAllGroups();
    }

    @GetMapping("/{id}")
    public Optional<Groups> findByIdGroups(@PathVariable("id") Long id) {
        return Optional.ofNullable(groupService.findById(id));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/save")
    public Groups saveGroups(@RequestBody Groups groups) {
        return groupService.saveGroups(groups, groups.getCourseId());
    }

    @PatchMapping("/update/{id}")
    public Groups updateGroups(@RequestBody Groups groups, @PathVariable("id") Long courseId) {
        groups.setCourseId(courseId);
        return groupService.updateGroups(groups, courseId);
    }
//localhost:9090/api/groups/search?student=Timur издоо жолу
    @GetMapping("/search")
    public String searchStudent(@RequestParam("student") String search) {
        return groupService.findByStudents(search).toString();


    }

    @DeleteMapping("/delete/{id}")
    public void deleteGroupsById(@PathVariable("id") Long id) {
        groupService.deleteById(id);
    }
}
