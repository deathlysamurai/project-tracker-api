package com.deathlysamurai.projecttrackerapi.controller.admin;

import com.deathlysamurai.projecttrackerapi.model.Task;
import com.deathlysamurai.projecttrackerapi.model.Technology;
import com.deathlysamurai.projecttrackerapi.repository.TechnologyRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/private/technologies")
@CrossOrigin
public class AdminTechnologyController {
    private final TechnologyRepository technologies;
    public AdminTechnologyController(TechnologyRepository technologies) { this.technologies = technologies; }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/add")
    public List<Technology> add(@Valid @RequestBody Technology technology) {
        technologies.save(technology);
        return technologies.findAll();
    }

    @PutMapping("/{id}")
    public List<Technology> update(@RequestBody Technology technology, @PathVariable Long id) {
        if(!technologies.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Technology not found.");
        }
        technologies.save(technology);
        return technologies.findAll();
    }
}