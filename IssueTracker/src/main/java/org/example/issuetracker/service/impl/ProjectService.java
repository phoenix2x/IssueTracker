package org.example.issuetracker.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.apache.commons.logging.Log;
import org.apache.log4j.Logger;
import org.example.issuetracker.constants.Constants;
import org.example.issuetracker.dao.IDao;
import org.example.issuetracker.dao.IProjectDao;
import org.example.issuetracker.domain.Build;
import org.example.issuetracker.domain.Issue;
import org.example.issuetracker.domain.PaginationParams;
import org.example.issuetracker.domain.Project;
import org.example.issuetracker.domain.User;
import org.example.issuetracker.service.IProjectService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProjectService extends AbstractGenericService<Project> implements IProjectService {
	private static final Logger LOG = Logger.getLogger(ProjectService.class);
	
	@Inject
	private IProjectDao projectDao;
	
	@Override
	protected IDao<Project> getDao() {
		return projectDao;
	}
	

	@Override
	@Transactional(readOnly=true)
	public List<Build> getBuildsByProjectId(long projectId) {
		return projectDao.getBuildsByProjectId(projectId);
	}
	
		
//	/* (non-Javadoc)
//	 * @see org.example.issuetracker.service.impl.AbstractGenericService#update(org.example.issuetracker.domain.GenericDomainObject)
//	 */
//	@Override
//	@Transactional
//	public Project update(Project newProject) {
//		Project project = projectDao.getById(newProject.getId());
//		copyProjectFields(newProject, project);
//		return project;
//	}
	@Override
	@Transactional(readOnly=true)
	public List<Project> getProjectsList() {
		return projectDao.getProjectsList(Constants.NUMBER_PROJECTS);
	}

	@Override
	@Transactional
	public void createBuild(Build build, Long projectId) {
		LOG.debug("Creating build: " + build);
		Project project = projectDao.getById(projectId);
		project.getBuilds().add(build);
		projectDao.createProperty(build);
		LOG.debug("after savin" + build);
	}


	@Override
	@Transactional(readOnly=true)
	public List<Project> getSortedProjectsList(PaginationParams params) {
		return projectDao.getSortedProjectsList(params);
	}
	
	@Override
	@Transactional(readOnly=true)
	public Integer getProjectsPages() {
		return (int) Math.ceil((double) projectDao.getProjectsCount() / Constants.NUMBER_PROJECTS); 
	}
	
//	private Project copyProjectFields(Project newProject, Project project) {
//		project.setDescription(newProject.getDescription());
//		project.setManager(newProject.getManager());
//		project.setName(newProject.getName());
//		return project;
//	}
}
