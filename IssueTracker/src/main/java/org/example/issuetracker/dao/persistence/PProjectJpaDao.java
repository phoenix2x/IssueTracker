package org.example.issuetracker.dao.persistence;

import java.util.List;
import org.example.issuetracker.dao.IProjectDao;
import org.example.issuetracker.domain.Build;
import org.example.issuetracker.domain.Project;
import org.example.issuetracker.model.exceptions.DAOException;

public class PProjectJpaDao extends AbstractJpaDAO<Project> implements IProjectDao {

	public PProjectJpaDao() {
		super(Project.class);
	}

	@Override
	public List<Build> getBuildsByProjectId(long projectId) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}
}
