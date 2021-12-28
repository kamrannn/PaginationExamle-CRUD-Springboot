package org.app.JakubsztubaSpringBoot.service;

import org.app.JakubsztubaSpringBoot.model.Note;
import org.app.JakubsztubaSpringBoot.model.User;
import org.app.JakubsztubaSpringBoot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public ResponseEntity<Object> findAllUsers() {
        List<User> userList = userRepository.findAll();
        return new ResponseEntity<>(userList, HttpStatus.OK);
    }

    public ResponseEntity<Object> addNewUser(User user) {
        userRepository.save(user);
        return new ResponseEntity<>("User Successfully added", HttpStatus.CREATED);
    }

    public Page<User> listUsersPaginated(int pageNo, int pageSize) {
        Pageable paging = PageRequest.of(pageNo, pageSize);
        Page<User> userList = userRepository.findAll(paging);
        if (userList.hasContent()) {
            return userList;
        } else {
            return null;
        }
    }

    public Page<User> listUsersPaginated(Pageable paging) {
        Page<User> userList = userRepository.findAll(paging);
        return userList;
    }

    public ResponseEntity<Object> deleteUser(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            userRepository.delete(user.get());
            return new ResponseEntity<>("User Successfully deleted", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No user against this Id", HttpStatus.OK);
        }
    }

    public ResponseEntity<Object> updateUser(User user) {
        Optional<User> optionalUser = userRepository.findById(user.getId());
        if (optionalUser.isPresent()) {
            userRepository.save(user);
            return new ResponseEntity<>("User Successfully updated", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No user against this Id", HttpStatus.OK);
        }
    }

    public ResponseEntity<Object> createUserNote(Long userId, Note note) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            optionalUser.get().getNotes().add(note);
            userRepository.save(optionalUser.get());
            return new ResponseEntity<>("Note is Successfully created", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No user against this Id", HttpStatus.OK);
        }
    }

}
