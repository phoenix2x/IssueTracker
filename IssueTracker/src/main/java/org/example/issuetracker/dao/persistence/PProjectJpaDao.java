package org.example.issuetracker.dao.persistence;

import java.util.List;

import javax.persistence.Query;

import org.example.issuetracker.constants.Constants;
import org.example.issuetracker.dao.IProjectDao;
import org.example.issuetracker.domain.Build;
import org.example.issuetracker.domain.Issue;
import org.example.issuetracker.domain.PaginationParams;
import org.example.issuetracker.domain.Project;
import org.example.issuetracker.domain.User;
import org.springframework.stereotype.Repository;

@Repository
public class PProjectJpaDao extends AbstractJpaDAO<Project> implements IProjectDao {

	public PProjectJpaDao() {
		super(Project.class);
	}

	@Override
	public List<Build> getBuildsByProjectId(long projectId) {
//		Query query = entityManager.createNativeQuery("SELECT id, name FROM Builds WHERE projectid=:projectId");
//		query.setParameter("projectId", projectId);
//		return query.getResultList();
		return entityManager.find(Project.class, projectId).getBuilds();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Project> getProjectsList(Integer count) {
		Query query = entityManager.createQuery("FROM Project");
		if (count != null) {
			query.setMaxResults(count);
		}
		return query.getResultList();
	}
//	@Override
//	public void createBuilds(long projectId, List<Build> builds) {
//		Query query = entityManager.createNativeQuery("INSERT INTO builds (name , projectid) values (:name, :projectid)");
//		for (Build build: builds) {
//			query.setParameter("name", build.getName());
//			query.setParameter("projectid", projectId);
//			query.executeUpdate();
//		}
//	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Project> getSortedProjectsList(PaginationParams params) {
		String stringQuery = "SELECT p.id, p.name, m.emailAddress, p.description FROM Project p LEFT JOIN p.manager m";
		stringQuery += " ORDER BY p." + params.getStringOrderBy() + " " + params.getOrder();
		Query query = entityManager.createQuery(stringQuery);
		query.setFirstResult(params.getPage() * Constants.NUMBER_PROJECTS);
		query.setMaxResults(Constants.NUMBER_PROJECTS);
		return query.getResultList();
	}
	
	@Override
	public long getProjectsCount() {
		Query query = entityManager.createQuery("SELECT count(*) FROM Project");
		return (long) query.getSingleResult(); 
	}

	/* (non-Javadoc)
	 * @see org.example.issuetracker.dao.persistence.AbstractJpaDAO#create(org.example.issuetracker.domain.GenericDomainObject)
	 */
//	@Override
//	public void create(GenericDomainObject entity) {
////		Project project = (Project) entity;
//		entityManager.persist(entity);
////		createBuilds(project.getBuilds(), project.getId());
//	}
//
//	/* (non-Javadoc)
//	 * @see org.example.issuetracker.dao.persistence.AbstractJpaDAO#update(org.example.issuetracker.domain.GenericDomainObject)
//	 */
//	@Override
//	public Project update(Project entity) {
//		Project originProject = entityManager.find(Project.class, entity.getId());
//		originProject.copyNonNullFields(entity);
//		createBuilds(entity.getBuilds(), entity.getId());
//		return originProject;
//	}
//	private void createBuilds(List<Build> builds, long projectId) {
//		Query query = entityManager.createNativeQuery("INSERT INTO builds (name , projectid) values (:name, :projectid)");
//		for (Build build: builds) {
//			query.setParameter("name", build.getName());
//			query.setParameter("projectid", projectId);
//			query.executeUpdate();
//		}
//	}
	
	
}
