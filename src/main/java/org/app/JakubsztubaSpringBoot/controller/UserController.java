package org.app.JakubsztubaSpringBoot.controller;

import org.app.JakubsztubaSpringBoot.model.Note;
import org.app.JakubsztubaSpringBoot.model.User;
import org.app.JakubsztubaSpringBoot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.ZonedDateTime;

@RestController
@RequestMapping("/users")
public class UserController {
    UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/list")
    public ResponseEntity<Object> listOfUsers() {
        return userService.findAllUsers();
    }

    @PostMapping("/add")
    public ResponseEntity<Object> add(@RequestBody User user) {
        user.setCreateDate(ZonedDateTime.now());
        return userService.addNewUser(user);
    }

    @GetMapping("/paginated-list")
    public ResponseEntity<Object> getAllUsers(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize) {
        Page<User> list = userService.listUsersPaginated(pageNo, pageSize);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/listPageable2")
    Page<User> usersListPageable(Pageable pageable) {
        return userService.listUsersPaginated(pageable);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Object> delete(@RequestParam Long userId) {
        return userService.deleteUser(userId);
    }

    @PutMapping("/update")
    public ResponseEntity<Object> update(@RequestBody User user) {
        return userService.updateUser(user);
    }

    @PostMapping("/create-note")
    public ResponseEntity<Object> createNotes(@RequestParam Long userId, @RequestBody Note note) {
        return userService.createUserNote(userId, note);
    }
}
