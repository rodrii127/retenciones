package com.sevenb.retenciones.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.sevenb.retenciones.entity.User;

/**
 * User repository. Access users database
 */
public interface UserRepository extends JpaRepository<User, Long> {

    public User findByUsername(String username);

    public User findByUsernameAndPassword(String username, String password);

    public List<User> findAll();

    public User save(User user);

}