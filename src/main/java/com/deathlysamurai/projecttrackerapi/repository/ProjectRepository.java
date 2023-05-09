package com.deathlysamurai.projecttrackerapi.repository;

import com.deathlysamurai.projecttrackerapi.model.Enums.ProjectStage;
import com.deathlysamurai.projecttrackerapi.model.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    Page<Project> findAllByStage(ProjectStage stage, Pageable pageable);

    @Query("SELECT p FROM Project p WHERE p.id IN (SELECT b.project FROM Bug b) ORDER BY p.id")
    List<Project> findAllProjectsWithBugs();
}
