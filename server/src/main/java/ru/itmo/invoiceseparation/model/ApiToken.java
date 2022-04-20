package ru.itmo.invoiceseparation.model;

import java.io.Serializable;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "tokens")
public class ApiToken implements Serializable {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
        name = "UUID",
        strategy = "org.hibernate.id.UUIDGenerator"
        )
    private String id;

    @OneToOne
    private User user;

    public ApiToken() {
    }

    public ApiToken(User user) {
        this.user = user;
    }

    public String getToken() {
        return id;
    }
}
