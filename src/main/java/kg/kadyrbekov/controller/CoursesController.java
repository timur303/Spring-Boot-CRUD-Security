package kg.kadyrbekov.controller;

import kg.kadyrbekov.entity.Courses;
import kg.kadyrbekov.service.CourserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/courses")
public class CoursesController {

    private final CourserService courserService;

    @GetMapping
    public List<Courses> findAllCourses() {
        return courserService.findAllCourses();
    }

    @GetMapping("/{id}")
    public Optional<Courses> findById(@PathVariable("id") Long id) {
        return courserService.findById(id);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/save")
    public Courses saveCourses(@RequestBody Courses courses) {
        return courserService.saveCourses(courses, courses.getCompanyId());
    }

    @PatchMapping("/update/{id}")
    public Courses updateCourse(@RequestBody Courses courses, @PathVariable("id") Long companyId) {
        courses.setCompanyId(companyId);
        return courserService.updateCourses(courses,courses.getCompanyId());
    }

    @DeleteMapping("/delete/{id}")
    public void deleteCoursesById(@PathVariable("id") Long id) {
        courserService.deleteById(id);
    }
}
