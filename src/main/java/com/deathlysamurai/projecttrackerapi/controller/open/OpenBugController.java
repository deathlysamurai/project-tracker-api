package com.deathlysamurai.projecttrackerapi.controller.open;

import com.deathlysamurai.projecttrackerapi.model.Bug;
import com.deathlysamurai.projecttrackerapi.model.BugAdd;
import com.deathlysamurai.projecttrackerapi.model.Enums.BugSeverity;
import com.deathlysamurai.projecttrackerapi.model.Enums.BugStatus;
import com.deathlysamurai.projecttrackerapi.model.Enums.ProjectStage;
import com.deathlysamurai.projecttrackerapi.model.Project;
import com.deathlysamurai.projecttrackerapi.model.Technology;
import com.deathlysamurai.projecttrackerapi.repository.BugRepository;
import com.deathlysamurai.projecttrackerapi.repository.ProjectRepository;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/public/bugs")
@CrossOrigin
public class OpenBugController {
    private final BugRepository bugs;
    private final ProjectRepository projects;
    public OpenBugController(BugRepository bugs, ProjectRepository projects) {
        this.bugs = bugs;
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
    public Page<Bug> findAll(
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer size,
            @RequestParam(required = false) String direction,
            @RequestParam(required = false) String sort,
            @RequestParam(required = false) String severity,
            @RequestParam(required = false) String status,
            @RequestParam(required = false) Long projectId
    ) {
        Page<Bug> responseBugs = null;
        Pageable pr = Pageable.unpaged();
        if(page != null && size != null && direction != null && sort != null) {
            pr = PageRequest.of(page, size, Sort.by(getSortDirection(direction), sort));
        }
        if(projectId != null && severity != null && status != null) {
            responseBugs = bugs.findAllByProjectIdAndSeverityAndStatus(projectId, BugSeverity.valueOf(severity), BugStatus.valueOf(status), pr);
        } else if (projectId != null && severity != null) {
            responseBugs = bugs.findAllByProjectIdAndSeverity(projectId, BugSeverity.valueOf(severity), pr);
        } else if (projectId != null && status != null) {
            responseBugs = bugs.findAllByProjectIdAndStatus(projectId, BugStatus.valueOf(status), pr);
        } else if (severity != null && status != null) {
            responseBugs = bugs.findAllBySeverityAndStatus(BugSeverity.valueOf(severity), BugStatus.valueOf(status), pr);
        } else if (projectId != null) {
            responseBugs = bugs.findAllByProjectId(projectId, pr);
        } else if (severity != null) {
            responseBugs = bugs.findAllBySeverity(BugSeverity.valueOf(severity), pr);
        } else if (status != null) {
            responseBugs = bugs.findAllByStatus(BugStatus.valueOf(status), pr);
        } else {
            responseBugs = bugs.findAll(pr);
        }
        return responseBugs;
    }

    @GetMapping("/{id}")
    public Optional<Bug> findById(@PathVariable("id") Long id) {
        if(!bugs.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Bug not found.");
        }
        Optional<Bug> bug = bugs.findById(id);
        return bug;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/add")
    public void add(@RequestBody BugAdd bugAdd) {
        System.out.println(bugAdd);
        Bug bug = bugAdd.getBug();
        Optional<Project> project = projects.findById(bugAdd.getProjectId());
        bug.setProject(project.get());
        bugs.save(bug);
    }
}
