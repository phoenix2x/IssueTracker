package org.example.issuetracker.service;

import java.util.List;

import org.example.issuetracker.domain.Build;
import org.example.issuetracker.domain.Project;

public interface IProjectService extends IGenericService<Project> {

	void createProject(Project entity);

	List<Build> getBuildsByProjectId(long projectId);

}
