package com.epam.issuetracker.analyzer.sax;

import java.util.HashMap;
import java.util.Map;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.epam.issuetracker.beans.Issue;

public class IssuesHandler extends DefaultHandler {
	private static enum ElementEnum {
		ISSUES, ISSUE, CREATE, CREATED, SUMMARY, DESCRIPTION, STATUS, TYPE, PRIORITY, PROJECT, ASSIGNEE, BUILD
	}

	private static final int ID_INDEX = 0;
	private final Map<Long, Issue> issues;
	private ElementEnum currentElementEnum;
	private Issue currentIssue;

	/**
	 * 
	 */
	public IssuesHandler() {
		this.issues = new HashMap<>();
	}

	/**
	 * @return the issues
	 */
	public Map<Long, Issue> getIssues() {
		return issues;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.xml.sax.helpers.DefaultHandler#startElement(java.lang.String,
	 * java.lang.String, java.lang.String, org.xml.sax.Attributes)
	 */
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		currentElementEnum = ElementEnum.valueOf(localName.toUpperCase());
		if (ElementEnum.ISSUE.equals(currentElementEnum)) {
			long id = Long.valueOf(attributes.getValue(ID_INDEX));
			currentIssue = new Issue(id);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.xml.sax.helpers.DefaultHandler#characters(char[], int, int)
	 */
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		if (currentElementEnum != null) {
			switch (currentElementEnum) {
			case CREATE:
				currentIssue.setCreateDate(new String(ch, start, length).trim());
				break;
			case CREATED:
				currentIssue.setCreatedBy(new String(ch, start, length).trim());
				break;
			case SUMMARY:
				currentIssue.setSummary(new String(ch, start, length).trim());
				break;
			case DESCRIPTION:
				currentIssue.setDescription(new String(ch, start, length).trim());
				break;
			case STATUS:
				currentIssue.setStatus(new String(ch, start, length).trim());
				break;
			case TYPE:
				currentIssue.setType(new String(ch, start, length).trim());
				break;
			case PRIORITY:
				currentIssue.setPriority(new String(ch, start, length).trim());
				break;
			case PROJECT:
				currentIssue.setProject(new String(ch, start, length).trim());
				break;
			case ASSIGNEE:
				currentIssue.setAssignee(new String(ch, start, length).trim());
				break;
			case BUILD:
				currentIssue.setBuildFound(new String(ch, start, length).trim());
				issues.put(currentIssue.getId(), currentIssue);
				currentIssue = null;
				break;
			default:
				break;
			}
		}
		currentElementEnum = null;
	}
}
