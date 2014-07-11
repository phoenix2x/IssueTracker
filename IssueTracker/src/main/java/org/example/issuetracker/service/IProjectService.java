package org.example.issuetracker.service;

import java.util.List;

import org.example.issuetracker.domain.Build;
import org.example.issuetracker.domain.PaginationParams;
import org.example.issuetracker.domain.Project;
import org.example.issuetracker.domain.User;

public interface IProjectService extends IGenericService<Project> {

	List<Build> getBuildsByProjectId(long projectId);

	void createBuild(Build build, Long projectId);
	
	List<Project> getSortedProjectsList(PaginationParams params);

	Integer getProjectsPages();

	List<Project> getProjectsList();

}
