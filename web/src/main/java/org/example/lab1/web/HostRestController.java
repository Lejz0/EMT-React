package org.example.lab1.web;

import org.example.lab1.model.Host;
import org.example.lab1.model.dto.HostDto;
import org.example.lab1.service.HostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/host")
public class HostRestController {
    private final HostService hostService;

    public HostRestController(HostService hostService) {
        this.hostService = hostService;
    }

    @GetMapping
    public List<Host> getAll()
    {
        return this.hostService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Host> getById(@PathVariable Long id)
    {
        return this.hostService.findById(id)
                .map(host -> ResponseEntity.ok().body(host))
                .orElseGet( () -> ResponseEntity.badRequest().build());
    }

    @PostMapping("/add")
    public ResponseEntity<Host> addHost(@RequestBody HostDto hostDto)
    {
        return this.hostService.save(hostDto)
                .map(host -> ResponseEntity.ok().body(host))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PostMapping("/edit/{id}")
    public ResponseEntity<Host> editHost(@PathVariable Long id, @RequestBody HostDto hostDto)
    {
        return this.hostService.edit(id, hostDto)
                .map(host -> ResponseEntity.ok().body(host))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PostMapping("/delete/{id}")
    public  ResponseEntity<Host> deleteById(@PathVariable Long id)
    {
        this.hostService.deleteById(id);
        if (this.hostService.findById(id).isEmpty())
        {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }
}
