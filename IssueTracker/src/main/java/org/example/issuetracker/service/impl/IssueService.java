package org.example.issuetracker.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.example.issuetracker.constants.Constants;
import org.example.issuetracker.dao.IDao;
import org.example.issuetracker.dao.IIssueDao;
import org.example.issuetracker.dao.IUserDao;
import org.example.issuetracker.domain.Attachment;
import org.example.issuetracker.domain.GenericDomainObject;
import org.example.issuetracker.domain.Issue;
import org.example.issuetracker.domain.Notification;
import org.example.issuetracker.domain.PaginationParams;
import org.example.issuetracker.domain.SearchIssue;
import org.example.issuetracker.domain.Status;
import org.example.issuetracker.domain.User;
import org.example.issuetracker.service.IIssueService;
import org.example.issuetracker.service.INotificationSender;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
public class IssueService extends AbstractGenericService<Issue> implements IIssueService{
	
	private static final Logger LOG = Logger.getLogger(IssueService.class);
	
	@Inject
	private IIssueDao issueDao;
	
	@Inject
	private IUserDao userDao;
	
	@Inject
	private INotificationSender myMailSender;
	
	/**
	 * @param abstractJpaDAOtoSet
	 */
	private IssueService() {
		super();
	}

	@Override
	protected IDao<Issue> getDao() {
		return issueDao;
	}
	
	
	
	/* (non-Javadoc)
	 * @see org.example.issuetracker.service.impl.AbstractGenericService#update(org.example.issuetracker.domain.GenericDomainObject)
	 */
	@Override
	@Transactional
	public Issue updateWithNotification(Issue entity) {
		Issue issue = update(entity); 
		try {
			if (entity.getAssignee() != null) {
				User assignee = userDao.getById(entity.getAssignee().getId());
				myMailSender.sendNotification(new Notification(assignee.getEmailAddress(), entity.getId() + "updated"));
			}
			if (entity.getCreatedBy() != null) {
				myMailSender.sendNotification(new Notification(entity.getCreatedBy().getEmailAddress(), entity.getId() + "updated"));
			}
		} catch (MailException e) {
			LOG.debug("notify on update fail", e);
		}
		return issue;
	}
	
	
	
	/* (non-Javadoc)
	 * @see org.example.issuetracker.service.impl.AbstractGenericService#create(org.example.issuetracker.domain.GenericDomainObject)
	 */
	@Override
	@Transactional
	public void createWithNotification(Issue entity) {
		create(entity);
		try {
			if (entity.getAssignee() != null) {
				User assignee = userDao.getById(entity.getAssignee().getId());
				myMailSender.sendNotification(new Notification(assignee.getEmailAddress(), entity.getId() + " created"));
			}
			if (entity.getCreatedBy() != null) {
				myMailSender.sendNotification(new Notification(entity.getCreatedBy().getEmailAddress(), entity.getId() + " created"));
			}
		} catch (MailException e) {
			LOG.debug("notify on create fail", e);
		}
	}

	@Override
	@Transactional(readOnly=true)
	public List<Issue> getIssuesList(User user) {
		if (user == null) {
			return issueDao.getLastIssues(Constants.NUMBER_ISSUES);
		} else {
			return issueDao.getIssuesByUserId(user, Constants.NUMBER_ISSUES);
		}
	}
	@Override
	@Transactional(readOnly=true)
	public Integer getIssuesPages(User user) {
		return (int) Math.ceil((double) issueDao.getIssuesCount(user) / Constants.NUMBER_ISSUES); 
	}
	
	@Override
	@Transactional(readOnly=true)
	public List<Issue> getSortedIssuesList(User user, PaginationParams params) {
		return issueDao.getSortedIssuesList(user, params);
	}
	
	@Override
	@Transactional(readOnly=true)
	public List<Status> getNewStatuses() {
		return issueDao.getProperties(Status.class).subList(0, 2);
	}

	@Override
	@Transactional
	public void createAttachment(Attachment attachment, MultipartFile file) {
		issueDao.createProperty(attachment);
		System.out.println(attachment.getId());
		File newFile = new File(attachment.getFilePath() + attachment.getId());
		try {
			FileUtils.writeByteArrayToFile(newFile, file.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	@Transactional(readOnly=true)
	public List<Issue> getFoundIssuesList(SearchIssue issue) {
		return issueDao.getFoundIssuesList(issue);
	}
}
