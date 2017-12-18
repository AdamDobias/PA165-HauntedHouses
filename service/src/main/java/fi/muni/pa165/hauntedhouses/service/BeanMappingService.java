package fi.muni.pa165.hauntedhouses.service;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.dozer.Mapper;

/**
 * @author Adam Dobias
 */

public interface BeanMappingService {

    public <T> List<T> mapTo(Collection<?> objects, Class<T> mapToClass);

    public <T> T mapTo(Object u, Class<T> mapToClass);

    public Mapper getMapper();
    
}
