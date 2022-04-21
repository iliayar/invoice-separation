package ru.itmo.invoiceseparation.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    @ManyToMany(targetEntity=User.class)
    @JoinTable(name="contacts",
        joinColumns={@JoinColumn(name="user_a_id", referencedColumnName="id")},
        inverseJoinColumns={@JoinColumn(name="user_b_id", referencedColumnName="id")}
    )
    private List<User> contacts = new ArrayList<>();

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

    public void addIncomingDebt(Debt debt) {
        incomingDebts.add(debt);
    }

    public void addOutcomingDebt(Debt debt) {
        outcomingDebts.add(debt);
    }

    public List<User> getContacts() {
        return contacts;
    }

    public void addContacts(List<User> contacts) {
        this.contacts.addAll(contacts);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return id.equals(user.id);
    }
}
