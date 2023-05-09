package com.deathlysamurai.projecttrackerapi.model;

import com.deathlysamurai.projecttrackerapi.model.Enums.ProjectStage;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "project")
public class Project {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id", updatable = false)
        Long id;

        @Column(name = "title", nullable = false, unique = true)
        String title;

        @Column(name = "stage", nullable = false)
        @Enumerated(EnumType.STRING)
        ProjectStage stage;

        @Column(name = "display", nullable = false)
        Boolean display;

        @Column(name = "url")
        String url;

        @Column(name = "notes")
        String notes;

        @OneToMany(mappedBy = "project", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
        Set<Task> tasks;

        @ManyToMany
        @JoinTable(
                name = "project_technologies",
                joinColumns = @JoinColumn(name = "project_id"),
                inverseJoinColumns = @JoinColumn(name = "technology_id")
        )
        Set<Technology> technologies;

        @OneToMany(mappedBy = "project", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
        @JsonIgnore
        Set<Bug> bugs;

        public Project() {}

        public Project(String title, ProjectStage stage, Boolean display, String url, Set<Task> tasks, String notes, Set<Technology> technologies, Set<Bug> bugs) {
                this.title = title;
                this.stage = stage;
                this.display = display;
                this.url = url;
                this.tasks = tasks;
                this.notes = notes;
                this.technologies = technologies;
                this.bugs = bugs;
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

        public ProjectStage getStage() {
                return stage;
        }

        public void setStage(ProjectStage stage) {
                this.stage = stage;
        }

        public Boolean getDisplay() {
                return display;
        }

        public void setDisplay(Boolean display) {
                this.display = display;
        }

        public String getUrl() {
                return url;
        }

        public void setUrl(String url) {
                this.url = url;
        }

        public Set<Task> getTasks() { return tasks; }

        public void setTasks(Set<Task> tasks) { this.tasks = tasks; }

        public String getNotes() { return notes; }

        public void setNotes(String notes) { this.notes = notes; }

        public Set<Technology> getTechnologies() { return technologies; }

        public void setTechnologies(Set<Technology> technologies) { this.technologies = technologies; }

        public Set<Bug> getBugs() { return bugs; }

        public void setBugs(Set<Bug> tasks) { this.bugs = bugs; }

        @Override
        public String toString() {
                return "Project{" +
                        "id=" + id +
                        ", title='" + title + '\'' +
                        ", stage=" + stage +
                        ", display=" + display +
                        ", url='" + url + '\'' +
                        ", tasks='" + tasks +
                        ", notes='" + notes + '\'' +
                        ", technologies='" + technologies +
                        ", bugs='" + bugs +
                        '}';
        }
}
