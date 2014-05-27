package org.example.issuetracker.model.utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.example.issuetracker.model.dao.jdbc.connections.ConnectionManager;

public class DbUtils {
	public static void createDatabaseIfNotExist() {
		int exist = -1;
		try (Connection cn = ConnectionManager.getConnection();
				Statement st = cn.createStatement();
				ResultSet rs = st
						.executeQuery("SELECT count(*) AS exist FROM information_schema.tables WHERE table_name = 'ISSUES'")) {
			if (rs.next()) {
				exist = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (exist == 0) {
			createDb();
		}
	}

	private static void createDb() {
		try (Connection cn = ConnectionManager.getConnection(); Statement st = cn.createStatement()) {
			st.addBatch("create table users (id bigint auto_increment primary key , emailaddress varchar(255) not null unique , firstname varchar(255) not null, lastname varchar(255) not null, password varchar(255) not null, userrole bigint not null)");
			st.addBatch("create table roles (id  int auto_increment primary key , role varchar(255)  not null)");
			st.addBatch("insert into users (emailaddress, firstname, lastname, password, userrole) values('admin@example.org', 'admin', 'admin', 'admin', 1)");
			st.addBatch("insert into users (emailaddress, firstname, lastname, password, userrole) values('user@example.org', 'user', 'user', 'user', 2)");
			st.addBatch("insert into roles (role) values ('ADMINISTRATOR')");
			st.addBatch("insert into roles (role) values ('USER')");
			st.addBatch("insert into roles (role) values ('GUEST')");
			st.addBatch("create table builds (id  bigint auto_increment primary key , name varchar(255)  not null, projectid bigint not null )");
			st.addBatch("create table projects (id  bigint auto_increment primary key , name varchar(255)  not null, description varchar(255) not null ,manager  bigint not null )");
			st.addBatch("insert into builds (name, projectid) values ('build1 of project1', 1)");
			st.addBatch("insert into builds (name, projectid) values ('build2 of project1', 1)");
			st.addBatch("insert into builds (name, projectid) values ('build1 of project2', 2)");
			st.addBatch("insert into builds (name, projectid) values ('build2 of project2', 2)");
			st.addBatch("insert into projects (name, description, manager) values ('project1', 'desc of pr1', 1)");
			st.addBatch("insert into projects (name, description, manager) values ('project2', 'desc of pr2', 1)");
			st.addBatch("create table priorities (id  int auto_increment primary key , name varchar(255)  not null)");
			st.addBatch("insert into priorities (name) values ('critical')");
			st.addBatch("insert into priorities (name) values ('major')");
			st.addBatch("insert into priorities (name) values ('important')");
			st.addBatch("insert into priorities (name) values ('minor')");
			st.addBatch("create table resolutions (id  int auto_increment primary key , name varchar(255)  not null)");
			st.addBatch("insert into resolutions (name) values ('fixed')");
			st.addBatch("insert into resolutions (name) values ('invalid')");
			st.addBatch("insert into resolutions (name) values ('wontfix')");
			st.addBatch("insert into resolutions (name) values ('worksforme')");
			st.addBatch("create table statuses (id  int auto_increment primary key , name varchar(255)  not null)");
			st.addBatch("insert into statuses (name) values ('new')");
			st.addBatch("insert into statuses (name) values ('assigned')");
			st.addBatch("insert into statuses (name) values ('inprogress')");
			st.addBatch("insert into statuses (name) values ('resolved')");
			st.addBatch("insert into statuses (name) values ('closed')");
			st.addBatch("insert into statuses (name) values ('reopened')");
			st.addBatch("create table types (id  int auto_increment primary key , name varchar(255)  not null)");
			st.addBatch("insert into types (name) values ('cosmetic')");
			st.addBatch("insert into types (name) values ('bug')");
			st.addBatch("insert into types (name) values ('feature')");
			st.addBatch("insert into types (name) values ('performance')");
			st.addBatch("create table issues (id  bigint auto_increment primary key ,  CREATEDATe date  not null, createdby bigint not null ,modifydate date , modifiedby bigint ,summary varchar(255) not null ,  description varchar(255) not null ,status int  not null , resolution int , type int not null, priority int not null, project bigint not null, buildfound bigint not null, assignee bigint )");
			st.executeBatch();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
