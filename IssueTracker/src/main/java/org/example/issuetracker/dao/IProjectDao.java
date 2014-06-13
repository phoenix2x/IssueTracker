package org.example.issuetracker.dao;

import java.util.List;

import org.example.issuetracker.domain.Build;
import org.example.issuetracker.domain.Project;

public interface IProjectDao extends IDao<Project>{
	List<Build> getBuildsByProjectId(long projectId);

	void createBuilds(long projectId, List<Build> builds);
}
