package com.deathlysamurai.projecttrackerapi.repository;

import com.deathlysamurai.projecttrackerapi.model.Technology;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TechnologyRepository extends JpaRepository<Technology, Long> {
}