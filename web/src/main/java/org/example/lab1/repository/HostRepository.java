package org.example.lab1.repository;

import org.example.lab1.model.Country;
import org.example.lab1.model.Host;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HostRepository extends JpaRepository<Host, Long> {
    Optional<Host> findByName(String name);

    List<Host> findAllByCountry(Country country);
}
