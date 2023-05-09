package com.deathlysamurai.projecttrackerapi.model;

public class BugAdd {
    Bug bug;
    Long projectId;

    public BugAdd() {};

    public BugAdd(Bug bug, Long projectId) {
        this.bug = bug;
        this.projectId = projectId;
    }

    public Bug getBug() {
        return bug;
    }

    public void setBug(Bug bug) {
        this.bug = bug;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }
}
