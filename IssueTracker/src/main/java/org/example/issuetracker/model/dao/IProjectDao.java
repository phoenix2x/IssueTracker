package org.example.issuetracker.model.dao;

import java.util.List;

import org.example.issuetracker.model.beans.Build;
import org.example.issuetracker.model.beans.Project;
import org.example.issuetracker.model.exceptions.DAOException;

public interface IProjectDao extends IDao<Project> {
	List<Build> getBuildsByProjectId(long projectId) throws DAOException;
}
