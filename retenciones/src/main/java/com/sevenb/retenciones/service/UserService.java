package com.sevenb.retenciones.service;

import org.springframework.http.ResponseEntity;
import com.sevenb.retenciones.entity.User;

/**
 * User service interface
 */
public interface UserService {

    public ResponseEntity<?> findByUsername(String username);

    public ResponseEntity<?> findAll();

    public ResponseEntity<?> login(User user);

    public ResponseEntity<?> saveUser(User user);
}
