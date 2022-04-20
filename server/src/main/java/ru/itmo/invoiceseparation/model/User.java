package ru.itmo.invoiceseparation.model;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String username;
    private String password;

    @OneToOne(cascade = CascadeType.ALL)
    private ApiToken token;

    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public void setApiToken(ApiToken token) {
        this.token = token;
    }

    public ApiToken getApiToken() {
        return token;
    }

    public boolean validatePassword(String password) {
        return this.password.equals(password);
    }

    public String getUsername() {
        return username;
    }
}
