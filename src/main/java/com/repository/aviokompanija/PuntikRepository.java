package com.repository.aviokompanija;

import com.model.aviokompanija.Putnik;
import com.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PuntikRepository extends JpaRepository<Putnik, Long> {
    public Optional<Putnik> findByUser(User user);
}
