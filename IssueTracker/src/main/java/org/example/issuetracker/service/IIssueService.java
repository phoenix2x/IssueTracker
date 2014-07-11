package org.example.issuetracker.service;

import java.util.List;

import org.example.issuetracker.domain.Attachment;
import org.example.issuetracker.domain.GenericDomainObject;
import org.example.issuetracker.domain.Issue;
import org.example.issuetracker.domain.PaginationParams;
import org.example.issuetracker.domain.SearchIssue;
import org.example.issuetracker.domain.Status;
import org.example.issuetracker.domain.User;
import org.springframework.web.multipart.MultipartFile;

public interface IIssueService extends IGenericService<Issue>{
	
	List<Issue> getIssuesList(User user);

	List<Status> getNewStatuses();

	List<Issue> getSortedIssuesList(User user, PaginationParams params);

	Integer getIssuesPages(User user);
	
	void createAttachment(Attachment attachment, MultipartFile file);

	Issue updateWithNotification(Issue entity);

	void createWithNotification(Issue entity);

	List<Issue> getFoundIssuesList(SearchIssue issue);

}
