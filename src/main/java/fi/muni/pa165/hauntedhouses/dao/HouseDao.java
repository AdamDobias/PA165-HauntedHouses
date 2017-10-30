package fi.muni.pa165.hauntedhouses.dao;

import fi.muni.pa165.hauntedhouses.entity.House;

import java.util.List;

/**
 * Author: Klara Kufova, 410091.
 * Created on Oct 22, 2017.
 */

public interface HouseDao {
    
    /**
     * Creates a new house.
     * @param house the house to be created
     */
    public void create(House house);
    
    /**
     * Updates a house in the database.
     * @param house the house to be updated
     */
    public void update(House house);
    
    /**
     * Removes a house.
     * @param house the house to be removed
     */
    public void remove(House house);
    
    /**
     * Returns a specific house based on its unique ID.
     * @param id the ID of the house to be returned
     * @return the found house
     */
    public House findByID(Long id);
    
    /**
     * Returns a specific house based on its unique name.
     * @param name the name of the house to be returned
     * @return the found house
     */
    public House findByName(String name);
    
    /**
     * Returns a list of all houses.
     * @return the list of all houses
     */
    public List<House> findAll();
}
