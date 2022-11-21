package kg.kadyrbekov.service;

import kg.kadyrbekov.entity.Groups;
import kg.kadyrbekov.entity.Student;

import java.util.List;

public interface GroupService {

    List<Groups> findAllGroups();

    Groups saveGroups(Groups groups, Long courseId);

    Groups findById(Long id);

    Groups updateGroups(Groups groups,Long coursesId);

    List<Student> findByStudents(String  search);


    void deleteById(Long id);

}
