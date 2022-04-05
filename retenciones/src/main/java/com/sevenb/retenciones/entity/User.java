package com.sevenb.retenciones.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

/**
 * User entity.
 */
@Entity
@Table(name = "users")
public class User implements Serializable {

    private final static long serialVersionUID = 1226873934060848455L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUser;

    @NotEmpty(message = "user.password.not-empty")
    private String password;

    @Email(message = "user.username.invalid-email-format")
    @NotEmpty(message = "user.username.not-empty")
    @Column(unique = true, length = 150)
    private String username;

    public User() {
        // No-arg constructor
    }

    public User(Long idUser, String password, String username) {
        this.idUser = idUser;
        this.password = password;
        this.username = username;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "User{" +
            "idUser=" + idUser +
            ", password='" + password + '\'' +
            ", username='" + username + '\'' +
            '}';
    }
}
