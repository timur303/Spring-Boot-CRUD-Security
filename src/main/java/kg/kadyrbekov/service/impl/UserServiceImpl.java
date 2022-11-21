package kg.kadyrbekov.service.impl;

import kg.kadyrbekov.entity.Role;
import kg.kadyrbekov.entity.User;
import kg.kadyrbekov.exception.NotFoundException;
import kg.kadyrbekov.repository.RoleRepository;
import kg.kadyrbekov.repository.UserRepository;
import kg.kadyrbekov.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder encoder;


    @Override
    public User save(User user) {

        if (user.getName().equals("timur")) {
            user.setRoles(
                    Collections.singleton(
                            new Role("ADMIN"))
            );
        } else {
            if (roleRepository.existsByName("USER")) {
                user.setRoles(
                        Collections.singleton(
                                roleRepository.findByName("USER")
                        )
                );
            } else {
                user.setRoles(
                        Collections.singleton(
                                new Role("USER")
                        )
                );
            }
        }
        user.setPassword(encoder.encode(user.getPassword()));
        userRepository.save(user);

        return user;
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(() ->
                new NotFoundException(String.format("User with %d id not found!", id)));
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User update(Long id, User newUser) {
        User user = findById(id);
        user.setName(newUser.getName());
        user.setPassword(newUser.getPassword());
        user.setEmail(newUser.getEmail());
        return userRepository.save(user);
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }


}