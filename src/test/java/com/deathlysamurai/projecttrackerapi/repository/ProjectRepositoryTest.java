package com.deathlysamurai.projecttrackerapi.repository;

import com.deathlysamurai.projecttrackerapi.model.Enums.ProjectStage;
import com.deathlysamurai.projecttrackerapi.model.Project;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ProjectRepositoryTest {

    @Autowired
    private ProjectRepository projectRepository;

    @Test
    public void getAllProjects() {
        List<Project> projects = projectRepository.findAll();
        System.out.println("projects = " + projects);
    }

}