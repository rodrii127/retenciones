package com.sevenb.retenciones.controller.implementation;

import javax.validation.Valid;
import javax.validation.constraints.Email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.sevenb.retenciones.entity.User;
import com.sevenb.retenciones.service.definition.UserService;

/**
 * User controller. Defines path and parameters required for accessing the User database and logging in
 */
@RestController
@RequestMapping(value = "/users", produces = "application/json")
public class UserControllerImp {

    private UserService userService;

    @Autowired
    public UserControllerImp(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<?> saveUser(@RequestBody @Valid User user) {
        return userService.saveUser(user);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid User user) {

        return userService.login(user);
    }

    @GetMapping("/find-all")
    public ResponseEntity<?> findAllUsers() {
        return userService.findAll();
    }

    @GetMapping("/{username}/user")
    public ResponseEntity<?> findUser(@PathVariable @Valid @Email String username) {
        return userService.findByUsername(username);
    }
}