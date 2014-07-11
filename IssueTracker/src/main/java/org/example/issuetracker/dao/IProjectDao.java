package org.example.issuetracker.dao;

import java.util.List;

import org.example.issuetracker.domain.Build;
import org.example.issuetracker.domain.Issue;
import org.example.issuetracker.domain.PaginationParams;
import org.example.issuetracker.domain.Project;

public interface IProjectDao extends IDao<Project>{
	List<Build> getBuildsByProjectId(long projectId);
	
	List<Project> getSortedProjectsList(PaginationParams params);

	long getProjectsCount();

	List<Project> getProjectsList(Integer count);
	
//	void createBuilds(long projectId, List<Build> builds);
}
