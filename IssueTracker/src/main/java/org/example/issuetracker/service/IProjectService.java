package org.example.issuetracker.service;

import java.util.List;

import org.example.issuetracker.domain.Build;
import org.example.issuetracker.domain.Project;

public interface IProjectService extends IGenericService<Project> {

	List<Build> getBuildsByProjectId(long projectId);

}
