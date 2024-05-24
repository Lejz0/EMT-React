package org.example.lab1.service;

import org.example.lab1.model.Host;
import org.example.lab1.model.dto.HostDto;

import java.util.List;
import java.util.Optional;

public interface HostService {
    List<Host> findAll();

    Optional<Host> findById(Long id);

    Optional<Host> findByName(String name);

    List<Host> findByCountry(Long country);

    Optional<Host> save(String name, String surname, Long country);

    Optional<Host> save(HostDto hostDto);

    Optional<Host> edit(Long id, String name, String surname, Long country);

    Optional<Host> edit(Long id, HostDto hostDto);

    void deleteById(Long id);
}
