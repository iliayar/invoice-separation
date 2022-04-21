package ru.itmo.invoiceseparation.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

public interface DebtRepository extends JpaRepository<Debt, Integer> {

    List<Debt> findByFromAndTo (User from, User to);

    @Transactional
    List<Debt> deleteByFromAndTo (User from, User to);

}
