package kg.kadyrbekov.service;

import kg.kadyrbekov.entity.User;

import java.util.List;

public interface UserService {

        User save(User user);

        User findById(Long id);

        List<User> findAll();

        void delete(Long id);

        User update(Long id, User newUser);
    }


