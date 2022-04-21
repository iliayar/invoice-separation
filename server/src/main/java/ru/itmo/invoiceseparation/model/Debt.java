
package ru.itmo.invoiceseparation.model;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "debts")
public class Debt implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    User from;

    @ManyToOne
    User to;

    Integer amount;

    public Debt() {
    }

    public Debt(User from, User to, Integer amount) {
        this.from = from;
        this.to = to;
        this.amount = amount;
    }

    public Integer getAmount() {
        return amount;
    }

    public User getTo() {
        return to;
    }

    public User getFrom() {
        return from;
    }
}
