package org.example.issuetracker.dao.persistence;

import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import org.example.issuetracker.constants.Constants;
import org.example.issuetracker.dao.IIssueDao;
import org.example.issuetracker.domain.GenericDomainObject;
import org.example.issuetracker.domain.Issue;
import org.example.issuetracker.domain.IssuePaginationParams;
import org.example.issuetracker.domain.Status;
import org.example.issuetracker.domain.User;
import org.springframework.stereotype.Repository;

@Repository
public class PIssueDao extends AbstractJpaDAO<Issue> implements IIssueDao {

	public PIssueDao() {
		super(Issue.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T extends GenericDomainObject> List<T> getProperties(Class<T> clazz) {
		return entityManager.createQuery("FROM " + clazz.getName()).getResultList();
	}
	
	@Override
	public Map<Integer, Status> getStatuses(int currentStatus) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Status> getNewStatuses() {
		// TODO Auto-generated method stub
		return null;
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<Issue> getLastIssues() {
		Query query = entityManager.createQuery("FROM Issue i ORDER BY i.id");
		query.setMaxResults(Constants.NUMBER_ISSUES);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Issue> getIssuesByUserId(User user) {
		Query query = entityManager.createQuery("FROM Issue i WHERE i.assignee.id = :assigneeId");
		query.setParameter("assigneeId", user.getId());
		query.setMaxResults(Constants.NUMBER_ISSUES);
		return query.getResultList();
	}

	@Override
	public List<String> getProperties(String propName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T extends GenericDomainObject> void createProperty(T entity) {
		entityManager.persist(entity);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Issue> getSortedIssuesList(IssuePaginationParams params) {
		String stringQuery = "SELECT i.id, i.priority, i.assignee, i.type, i.status, i.summary FROM Issue i";
		User user = params.getUser();
		if (user != null) {
			stringQuery += " WHERE i.assignee.id = :assigneeId";
		}
		stringQuery += " ORDER BY i." + params.getOrderBy() + " " + params.getOrder();
		Query query = entityManager.createQuery(stringQuery);
		if (user != null) {
			query.setParameter("assigneeId", user.getId());
		}
		query.setFirstResult(params.getPage() * Constants.NUMBER_ISSUES);
		query.setMaxResults(Constants.NUMBER_ISSUES);
		return query.getResultList();
	}
}
