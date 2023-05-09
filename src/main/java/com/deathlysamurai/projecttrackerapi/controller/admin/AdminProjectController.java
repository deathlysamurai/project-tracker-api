package com.deathlysamurai.projecttrackerapi.controller.admin;

import com.deathlysamurai.projecttrackerapi.model.Project;
import com.deathlysamurai.projecttrackerapi.model.Task;
import com.deathlysamurai.projecttrackerapi.repository.ProjectRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api/private/projects")
@CrossOrigin
public class AdminProjectController {
    private final ProjectRepository projects;
    public AdminProjectController(ProjectRepository projects) {
        this.projects = projects;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/add")
    public void add(@Valid @RequestBody Project project) {
        projects.save(project);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    public void update(@RequestBody Project project, @PathVariable Long id) {
        if(!projects.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Project not found.");
        }
        Set<Task> tasks = project.getTasks();
        tasks.forEach((task -> {
            task.setProject(project);
        }));
        Optional<Project> currentProject = projects.findById(id);
        project.setBugs(currentProject.get().getBugs());
        projects.save(project);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        projects.deleteById(id);
    }
}