package kg.kadyrbekov.controller;

import kg.kadyrbekov.entity.User;
import kg.kadyrbekov.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    public User findAll() {
        return (User) userService.findAll();
    }

    @PostMapping("/save")
    public String save(@RequestBody User user) {
        userService.save(user);
        return "Successfully saved!";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("{id}")
    public User findById(@PathVariable Long id) {
        return userService.findById(id);
    }

    @PatchMapping("/update/{id}")
    public User updateUser(@PathVariable("id") Long id, @RequestBody User user) {
        return userService.update(id, user);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteUserById(@PathVariable("id") Long id) {
        userService.delete(id);
    }
}

