package org.example.issuetracker.dao.persistence;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import org.example.issuetracker.dao.IIssueDao;
import org.example.issuetracker.domain.Issue;
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
	public <T extends Serializable> List<T> getProperties(Class<T> clazz) {
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


	@Override
	public List<Issue> getLastIssues(int numberIssues, int offset, int orderBy, int order) {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Issue> getIssuesByUserId(User user) {
		Query query = entityManager.createQuery("FROM Issue i WHERE i.assignee.id = :assigneeId");
		query.setParameter("assigneeId", user.getId());
		return query.getResultList();
	}

	@Override
	public List<String> getProperties(String propName) {
		// TODO Auto-generated method stub
		return null;
	}

}
