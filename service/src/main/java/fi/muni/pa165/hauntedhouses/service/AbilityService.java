package fi.muni.pa165.hauntedhouses.service;

import fi.muni.pa165.hauntedhouses.entity.Ability;
import java.util.List;
import org.springframework.dao.DataAccessException;

/**
 *
 * @author Adam Dobiáš
 */
public interface AbilityService {
    
    /**
     * 
     * @return list of all abilities
     * @throws DataAccessException in case of any failure on persistence layer
     */
    List<Ability> findAllAbilities() throws DataAccessException;
    
    /**
     * 
     * @param id by which to search
     * @return Ability with given Id, null if non exiting
     * @throws DataAccessException in case of any failure on persistence layer
     * @throws IllegalArgumentException if id is null
     */
    Ability findById(Long id) throws DataAccessException, IllegalArgumentException;
    
    /**
     * 
     * @param name by which to search
     * @return ability matching given name
     * @throws DataAccessException in case of any failure on persistence layer
     * @throws IllegalArgumentException if name is null or empty string
     */
    Ability findByName(String name) throws DataAccessException, IllegalArgumentException;
    
    /**
     * 
     * @param ability to be created
     * @throws DataAccessException in case of any failure on persistence layer
     * @throws IllegalArgumentException if ability is null
     */
    void createAbility(Ability ability) throws DataAccessException, IllegalArgumentException;
    
    /**
     * 
     * @param ability new state of ability
     * @throws DataAccessException in case of any failure on persistence layer
     * @throws IllegalArgumentException if ability is null
     */
    void updateAbility(Ability ability) throws DataAccessException, IllegalArgumentException;
    
    /**
     * 
     * @param ability to be deleted
     * @throws DataAccessException in case of any failure on persistence layer
     * @throws IllegalArgumentException if ability is null
     */
    void deleteAbility(Ability ability) throws DataAccessException, IllegalArgumentException;
    
}
