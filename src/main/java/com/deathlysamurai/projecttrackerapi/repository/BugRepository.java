package com.deathlysamurai.projecttrackerapi.repository;

import com.deathlysamurai.projecttrackerapi.model.Bug;
import com.deathlysamurai.projecttrackerapi.model.Enums.BugSeverity;
import com.deathlysamurai.projecttrackerapi.model.Enums.BugStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BugRepository extends JpaRepository<Bug, Long> {
    Page<Bug> findAllBySeverity(BugSeverity severity, Pageable pageable);
    Page<Bug> findAllByStatus(BugStatus status, Pageable pageable);
    Page<Bug> findAllBySeverityAndStatus(BugSeverity severity, BugStatus status, Pageable pageable);
    Page<Bug> findAllByProjectId(Long projectId, Pageable pageable);
    Page<Bug> findAllByProjectIdAndSeverityAndStatus(Long projectId, BugSeverity severity, BugStatus status, Pageable pageable);
    Page<Bug> findAllByProjectIdAndStatus(Long projectId, BugStatus status, Pageable pageable);
    Page<Bug> findAllByProjectIdAndSeverity(Long projectId, BugSeverity severity, Pageable pageable);
}
