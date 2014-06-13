package org.example.issuetracker.dao.persistence;

import java.util.List;

import javax.persistence.Query;

import org.example.issuetracker.dao.IProjectDao;
import org.example.issuetracker.domain.Build;
import org.example.issuetracker.domain.Project;
import org.springframework.stereotype.Repository;

@Repository
public class PProjectJpaDao extends AbstractJpaDAO<Project> implements IProjectDao {

	public PProjectJpaDao() {
		super(Project.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Build> getBuildsByProjectId(long projectId) {
		Query query = entityManager.createNativeQuery("SELECT id, name FROM Builds WHERE projectid=:projectId");
		query.setParameter("projectId", projectId);
		return query.getResultList();
	}
	@Override
	public void createBuilds(long projectId, List<Build> builds) {
		Query query = entityManager.createNativeQuery("INSERT INTO builds (name , projectid) values (:name, :projectid)");
		for (Build build: builds) {
			query.setParameter("name", build.getName());
			query.setParameter("projectid", projectId);
			query.executeUpdate();
		}
	}
}
