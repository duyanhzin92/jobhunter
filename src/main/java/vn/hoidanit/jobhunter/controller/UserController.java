package vn.hoidanit.jobhunter.controller;

import org.springframework.web.bind.annotation.RestController;

import vn.hoidanit.jobhunter.domain.User;
import vn.hoidanit.jobhunter.service.UserService;
import vn.hoidanit.jobhunter.util.error.IdInvalidException;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping(value = "/users")
public class UserController {
    private final UserService userService;

    private final PasswordEncoder passwordEncoder;

    public UserController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("")
    public ResponseEntity<User> createNewUser(@RequestBody User postManUser) {
        String hashPassword = this.passwordEncoder.encode(postManUser.getPassword());
        postManUser.setPassword(hashPassword);
        User ericUser = this.userService.createUser(postManUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(ericUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") long id)
            throws IdInvalidException {
        if (id >= 1500) {
            throw new IdInvalidException("Id khong lon hown 1501");
        }

        this.userService.deleteUser(id);
        return ResponseEntity.ok("ericUser");
        // return ResponseEntity.status(HttpStatus.OK).body("ericUser");
    }

    // fetch user by id
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") long id) {
        User fetchUser = this.userService.getUserById(id);
        // return ResponseEntity.ok(fetchUser);
        return ResponseEntity.status(HttpStatus.OK).body(fetchUser);
    }

    // fetch all users
    @GetMapping("")
    public ResponseEntity<List<User>> getAllUser() {
        // return ResponseEntity.ok(this.userService.fetchAllUser());
        return ResponseEntity.status(HttpStatus.OK).body(this.userService.getListUsers());
    }

    @PutMapping("")
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        User ericUser = this.userService.updatedUser(user);
        return ResponseEntity.ok(ericUser);
    }

}
