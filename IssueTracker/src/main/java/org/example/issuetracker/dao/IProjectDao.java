package org.example.issuetracker.dao;

import java.util.List;

import org.example.issuetracker.domain.Build;
import org.example.issuetracker.domain.Project;
import org.example.issuetracker.model.exceptions.DAOException;

public interface IProjectDao extends IDao<Project>{
	List<Build> getBuildsByProjectId(long projectId) throws DAOException;
}
