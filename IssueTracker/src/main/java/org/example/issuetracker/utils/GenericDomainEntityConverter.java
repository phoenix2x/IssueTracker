package org.example.issuetracker.utils;

import java.util.HashSet;
import java.util.Set;

import org.example.issuetracker.dao.persistence.DomainLoaderDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.GenericConverter;

public class GenericDomainEntityConverter implements GenericConverter {
	
	@Autowired
	private DomainLoaderDao domainLoaderDao;

	private Set<Class<?>> domainClasses;

    public Set<ConvertiblePair> getConvertibleTypes() {
        Set<ConvertiblePair> convertablePairs = new HashSet<ConvertiblePair>();
        for (Class<?> domainClass : domainClasses) {
            convertablePairs.add(new ConvertiblePair(String.class, domainClass));
        }
        System.out.println("setConvPair" + convertablePairs);
        return convertablePairs;
    }

    public Object convert(Object source, TypeDescriptor sourceType, TypeDescriptor targetType) {
    	System.out.println("convert");
        return domainLoaderDao.load(targetType.getType(), new Long(source.toString()));
    }

    public void setDomainLoaderDao(DomainLoaderDao domainLoaderDao) {
    	System.out.println("setDao");
        this.domainLoaderDao = domainLoaderDao;
    }

    public void setDomainClasses(Set<Class<?>> domainClasses) {
    	System.out.println("setDClasses" + domainClasses);
        this.domainClasses = domainClasses;
    }

}
