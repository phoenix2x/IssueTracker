package org.example.issuetracker.domain;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.apache.log4j.Logger;

@MappedSuperclass
public class GenericDomainObject implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column
	@GeneratedValue
	private long id;

	/**
	 * 
	 */
	public GenericDomainObject() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param id
	 */
	public GenericDomainObject(long id) {
		super();
		this.id = id;
	}

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}
	
	@SuppressWarnings("unchecked")
	public void copyNonNullFields(GenericDomainObject obj) {
		if ((obj != null) && (this.getClass().isAssignableFrom(obj.getClass()))) {

			try {
				Map<String, Object> props = PropertyUtils.describe(obj);

				for (Entry<String, Object> entry : props.entrySet()) {
					if (PropertyUtils.isWriteable(this, entry.getKey())) {
						Class<?> clazz = PropertyUtils.getPropertyType(obj,
								entry.getKey());
						if ((!Collection.class.isAssignableFrom(clazz))
								&& (!clazz.isAssignableFrom(Class.class))
								&& (!entry.getKey().equals("id"))
								&& (entry.getValue() != null)) {
							PropertyUtils.setProperty(this, entry.getKey(),
									entry.getValue());
						}
					}
				}

			} catch (Exception e) {
				Logger log = Logger.getLogger(getClass());
				log.error("DomainObject.ErrorCopyFieldValues " + this.toString() + obj.toString(), e);
			}

		}
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GenericDomainObject other = (GenericDomainObject) obj;
		if (id != other.id)
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return new ReflectionToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE).toString();

	}
	
}
