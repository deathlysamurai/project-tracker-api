package com.deathlysamurai.projecttrackerapi.model;

import com.deathlysamurai.projecttrackerapi.model.Enums.TechnologyType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "technology")
public class Technology {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    Long id;

    @Column(name = "name", nullable = false)
    String name;

    @Column(name = "type", nullable = false)
    @Enumerated(EnumType.STRING)
    TechnologyType type;

    @Column(name = "short_name")
    String shortName;

    @ManyToMany(mappedBy = "technologies")
    @JsonIgnore
    Set<Project> projects;

    public Technology() {}

    public Technology(String name, TechnologyType type, String shortName, Set<Project> projects) {
        this.name = name;
        this.type = type;
        this.shortName = shortName;
        this.projects = projects;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TechnologyType getType() {
        return type;
    }

    public void setType(TechnologyType type) {
        this.type = type;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public Set<Project> getProjects() { return projects; }

    public void setProjects(Set<Project> projects) { this.projects = projects; }

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type=" + type +
                ", shortName='" + shortName + '\'' +
                ", projects='" + projects +
                '}';
    }
}