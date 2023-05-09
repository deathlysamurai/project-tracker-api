package com.deathlysamurai.projecttrackerapi.controller.admin;

import com.deathlysamurai.projecttrackerapi.model.Bug;
import com.deathlysamurai.projecttrackerapi.model.Project;
import com.deathlysamurai.projecttrackerapi.model.Task;
import com.deathlysamurai.projecttrackerapi.repository.BugRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api/private/bugs")
@CrossOrigin
public class AdminBugController {
    private final BugRepository bugs;
    public AdminBugController(BugRepository bugs) {
        this.bugs = bugs;
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    public void update(@RequestBody Bug bug, @PathVariable Long id) {
        if(!bugs.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Bug not found.");
        }
        bugs.save(bug);
    }
}