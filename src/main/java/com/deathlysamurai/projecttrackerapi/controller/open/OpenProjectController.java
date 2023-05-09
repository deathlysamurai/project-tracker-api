package com.deathlysamurai.projecttrackerapi.controller.open;

import com.deathlysamurai.projecttrackerapi.model.Enums.ProjectStage;
import com.deathlysamurai.projecttrackerapi.model.Project;
import com.deathlysamurai.projecttrackerapi.repository.ProjectRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/public/projects")
@CrossOrigin
public class OpenProjectController {
    private final ProjectRepository projects;
    public OpenProjectController(ProjectRepository projects) {
        this.projects = projects;
    }

    private Sort.Direction getSortDirection(String direction) {
        if (direction.equals("asc")) {
            return Sort.Direction.ASC;
        } else if (direction.equals("desc")) {
            return Sort.Direction.DESC;
        }

        return Sort.Direction.ASC;
    }

    @GetMapping("")
    public Page<Project> findAll(@RequestParam int page, @RequestParam int size, @RequestParam String direction, @RequestParam String sort, @RequestParam(required = false) String stageFilter) {
        Page<Project> responseProjects = null;
        PageRequest pr = PageRequest.of(page, size, Sort.by(getSortDirection(direction), sort));
        if(stageFilter == null) {
            responseProjects = projects.findAll(pr);
        } else {
            responseProjects = projects.findAllByStage(ProjectStage.valueOf(stageFilter), pr);
        }
        return responseProjects;
    }

    @GetMapping("/{id}")
    public Optional<Project> findById(@PathVariable("id") Long id) {
        if(!projects.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Project not found.");
        }
        Optional<Project> project = projects.findById(id);
        return project;
    }

    @GetMapping("/bugProjects")
    public List<Project> findAllWithBugs() {
        return projects.findAllProjectsWithBugs();
    }

    @GetMapping("/all")
    public List<Project> all() {
        return projects.findAll();
    }
}
