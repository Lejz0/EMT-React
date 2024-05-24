package org.example.lab1.service.impl;

import org.example.lab1.model.Country;
import org.example.lab1.model.Host;
import org.example.lab1.model.dto.HostDto;
import org.example.lab1.model.exceptions.InvalidCountryIdException;
import org.example.lab1.model.exceptions.InvalidHostIdException;
import org.example.lab1.repository.CountryRepository;
import org.example.lab1.repository.HostRepository;
import org.example.lab1.service.HostService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HostServiceImpl implements HostService {
    public HostServiceImpl(CountryRepository countryRepository, HostRepository hostRepository) {
        this.countryRepository = countryRepository;
        this.hostRepository = hostRepository;
    }

    private final CountryRepository countryRepository;
    private final HostRepository hostRepository;
    @Override
    public List<Host> findAll() {
        return this.hostRepository.findAll();
    }

    @Override
    public Optional<Host> findById(Long id) {
        return this.hostRepository.findById(id);
    }

    @Override
    public Optional<Host> findByName(String name) {
        return this.hostRepository.findByName(name);
    }

    @Override
    public List<Host> findByCountry(Long country) {
        Country country1 = this.countryRepository.findById(country).orElseThrow(() -> new InvalidCountryIdException(country));
        return this.hostRepository.findAllByCountry(country1);
    }

    @Override
    public Optional<Host> save(String name, String surname, Long country) {
        Country country1 = this.countryRepository.findById(country).orElseThrow(() -> new InvalidCountryIdException(country));
        Host host = new Host(name, surname, country1);
        this.hostRepository.save(host);
        return Optional.of(host);
    }

    @Override
    public Optional<Host> save(HostDto hostDto) {
        Country country = this.countryRepository.findById(hostDto.getCountryId()).orElseThrow(() -> new InvalidCountryIdException(hostDto.getCountryId()));

        Host host = new Host(hostDto.getName(), hostDto.getSurname(), country);

        this.hostRepository.save(host);
        return Optional.of(host);
    }


    @Override
    public Optional<Host> edit(Long id, String name, String surname, Long country) {
        Country country1 = this.countryRepository.findById(country).orElseThrow(() -> new InvalidCountryIdException(country));
        Host host = this.hostRepository.findById(id).orElseThrow(() -> new InvalidHostIdException(id));

        host.setName(name);
        host.setSurname(surname);
        host.setCountry(country1);

        this.hostRepository.save(host);
        return Optional.of(host);
    }

    @Override
    public Optional<Host> edit(Long id, HostDto hostDto) {
        Country country = this.countryRepository.findById(hostDto.getCountryId()).orElseThrow(() -> new InvalidCountryIdException(hostDto.getCountryId()));
        Host host = this.hostRepository.findById(id).orElseThrow(() -> new InvalidHostIdException(id));

        host.setName(hostDto.getName());
        host.setSurname(hostDto.getSurname());
        host.setCountry(country);

        this.hostRepository.save(host);
        return Optional.of(host);
    }

    @Override
    public void deleteById(Long id) {
        this.hostRepository.deleteById(id);
    }
}
