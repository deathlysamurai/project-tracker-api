package com.deathlysamurai.projecttrackerapi.repository;

import com.deathlysamurai.projecttrackerapi.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
}