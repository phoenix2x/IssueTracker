package org.example.issuetracker.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.example.issuetracker.dao.IDao;
import org.example.issuetracker.dao.IProjectDao;
import org.example.issuetracker.domain.Build;
import org.example.issuetracker.domain.Project;
import org.example.issuetracker.service.IProjectService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProjectService extends AbstractGenericService<Project> implements IProjectService {

	
	@Inject
	private IProjectDao projectDao;
	
	@Override
	protected IDao<Project> getDao() {
		return projectDao;
	}
	
	@Override
	@Transactional
	public void create(Project entity) {
		projectDao.create(entity);	
	}

	@Override
	@Transactional
	public List<Build> getBuildsByProjectId(long projectId) {
		return projectDao.getBuildsByProjectId(projectId);
	}
	
	

}
