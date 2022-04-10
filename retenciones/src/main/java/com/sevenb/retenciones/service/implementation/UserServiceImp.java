package com.sevenb.retenciones.service.implementation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.persistence.EntityExistsException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.apache.commons.collections4.CollectionUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.sevenb.retenciones.config.exception.BadRequestException;
import com.sevenb.retenciones.config.exception.NotFoundException;
import com.sevenb.retenciones.entity.User;
import com.sevenb.retenciones.repository.UserRepository;
import com.sevenb.retenciones.security.JWTGenerator;
import com.sevenb.retenciones.security.entity.JWTUser;
import com.sevenb.retenciones.service.definition.UserService;
import com.sevenb.retenciones.utils.JWTConstants;

/**
 * UserService implementation. Defines how methods will access Users database.
 */
@Service
public class UserServiceImp implements UserService {

    private static final Logger LOG = LoggerFactory.getLogger(UserServiceImp.class);
    private UserRepository userRepository;

    private JWTGenerator jwtGenerator;

    @Autowired
    public UserServiceImp(UserRepository userRepository, JWTGenerator jwtGenerator) {
        this.userRepository = userRepository;
        this.jwtGenerator = jwtGenerator;
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<?> findByUsername(String username) {
        User userDB = userRepository.findByUsername(username);
        if (Objects.nonNull(userDB)) {
            return new ResponseEntity<>(userDB, HttpStatus.FOUND);
        }
        LOG.error("User Service findByUsername can't find a User related to the email sent");
        throw new NotFoundException("user-service.user.not-found");
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<?> findAll() {
        List<User> usersDB = userRepository.findAll();
        if (CollectionUtils.isNotEmpty(usersDB)) {
            return new ResponseEntity<>(usersDB, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Override
    @Transactional
    public ResponseEntity<?> login(User user) {
        try {
            JWTUser jwtUser = new JWTUser();
            User userDB = userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
            if (Objects.nonNull(userDB)) {
                jwtUser.setUsername(userDB.getUsername());
                jwtUser.setId(userDB.getIdUser());
                jwtUser.setRole(JWTConstants.DEFAULT_ROLE);
                Map<String, String> loginToken = new HashMap<>();
                loginToken.put("loginToken", jwtGenerator.generate(jwtUser));
                return new ResponseEntity<Map>(loginToken, HttpStatus.CREATED);
            }
        } catch (Exception exception) {
            LOG.error("Login failure. Username and/or password might be missing or incorrect");
            throw new BadRequestException(exception.getMessage());
        }
        return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
    }

    @Override
    @Transactional
    public ResponseEntity<?> saveUser(User user) {
        User userDB = userRepository.findByUsername(user.getUsername());
        if (Objects.isNull(userDB)) {
            userRepository.save(user);
            return new ResponseEntity<>(user, HttpStatus.CREATED);
        }
        LOG.error("Username already taken.");
        throw new EntityExistsException("user-service.user.entity-already-exists");
    }
}
