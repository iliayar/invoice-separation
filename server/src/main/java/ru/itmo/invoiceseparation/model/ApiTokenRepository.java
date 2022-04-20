package ru.itmo.invoiceseparation.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApiTokenRepository extends JpaRepository<ApiToken, Integer> {

    ApiToken findByUser(User user);

    ApiToken findById(String id);

}
