package org.example.issuetracker.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "resolutions")
public class Resolution extends GenericDomainObject {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		
		@Column(name = "NAME")
		private String name;

		/**
		 * @return the name
		 */
		public String getName() {
			return name;
		}

		/**
		 * @param name the name to set
		 */
		public void setName(String name) {
			this.name = name;
		}

}
