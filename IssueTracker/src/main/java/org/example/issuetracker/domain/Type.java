package org.example.issuetracker.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.codehaus.jackson.annotate.JsonValue;

@Entity
@Table(name = "types")
public class Type extends GenericDomainObject {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		
		@Column(name = "NAME")
		@Size(min = 1, message = "Name required")
		private String name;

		/**
		 * @return the name
		 */
		@JsonValue
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
