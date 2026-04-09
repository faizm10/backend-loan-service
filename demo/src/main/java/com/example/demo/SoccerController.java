package com.example.demo;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/soccer")
public class SoccerController {

    private final SoccerRepository soccerRepository;

    public SoccerController(SoccerRepository soccerRepository) {
        this.soccerRepository = soccerRepository;
    }
// 1. annotation tells Spring the HTTP method + path
// 2. method return type is what gets sent back as JSON
// 3. parameters come from the URL or request body

    @GetMapping                          // GET /soccer
    public List<Soccer> getAll() {
        return soccerRepository.findAll();
    }

    @GetMapping("/{name}")               // GET /soccer/Messi
    public ResponseEntity<Soccer> getOne(@PathVariable String name) {
        return soccerRepository.findById(name)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping                         // POST /soccer
    public ResponseEntity<Soccer> create(@RequestBody Soccer player) {
        Soccer saved = soccerRepository.save(player);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PutMapping("/{name}")               // PUT /soccer/Messi
    public ResponseEntity<Soccer> update(@PathVariable String name, @RequestBody Soccer updated) {
        return soccerRepository.findById(name).map(existing -> {
            existing.setGoals(updated.getGoals());
            return ResponseEntity.ok(soccerRepository.save(existing));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{name}")            // DELETE /soccer/Messi
    public ResponseEntity<Void> delete(@PathVariable String name) {
        if (!soccerRepository.existsById(name)) {
            return ResponseEntity.notFound().build();
        }
        soccerRepository.deleteById(name);
        return ResponseEntity.noContent().build();
    }

}
