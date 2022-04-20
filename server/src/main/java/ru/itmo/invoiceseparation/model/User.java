package ru.itmo.invoiceseparation.model;

import java.io.Serializable;
import java.util.List;

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

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "to")
    private List<Debt> incomingDebts;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "from")
    private List<Debt> outcomingDebts;

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
