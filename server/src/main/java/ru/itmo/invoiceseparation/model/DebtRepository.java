package ru.itmo.invoiceseparation.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

public interface DebtRepository extends JpaRepository<Debt, Integer> {

}
