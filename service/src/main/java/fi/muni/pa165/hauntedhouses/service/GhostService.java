package fi.muni.pa165.hauntedhouses.service;

import fi.muni.pa165.hauntedhouses.entity.Ability;
import fi.muni.pa165.hauntedhouses.entity.Ghost;
import fi.muni.pa165.hauntedhouses.entity.House;
import java.util.List;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

/**
 *
 * @author Adam Dobias
 */
@Service
public interface GhostService {
    
    /**
     * 
     * @param id by which to search
     * @return ghost with matching id
     * @throws DataAccessException in case of any failure on persistence layer
     * @throws IllegalArgumentException if id is null
     */
    Ghost findById(Long id)  throws DataAccessException, IllegalArgumentException;
    
    /**
     * 
     * @param name by which to search
     * @return ghost with mathing name
     * @throws DataAccessException in case of any failure on persistence layer
     * @throws IllegalArgumentException if name is null or empty
     */
    Ghost findByName(String name) throws DataAccessException, IllegalArgumentException;
    
    /**
     * 
     * @return list of all ghosts in DB
     * @throws DataAccessException in case of any failure on persistence layer
     */
    List<Ghost> findAll()  throws DataAccessException;
    
    /**
     * 
     * @param ghost to be deleted
     * @throws DataAccessException in case of any failure on persistence layer
     * @throws IllegalArgumentException if ghost is null
     */
    void deleteGhost(Ghost ghost)  throws DataAccessException, IllegalArgumentException;
    
    /**
     * 
     * @param ghost new state of the ghost
     * @throws DataAccessException in case of any failure on persistence layer
     * @throws IllegalArgumentException if ghost is null
     */
    void updateGhost(Ghost ghost)  throws DataAccessException, IllegalArgumentException;
    
    /**
     * 
     * @param ghost to be created
     * @throws DataAccessException in case of any failure on persistence layer
     * @throws IllegalArgumentException if ghost is null 
     */
    void createGhost(Ghost ghost)  throws DataAccessException, IllegalArgumentException;
    
    /**
     * 
     * @param ability by which to search
     * @return list of all ghosts having this ability
     * @throws DataAccessException in case of any failure on persistence layer
     * @throws IllegalArgumentException if ability is null
     */
    List<Ghost> findByAbility(Ability ability)  throws DataAccessException, IllegalArgumentException;
    
    /**
     * 
     * @param ghost to which bility will be given
     * @param ability which will be granted
     * @throws DataAccessException in case of any failure on persistence layer
     * @throws IllegalArgumentException if ghost or ability are null or not in DB
     */
    void giveAbility(Ghost ghost, Ability ability) throws DataAccessException, IllegalArgumentException;
    
    /**
     * 
     * @param ghost from which ability will be removed
     * @param ability which will be removed
     * @throws DataAccessException in case of any failure on persistence layer
     * @throws IllegalArgumentException if ghost or ability are null or not in DB
     */
    void removeAbility(Ghost ghost, Ability ability)  throws DataAccessException, IllegalArgumentException;
    
    /**
     * ghost's house will be CHANGED
     * @param house house where ghost should haunt
     * @param ghost ghost which should be haunting
     * @throws DataAccessException in case of any failure on persistence layer
     * @throws IllegalArgumentException if ghost or house are null or not in DB
     */
    void hauntHouse(House house, Ghost ghost) throws DataAccessException, IllegalArgumentException;
    
    /**
     * 
     * @param ghost ghost of concern
     * @return true if is currently haunting, false otherwise
     * @throws DataAccessException in case of any failure on persistence layer
     * @throws IllegalArgumentException if ghost is null or not in DB
     */
    boolean isGhostHaunting(Ghost ghost) throws DataAccessException, IllegalArgumentException;
    
}
