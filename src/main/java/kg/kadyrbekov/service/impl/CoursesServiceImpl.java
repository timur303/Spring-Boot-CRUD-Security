package kg.kadyrbekov.service.impl;

import kg.kadyrbekov.entity.Company;
import kg.kadyrbekov.entity.Courses;
import kg.kadyrbekov.repository.CompanyRepository;
import kg.kadyrbekov.repository.CoursesRepository;
import kg.kadyrbekov.service.CourserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CoursesServiceImpl implements CourserService {

    private final CompanyRepository companyRepository;
    private final CoursesRepository coursesRepository;

    @Autowired
    public CoursesServiceImpl(CompanyRepository companyRepository, CoursesRepository coursesRepository) {
        this.companyRepository = companyRepository;
        this.coursesRepository = coursesRepository;
    }


    @Override
    public List<Courses> findAllCourses() {
        return coursesRepository.findAll();
    }

    @Override
    public Optional<Courses> findById(Long id) {
        return coursesRepository.findById(id);
    }

    @Override
    public Courses saveCourses(Courses courses, Long companyId) {
        Company company = companyRepository.findById(companyId).get();
        courses.setCompany(company);
        return coursesRepository.save(courses);
    }

    @Override
    public Courses updateCourses(Courses newCourses, Long id ) {
        Courses courses = findById(id).get();
        courses.setCourseName(newCourses.getCourseName());
        courses.setDuration(newCourses.getDuration());
        courses.setCompanyId(newCourses.getCompanyId());
        return coursesRepository.save(courses);

    }

    @Override
    public void deleteById(Long id) {
        coursesRepository.deleteById(id);
    }
}
