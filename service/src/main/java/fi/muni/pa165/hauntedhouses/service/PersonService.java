
package fi.muni.pa165.hauntedhouses.service;

import fi.muni.pa165.hauntedhouses.entity.House;
import fi.muni.pa165.hauntedhouses.entity.Person;
import fi.muni.pa165.hauntedhouses.enums.Role;
import java.util.List;
import org.springframework.dao.DataAccessException;

/**
 *
 * @author Adam Dobiáš
 */
public interface PersonService {
    
    /**
     * 
     * @param p Person to be created
     * @param password to person's account, will be encrypted
     * @throws DataAccessException in case of any failure on persistence layer
     * @throws IllegalArgumentException if person is null
     */
    void registerPerson(Person p, String password) throws DataAccessException, IllegalArgumentException;
    
    /**
     * 
     * @param p person to be removed
     * @throws DataAccessException in case of any failure on persistence layer
     * @throws IllegalArgumentException if person is null
     */
    void removePerson(Person p) throws DataAccessException, IllegalArgumentException;
    
    /**
     * 
     * @return List of Person entities, null if empty
     * @throws DataAccessException in case on any failure on persistence layer
     */
    List<Person> getAllPeople() throws DataAccessException;
    
    /**
     * 
     * @param p Person to be authentificated
     * @param password  person's password
     * @return true if password hash matches record in DB, false otherwise
     * @throws DataAccessException in case of any failure on persistence layer
     * @throws IllegalArgumentException if person is null
     */
    boolean authenticate(Person p, String password) throws DataAccessException, IllegalArgumentException;
    
    /**
     * 
     * @param p Person of interest
     * @param acessConstraint Roles, for which action is permitted
     * @return true if person is allowed for action, false otherwise
     * @throws DataAccessException in case of any failure on persistence layer
     * @throws IllegalArgumentException if person or list are null or if list is an empty list
     */
    boolean isAllowed(Person p, List<Role> acessConstraint) throws DataAccessException, IllegalArgumentException;
    
    /**
     * 
     * @param id id by which to search
     * @return Person with that Id, null if non existent
     * @throws DataAccessException in case of any failure on persistence layer
     * @throws IllegalArgumentException if id is null
     */
    Person findPersonById(Long id) throws DataAccessException, IllegalArgumentException;
    
    /**
     * 
     * @param name or surname or login of the Person
     * @return List of Person entities having matching login or name or surname
     * @throws DataAccessException in case of any failure on persistence layer
     * @throws IllegalArgumentException if name is null or empty string
     */
    List<Person> findPersonByName(String name) throws DataAccessException, IllegalArgumentException;
    
    /**
     * 
     * @param person new state of Person
     * @throws DataAccessException in case of any failure on persistence layer
     * @throws IllegalArgumentException if person is null
     */
    void updatePerson(Person person) throws DataAccessException, IllegalArgumentException;
    
    /**
     * person's current house will be CHNAGED
     * @param house which should be inhabited
     * @param person which should be moving
     * @throws DataAccessException in case of any failure on persistence layer
     * @throws IllegalArgumentException if person or house are null or non existent in DB
     */
    void inhabitHouse(House house, Person person) throws DataAccessException, IllegalArgumentException;
    
}
