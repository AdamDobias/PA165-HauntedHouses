package fi.muni.pa165.hauntedhouses.service;

import java.util.Collection;
import java.util.List;
import org.dozer.Mapper;

/**
 *
 * @author Adam Dobias
 */
public interface BeanMappingService {
    
    /**
     * 
     * @param <T> type of class
     * @param objects opjects which should be mapped
     * @param mapToClass class to which objects should be mapped
     * @return list of mapped objects
     */
    public  <T> List<T> mapTo(Collection<?> objects, Class<T> mapToClass);

    /**
     * 
     * @param <T> type of class
     * @param o obect which should be mapped
     * @param mapToClass class to which map
     * @return mapped object
     */
    public  <T> T mapTo(Object o, Class<T> mapToClass);
    
    
    public Mapper getMapper();
    
}
