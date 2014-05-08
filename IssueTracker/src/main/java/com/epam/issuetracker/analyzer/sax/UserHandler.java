package com.epam.issuetracker.analyzer.sax;

import java.util.HashMap;
import java.util.Map;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.epam.issuetracker.beans.User;
import com.epam.issuetracker.constants.Constants;

public class UserHandler extends DefaultHandler {
	private enum AttributesList {
		ID, NAME, PASSWORD, ROLE
	}

	private final Map<String, User> users;

	public UserHandler() {
		super();
		this.users = new HashMap<>();
	}

	public Map<String, User> getUsers() {
		return users;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.xml.sax.helpers.DefaultHandler#startElement(java.lang.String,
	 * java.lang.String, java.lang.String, org.xml.sax.Attributes)
	 */
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		if (localName.equals(Constants.USER)) {
			long id = 0;
			String name = null;
			String password = null;
			String role = null;
			for (int i = 0; i < attributes.getLength(); i++) {
				AttributesList attributesList = AttributesList.valueOf(attributes.getLocalName(i).toUpperCase());
				switch (attributesList) {
				case ID:
					id = Long.valueOf(attributes.getValue(i));
				case NAME:
					name = attributes.getValue(i);
					break;
				case PASSWORD:
					password = attributes.getValue(i);
					break;
				case ROLE:
					role = attributes.getValue(i);
					break;
				}
			}
			users.put(name, new User(id, name, password, role));
		}
	}
}
