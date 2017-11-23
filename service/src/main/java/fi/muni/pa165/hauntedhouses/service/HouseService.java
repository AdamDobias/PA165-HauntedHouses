
package fi.muni.pa165.hauntedhouses.service;

import fi.muni.pa165.hauntedhouses.entity.Ghost;
import fi.muni.pa165.hauntedhouses.entity.House;
import fi.muni.pa165.hauntedhouses.entity.Person;
import java.util.List;
import org.springframework.dao.DataAccessException;

/**
 *
 * @author Adam Dobiáš
 */
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
     * @return list of all houses with matching name
     * @throws DataAccessException in case of any failure on persistence layer
     * @throws IllegalArgumentException if name is null or empty
     */
    List<House> findByName(String name) throws DataAccessException, IllegalArgumentException;
    
    /**
     * 
     * @param adress by which to search
     * @return house on this address
     * @throws DataAccessException in case of any failure on persistence layer
     * @throws IllegalArgumentException  if adress is null or empty
     */
    House findByAdress(String adress) throws DataAccessException, IllegalArgumentException;
    
    /**
     * 
     * @param id by which to search
     * @return house with given id
     * @throws DataAccessException in case of any failure on persistence layer
     * @throws IllegalArgumentException if id is null
     */
    House findById(Long id) throws DataAccessException, IllegalArgumentException;
    
    /**
     * 
     * @param house new state of the house
     * @throws DataAccessException in case of any failure on persistence layer
     * @throws IllegalArgumentException if house is null
     */
    void updateHouse(House house) throws DataAccessException, IllegalArgumentException;
    
    /**
     * 
     * @param house to be deleted
     * @throws DataAccessException in case of any failure on persistence layer
     * @throws IllegalArgumentException if house is null
     */
    void deleteHouse(House house) throws DataAccessException, IllegalArgumentException;
    
    /**
     * 
     * @param house to be created
     * @throws DataAccessException in case of any failure on persistence layer
     * @throws IllegalArgumentException if house is null
     */
    void createHouse(House house) throws DataAccessException, IllegalArgumentException;
    
    /**
     * ghost's house will be CHANGED
     * @param house house where ghost should be assigned
     * @param ghost ghost which should be assigned
     * @throws DataAccessException in case of any failure on persistence layer
     * @throws IllegalArgumentException if ghost or house are null
     */
    void assignGhostToHouse(House house, Ghost ghost) throws DataAccessException, IllegalArgumentException;

    /**
     * person's house will be CHANGED
     * @param house to where person will be living
     * @param person person which moves in
     * @throws DataAccessException  in case of any failure on persistence layer
     * @throws IllegalArgumentException if house or person is null
     */
    void addNewTenant(House house, Person person)  throws DataAccessException, IllegalArgumentException;

    
    /* these methods should perhaps be a part of facade layer?
    boolean isHouseHaunted(House house)  throws DataAccessException, IllegalArgumentException;
    
    boolean isOccupated(House house) throws DataAccessException, IllegalArgumentException;

    List<Ghost> getGhosts(House house)  throws DataAccessException, IllegalArgumentException;
    
    List<Person> getInhabitants(House house)  throws DataAccessException, IllegalArgumentException;
    
    Person getOwner(House house)  throws DataAccessException, IllegalArgumentException;
    */
    
}
