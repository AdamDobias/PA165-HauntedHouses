package fi.muni.pa165.hauntedhouses.service;

import fi.muni.pa165.hauntedhouses.entity.Ability;
import fi.muni.pa165.hauntedhouses.enums.AbilityType;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

/**
 * @author Adam Dobias
 */

@Service
public interface AbilityService {

    /**
     * Returns a list of all abilities.
     * 
     * @return a list of all abilities
     * @throws DataAccessException in case of any failure on the persistence layer
     */
    List<Ability> findAllAbilities() throws DataAccessException;

    /**
     * Finds an ability based on its ID.
     * 
     * @param id of the ability to be found
     * @return the ability with the given ID, null if the ability does not exist
     * @throws DataAccessException in case of any failure on the persistence layer
     * @throws IllegalArgumentException if the used ID is null
     */
    Ability findById(Long id) throws DataAccessException, IllegalArgumentException;

    /**
     * Finds an ability based on its name.
     * 
     * @param name of the ability to be found
     * @return the ability with the given name
     * @throws DataAccessException in case of any failure on the persistence layer
     * @throws IllegalArgumentException if the used name is null or an empty string
     */
    Ability findByName(String name) throws DataAccessException, IllegalArgumentException;

    /**
     * Creates an ability.
     * 
     * @param ability to be created
     * @throws DataAccessException in case of any failure on the persistence layer
     * @throws IllegalArgumentException if the ability is null
     */
    void createAbility(Ability ability) throws DataAccessException, IllegalArgumentException;

    /**
     * Updates an ability.
     * 
     * @param ability to be updated
     * @throws DataAccessException in case of any failure on the persistence layer
     * @throws IllegalArgumentException if the ability is null
     */
    void updateAbility(Ability ability) throws DataAccessException, IllegalArgumentException;

    /**
     * Removes an ability.
     * 
     * @param ability to be deleted
     * @throws DataAccessException in case of any failure on the persistence layer
     * @throws IllegalArgumentException if the ability is null
     */
    void deleteAbility(Ability ability) throws DataAccessException, IllegalArgumentException;

    List<AbilityType> getAbilityTypes();
    
}
