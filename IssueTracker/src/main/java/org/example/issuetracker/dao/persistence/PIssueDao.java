package org.example.issuetracker.dao.persistence;

import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.example.issuetracker.constants.Constants;
import org.example.issuetracker.dao.IIssueDao;
import org.example.issuetracker.domain.GenericDomainObject;
import org.example.issuetracker.domain.Issue;
import org.example.issuetracker.domain.PaginationParams;
import org.example.issuetracker.domain.SearchIssue;
import org.example.issuetracker.domain.Status;
import org.example.issuetracker.domain.User;
import org.example.issuetracker.web.servlets.Issues;
import org.springframework.stereotype.Repository;

@Repository
public class PIssueDao extends AbstractJpaDAO<Issue> implements IIssueDao {
	
	private static final Logger LOG = Logger.getLogger(PIssueDao.class);

	public PIssueDao() {
		super(Issue.class);
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
	public List<Issue> getLastIssues(Integer count) {
		Query query = entityManager.createQuery("FROM Issue i ORDER BY i.id");
		if (count != null) {
			query.setMaxResults(count);
		}
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Issue> getIssuesByUserId(User user, Integer count) {
		Query query = entityManager.createQuery("FROM Issue i WHERE i.assignee.id = :assigneeId");
		query.setParameter("assigneeId", user.getId());
		if (count != null) {
			query.setMaxResults(count);
		}
		return query.getResultList();
	}
	
	@Override
	public long getIssuesCount(User user) {
		Query query;
		if (user == null) {
			query = entityManager.createQuery("SELECT count(*) FROM Issue");
		} else {
			query = entityManager.createQuery("SELECT count(*) FROM Issue WHERE assignee=:assignee");
			query.setParameter("assignee", user);
		}
		return (long) query.getSingleResult(); 
	}

	@Override
	public List<String> getProperties(String propName) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Issue> getSortedIssuesList(User user, PaginationParams params) {
		String stringQuery = "SELECT i.id, i.priority, a.emailAddress, i.type, i.status, i.summary FROM Issue i LEFT JOIN i.assignee a";
		if (user != null) {
			stringQuery += " WHERE i.assignee.id = :assigneeId";
		}
		stringQuery += " ORDER BY i." + params.getStringOrderBy() + " " + params.getOrder();
		Query query = entityManager.createQuery(stringQuery);
		if (user != null) {
			query.setParameter("assigneeId", user.getId());
		}
		query.setFirstResult(params.getPage() * Constants.NUMBER_ISSUES);
		query.setMaxResults(Constants.NUMBER_ISSUES);
		return query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Issue> getFoundIssuesList(SearchIssue issue) {
		LOG.info(issue);
		Query query = entityManager.createQuery(buildQuery(issue));
		return query.getResultList();
	}
	
	private String buildQuery(SearchIssue issue) {
		String query = "FROM Issue i WHERE";
		boolean firstStatement = true; 
		if (issue.getAssigneeEmail() != null) {
			query += " i.assignee.emailaddress=" + issue.getAssigneeEmail();
			firstStatement = false;
		}
		if (issue.getSummary() != null) {
			if (!firstStatement) {
				query += " AND";
			}
			query += " i.summary='" + issue.getSummary() + "'";
			firstStatement = false;
		}
		return query;
	}
}
