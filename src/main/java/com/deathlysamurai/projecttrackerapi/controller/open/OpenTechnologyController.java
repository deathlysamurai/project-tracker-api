package com.deathlysamurai.projecttrackerapi.controller.open;

import com.deathlysamurai.projecttrackerapi.model.Enums.ProjectStage;
import com.deathlysamurai.projecttrackerapi.model.Project;
import com.deathlysamurai.projecttrackerapi.model.Technology;
import com.deathlysamurai.projecttrackerapi.repository.ProjectRepository;
import com.deathlysamurai.projecttrackerapi.repository.TechnologyRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/public/technologies")
@CrossOrigin
public class OpenTechnologyController {
    private final TechnologyRepository technologies;
    public OpenTechnologyController(TechnologyRepository technologies) {
        this.technologies = technologies;
    }

    @GetMapping("")
    public List<Technology> findAll() {
        return technologies.findAll();
    }
}