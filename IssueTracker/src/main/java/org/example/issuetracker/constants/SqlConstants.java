package org.example.issuetracker.constants;

public class SqlConstants {
	public static final String SELECT_USER_BY_LOGIN = "SELECT users.id AS id, emailaddress, firstname, lastname, password, role AS userrole "
			+ "FROM users "
			+ "INNER JOIN roles ON users.userrole=roles.id"
			+ " WHERE emailaddress=?";
	public static final int SELECT_USER_EMAIL_INDEX = 1;
	public static final int SELECT_USER_RET_ID = 1;
	public static final int SELECT_USER_RET_EMAIL = 2;
	public static final int SELECT_USER_RET_FIRST_NAME = 3;
	public static final int SELECT_USER_RET_LAST_NAME = 4;
	public static final int SELECT_USER_RET_PASSWORD = 5;
	public static final int SELECT_USER_RET_ROLE = 6;
	public static final String SELECT_USER_BY_ID = "SELECT users.id AS id, emailaddress, firstname, lastname, password, role AS userrole "
			+ "FROM users "
			+ "INNER JOIN roles ON users.userrole=roles.id"
			+ " WHERE users.id=?";
	public static final int SELECT_USER_BY_ID_INDEX = 1;
	public static final String SELECT_PROJECT_BY_ID = "SELECT id, name, description, manager "
			+ "FROM projects "
			+ "WHERE id=?";
	public static final int SELECT_PROJECT_BY_ID_INDEX = 1;
	public static final int SELECT_PROJECT_RET_ID_INDEX = 1;
	public static final int SELECT_PROJECT_RET_NAME_INDEX = 2;
	public static final int SELECT_PROJECT_RET_DESCRIPTION_INDEX = 3;
	public static final int SELECT_PROJECT_RET_MANAGER_INDEX = 4;
	public static final String SELECT_BUILD_BY_PROJECT_ID = "SELECT name FROM builds WHERE projectid=?";
	public static final int SELECT_BUILDS_BY_PROJECT_ID_INDEX = 1;
	public static final int SELECT_BUILDS_RET_NAME_INDEX = 1;
//	public static final String SELECT_ISSUE_BY_ASSIGNEE_ID = "SELECT TOP ? ISSUES.ID, CREATEDATE, cr.id AS crid, cr.emailaddress as cremail, cr.firstname as crfirstname, cr.lastname as crlastname, cr.password as crpassword, cr.role AS crrole, MODIFYDATE, md.id AS mdid, md.emailaddress as mdemail, md.firstname as mdfirstname, md.lastname as mdlastname, md.password as mdpassword, md.role AS mdrole, SUMMARY, issues.DESCRIPTION, statuses.name as STATUS, resolutions.name as RESOLUTION, types.name as TYPE, priorities.name as PRIORITY, pr.id AS prid, pr.name AS prname, pr.description AS prdesc, pr.mid AS prmid, pr.memail AS prmemail, pr.mfirstname AS prmfirstname, pr.mlastname AS prmlastname, pr.mpassword AS prmpassword, pr.mrole AS prmrole, builds.name as BUILDFOUND, as.id AS asid, as.emailaddress as asemail, as.firstname as asfirstname, as.lastname as aslastname, as.password as aspassword, as.role AS asrole FROM ISSUES inner join statuses on issues.status=statuses.id inner join types on issues.type=types.id inner join priorities on issues.priority=priorities.id inner join builds on issues.buildfound=builds.id inner join (SELECT projects.id, projects.name, description, users.id AS mid ,users.emailaddress AS memail, users.firstname AS mfirstname, users.lastname AS mlastname, users.password AS mpassword, roles.role AS mrole FROM projects INNER JOIN users ON users.id=projects.manager INNER JOIN roles ON users.userrole=roles.id) AS pr on issues.project=pr.id inner join (SELECT users.id, emailaddress, firstname, lastname, password, roles.role as role FROM users inner join roles ON users.userrole=roles.id) AS cr on issues.createdby=cr.id left join (SELECT users.id, emailaddress, firstname, lastname, password, roles.role as role FROM users inner join roles ON users.userrole=roles.id) AS md on issues.modifiedby=md.id left join (SELECT users.id, emailaddress, firstname, lastname, password, roles.role as role FROM users inner join roles ON users.userrole=roles.id) AS as on issues.modifiedby=as.id left join resolutions on issues.resolution=resolutions.id WHERE issues.assignee=?";
//	public static final String SELECT_ISSUE_BY_ASSIGNEE_ID = "SELECT TOP ? ISSUES.ID, CREATEDATE, createdby, MODIFYDATE, modifiedby, SUMMARY, issues.DESCRIPTION, statuses.name as STATUS, resolutions.name as RESOLUTION, types.name as TYPE, priorities.name as PRIORITY, pr.id AS prid, pr.name AS prname, pr.description AS prdesc, pr.manager, builds.name as BUILDFOUND, assignee "
//			+ "FROM ISSUES "
//			+ "inner join statuses on issues.status=statuses.id "
//			+ "inner join types on issues.type=types.id "
//			+ "inner join priorities on issues.priority=priorities.id "
//			+ "inner join builds on issues.buildfound=builds.id "
//			+ "inner join projects AS pr on issues.project=pr.id "
//			+ "left join resolutions on issues.resolution=resolutions.id "
//			+ "WHERE issues.assignee=?";
	public static final String SELECT_ISSUE_BY_ASSIGNEE_ID = "SELECT TOP ? ISSUES.ID, CREATEDATE, createdby, MODIFYDATE, modifiedby, SUMMARY, issues.DESCRIPTION, statuses.name as STATUS, resolutions.name as RESOLUTION, types.name as TYPE, priorities.name as PRIORITY, project, builds.name as BUILDFOUND, assignee "
			+ "FROM ISSUES "
			+ "inner join statuses on issues.status=statuses.id "
			+ "inner join types on issues.type=types.id "
			+ "inner join priorities on issues.priority=priorities.id "
			+ "inner join builds on issues.buildfound=builds.id "
			+ "left join resolutions on issues.resolution=resolutions.id "
			+ "WHERE issues.assignee=?";
	public static final int SELECT_ISSUE_BY_ASSIGNEE_N_INDEX = 1;
	public static final int SELECT_ISSUE_BY_ASSIGNEE_ID_INDEX = 2;
	public static final int SELECT_ISSUE_RET_ID_INDEX = 1;
	public static final int SELECT_ISSUE_RET_CREATEDATE_INDEX = 2;
	public static final int SELECT_ISSUE_RET_CREATEDBY_INDEX = 3;
	public static final int SELECT_ISSUE_RET_MODIFYDATE_INDEX = 4;
	public static final int SELECT_ISSUE_RET_MODIFIEDBY_INDEX = 5;
	public static final int SELECT_ISSUE_RET_SUMMARY_INDEX = 6;
	public static final int SELECT_ISSUE_RET_DESCRIPTION_INDEX = 7;
	public static final int SELECT_ISSUE_RET_STATUS_INDEX = 8;
	public static final int SELECT_ISSUE_RET_RESOLUTION_INDEX = 9;
	public static final int SELECT_ISSUE_RET_TYPE_INDEX = 10;
	public static final int SELECT_ISSUE_RET_PRIORITY_INDEX = 11;
	public static final int SELECT_ISSUE_RET_PROJECT_INDEX = 12;
	public static final int SELECT_ISSUE_RET_BUILDFOUND_INDEX = 13;
	public static final int SELECT_ISSUE_RET_ASSIGNEE_INDEX = 14;
	public static final String SELECT_LAST_ISSUES = "SELECT TOP ? ISSUES.ID, CREATEDATE, createdby, MODIFYDATE, modifiedby, SUMMARY, issues.DESCRIPTION, statuses.name as STATUS, resolutions.name as RESOLUTION, types.name as TYPE, priorities.name as PRIORITY, project, builds.name as BUILDFOUND, assignee "
			+ "FROM ISSUES "
			+ "inner join statuses on issues.status=statuses.id "
			+ "inner join types on issues.type=types.id "
			+ "inner join priorities on issues.priority=priorities.id "
			+ "inner join builds on issues.buildfound=builds.id "
			+ "left join resolutions on issues.resolution=resolutions.id "
			+ "ORDER BY createdate";
	public static final int SELECT_LAST_ISSUES_N_INDEX = 1;
//	public static final int SELECT_ISSUE_RET_ID_INDEX = 1;
//	public static final int SELECT_ISSUE_RET_CREATEDATE_INDEX = 2;
//	public static final int SELECT_ISSUE_RET_CREATEDBY_INDEX = 3;
//	public static final int SELECT_ISSUE_RET_MODIFYDATE_INDEX = 4;
//	public static final int SELECT_ISSUE_RET_MODIFIEDBY_INDEX = 5;
//	public static final int SELECT_ISSUE_RET_SUMMARY_INDEX = 6;
//	public static final int SELECT_ISSUE_RET_DESCRIPTION_INDEX = 7;
//	public static final int SELECT_ISSUE_RET_STATUS_INDEX = 8;
//	public static final int SELECT_ISSUE_RET_RESOLUTION_INDEX = 9;
//	public static final int SELECT_ISSUE_RET_TYPE_INDEX = 10;
//	public static final int SELECT_ISSUE_RET_PRIORITY_INDEX = 11;
//	public static final int SELECT_ISSUE_RET_PRID_INDEX = 12;
//	public static final int SELECT_ISSUE_RET_PRNAME_INDEX = 13;
//	public static final int SELECT_ISSUE_RET_PRDESC_INDEX = 14;
//	public static final int SELECT_ISSUE_RET_PRMANAGER_INDEX = 15;
//	public static final int SELECT_ISSUE_RET_BUILDFOUND_INDEX = 16;
//	public static final int SELECT_ISSUE_RET_ASSIGNEE_INDEX = 17;
//	public static final int SELECT_ISSUE_RET_ID_INDEX = 1;
//	public static final int SELECT_ISSUE_RET_CREATEDATE_INDEX = 2;
//	public static final int SELECT_ISSUE_RET_CRID_INDEX = 3;
//	public static final int SELECT_ISSUE_RET_CREMAIL_INDEX = 4;
//	public static final int SELECT_ISSUE_RET_CRFIRSTNAME_INDEX = 5;
//	public static final int SELECT_ISSUE_RET_CRLASTNAME_INDEX = 6;
//	public static final int SELECT_ISSUE_RET_CRPASSWORD_INDEX = 7;
//	public static final int SELECT_ISSUE_RET_CRROLE_INDEX = 8;
//	public static final int SELECT_ISSUE_RET_MODIFYDATE_INDEX = 9;
//	public static final int SELECT_ISSUE_RET_MDID_INDEX = 10;
//	public static final int SELECT_ISSUE_RET_MDEMAIL_INDEX = 11;
//	public static final int SELECT_ISSUE_RET_MDFIRSTNAME_INDEX = 12;
//	public static final int SELECT_ISSUE_RET_MDLASTNAME_INDEX = 13;
//	public static final int SELECT_ISSUE_RET_MDPASSWORD_INDEX = 14;
//	public static final int SELECT_ISSUE_RET_MDROLE_INDEX = 15;
//	public static final int SELECT_ISSUE_RET_SUMMARY_INDEX = 16;
//	public static final int SELECT_ISSUE_RET_DESCRIPTION_INDEX = 17;
//	public static final int SELECT_ISSUE_RET_STATUS_INDEX = 18;
//	public static final int SELECT_ISSUE_RET_RESOLUTION_INDEX = 19;
//	public static final int SELECT_ISSUE_RET_TYPE_INDEX = 20;
//	public static final int SELECT_ISSUE_RET_PRIORITY_INDEX = 21;
//	public static final int SELECT_ISSUE_RET_PRID_INDEX = 22;
//	public static final int SELECT_ISSUE_RET_PRNAME_INDEX = 23;
//	public static final int SELECT_ISSUE_RET_PRDESC_INDEX = 24;
//	public static final int SELECT_ISSUE_RET_PRMID_INDEX = 25;
//	public static final int SELECT_ISSUE_RET_PRMEMAIL_INDEX = 26;
//	public static final int SELECT_ISSUE_RET_PRMFIRSTNAME_INDEX = 27;
//	public static final int SELECT_ISSUE_RET_PRMLASTNAME_INDEX = 28;
//	public static final int SELECT_ISSUE_RET_PRMPASSWORD_INDEX = 29;
//	public static final int SELECT_ISSUE_RET_PRMROLE_INDEX = 30;
//	public static final int SELECT_ISSUE_RET_BUILDFOUND_INDEX = 31;
//	public static final int SELECT_ISSUE_RET_ASID_INDEX = 32;
//	public static final int SELECT_ISSUE_RET_ASEMAIL_INDEX = 33;
//	public static final int SELECT_ISSUE_RET_ASFIRSTNAME_INDEX = 34;
//	public static final int SELECT_ISSUE_RET_ASLASTNAME_INDEX = 35;
//	public static final int SELECT_ISSUE_RET_ASPASSWORD_INDEX = 36;
//	public static final int SELECT_ISSUE_RET_ASROLE_INDEX = 37;
	public static final String SELECT_ALL_PROPERTIES = "SELECT name FROM ?";
	public static final int SELECT_ALL_PROPERTIES_TABLE_NAME_INDEX = 1;
	public static final int SELECT_ALL_PROPERTIES_RET_NAME_INDEX = 1;
}
