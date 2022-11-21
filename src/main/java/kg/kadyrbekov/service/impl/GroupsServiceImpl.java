package kg.kadyrbekov.service.impl;

import kg.kadyrbekov.entity.Courses;
import kg.kadyrbekov.entity.Groups;
import kg.kadyrbekov.entity.Student;
import kg.kadyrbekov.repository.CoursesRepository;
import kg.kadyrbekov.repository.GroupsRepository;
import kg.kadyrbekov.repository.StudentRepository;
import kg.kadyrbekov.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class GroupsServiceImpl implements GroupService {

    private final StudentRepository studentRepository;
    private final GroupsRepository groupsRepository;
    private final CoursesRepository coursesRepository;

    @Autowired
    public GroupsServiceImpl(GroupsRepository groupsRepository, CoursesRepository coursesRepository,StudentRepository studentRepository) {
        this.groupsRepository = groupsRepository;
        this.coursesRepository = coursesRepository;
        this.studentRepository=studentRepository;
    }

    @Override
    public List<Groups> findAllGroups() {
        return groupsRepository.findAll();
    }

    @Override
    public Groups saveGroups(Groups groups, Long courseId) {
        Courses courses = coursesRepository.findById(courseId).get();
        groups.setCourses(Collections.singletonList(courses));
        return groupsRepository.save(groups);
    }

    @Override
    public Groups findById(Long id) {
        return groupsRepository.findById(id).get();
    }

    @Override
    public Groups updateGroups(Groups newGroups, Long coursesId) {
        Groups groups = findById(coursesId);
        groups.setGroupName(newGroups.getGroupName());
        groups.setDateOfStart(newGroups.getDateOfStart());
        groups.setDateOfFinish(newGroups.getDateOfFinish());
        groups.setCourseId(newGroups.getCourseId());
        return groupsRepository.save(groups);
    }

    @Override
    public List<Student> findByStudents(String search) {
        if (search !=null){
            return groupsRepository.findByStudents(search);
        }
        return  studentRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        groupsRepository.deleteById(id);
    }
}
