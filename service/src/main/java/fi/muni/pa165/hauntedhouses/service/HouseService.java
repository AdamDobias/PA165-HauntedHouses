
package fi.muni.pa165.hauntedhouses.service;

import fi.muni.pa165.hauntedhouses.entity.Ghost;
import fi.muni.pa165.hauntedhouses.entity.House;
import fi.muni.pa165.hauntedhouses.entity.Person;
import java.util.List;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

/**
 *
 * @author Adam Dobias
 */
@Service
public interface HouseService {
    
    /**
     * 
     * @return list of all houses in DB
     * @throws DataAccessException in case of any failure on persistence layer
     */
    List<House> findAllHouses() throws DataAccessException;
    
    /**
     * 
     * @param name by which to find
     * @return house with matching name
     * @throws DataAccessException in case of any failure on persistence layer
     * @throws IllegalArgumentException if name is null or empty
     */
    House findByName(String name) throws DataAccessException, IllegalArgumentException;
    
    /**
     * 
     * @param adress by which to search
     * @return house on this address, null if not found
     * @throws DataAccessException in case of any failure on persistence layer
     * @throws IllegalArgumentException  if adress is null or empty
     */
    House findByAdress(String adress) throws DataAccessException, IllegalArgumentException;
    
    /**
     * 
     * @param id by which to search
     * @return house with given id
     * @throws DataAccessException in case of any failure on persistence layer
     * @throws IllegalArgumentException if id is null or not in DB
     */
    House findById(Long id) throws DataAccessException, IllegalArgumentException;
    
    /**
     * 
     * @param house new state of the house
     * @throws DataAccessException in case of any failure on persistence layer
     * @throws IllegalArgumentException if house is nullor not in DB
     */
    void updateHouse(House house) throws DataAccessException, IllegalArgumentException;
    
    /**
     * 
     * @param house to be deleted
     * @throws DataAccessException in case of any failure on persistence layer
     * @throws IllegalArgumentException if house is null or not in DB
     */
    void deleteHouse(House house) throws DataAccessException, IllegalArgumentException;
    
    /**
     * 
     * @param house to be created
     * @throws DataAccessException in case of any failure on persistence layer
     * @throws IllegalArgumentException if house is null or not in DB
     */
    void createHouse(House house) throws DataAccessException, IllegalArgumentException;
    

    /**
     * this has been moved to GhostService under name hauntHouse
     */
    //void assignGhostToHouse(House house, Ghost ghost) throws DataAccessException, IllegalArgumentException;

    
    /**
     * this has been moved to PersonService under name inhabitHouse
    */
    //void addNewTenant(House house, Person person)  throws DataAccessException, IllegalArgumentException;
    
    /**
     * 
     * @param house house of concern
     * @return true if is hounted, false otherwise
     * @throws DataAccessException in case of any failure on persistence layer
     * @throws IllegalArgumentException if house is null or not in DB
     */
    boolean isHouseHaunted(House house)  throws DataAccessException, IllegalArgumentException;
    
    /**
     * 
     * @param house house of concern
     * @return true if has tenants, false otherwise
     * @throws DataAccessException in case of any failure on persistence layer
     * @throws IllegalArgumentException if house is null or not in DB
     */
    boolean isOccupated(House house) throws DataAccessException, IllegalArgumentException;

    /**
     * 
     * @param house house of concern
     * @return list of ghosts haunting in this house
     * @throws DataAccessException in case of any failure on persistence layer
     * @throws IllegalArgumentException if house is null or not in DB
     */
    List<Ghost> getGhosts(House house)  throws DataAccessException, IllegalArgumentException;
    
    /**
     * 
     * @param house of concern
     * @return list of people living in the house
     * @throws DataAccessException in case of any failure on persistence layer
     * @throws IllegalArgumentException if house is null or not in DB 
     */
    List<Person> getInhabitants(House house)  throws DataAccessException, IllegalArgumentException;
    
    /**
     * 
     * @param house of concern
     * @return Person, which is owning the house
     * @throws DataAccessException in case of any failure on persistence layer
     * @throws IllegalArgumentException if house is null or not in DB 
     */
    Person getOwner(House house)  throws DataAccessException, IllegalArgumentException;
   
    
}
