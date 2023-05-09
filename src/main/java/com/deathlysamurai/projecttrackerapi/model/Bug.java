package com.deathlysamurai.projecttrackerapi.model;

import com.deathlysamurai.projecttrackerapi.model.Enums.BugSeverity;
import com.deathlysamurai.projecttrackerapi.model.Enums.BugStatus;
import jakarta.persistence.*;

@Entity
@Table(name = "bug")
public class Bug {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    Long id;

    @Column(name = "title", nullable = false)
    String title;

    @Column(name = "description", nullable = false)
    String description;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    BugStatus status;

    @Column(name = "notes")
    String notes;

    @Column(name = "severity", nullable = false)
    @Enumerated(EnumType.STRING)
    BugSeverity severity;

    @ManyToOne(fetch = FetchType.EAGER, optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "project_id", nullable = false)
    Project project;

    public Bug() {}

    public Bug(String title, String description, BugStatus status, String notes, BugSeverity severity, Project project) {
        this.title = title;
        this.description = description;
        this.status = status;
        this.notes = notes;
        this.severity = severity;
        this.project = project;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BugStatus getStatus() {
        return status;
    }

    public void setStatus(BugStatus status) {
        this.status = status;
    }

    public String getNotes() { return notes; }

    public void setNotes(String notes) { this.notes = notes; }

    public BugSeverity getSeverity() { return severity; }

    public void setSeverity(BugSeverity severity) { this.severity = severity; }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                ", notes='" + notes + '\'' +
                ", severity=" + severity +
                '}';
    }
}
