package fi.muni.pa165.hauntedhouses.dao;

import fi.muni.pa165.hauntedhouses.entity.Ability;
import fi.muni.pa165.hauntedhouses.enums.AbilityType;

import java.util.List;

/**
 * @author Adam Dobias, 451044
 */

public interface AbilityDao {

    /**
     * Creates a new ability entry in the database.
     *
     * @param ability the ability to be created
     */
    public void create(Ability ability);

    /**
     * Updates the given ability.
     *
     * @param ability the ability to be updated
     */
    public void update(Ability ability);

    /**
     * Removes the given ability from the database.
     *
     * @param ability the ability to be removed
     */
    public void remove(Ability ability);

    /**
     * Finds an ability by its ID.
     *
     * @param id of the ability to be found
     * @return the ability specified by its ID, null otherwise
     */
    public Ability findById(Long id);

    /**
     * Finds an ability by its name.
     *
     * @param abilityName of the ability to be found
     * @return the ability specified by its (unique) name, null otherwise
     */
    public Ability findByName(String abilityName);

    /**
     * Finds all abilities currently stored in the database.
     *
     * @return a list of the currently stored abilities
     */
    public List<Ability> findAll();

    /**
     * returns list of all available ability types
     * @return 
     */
    public List<AbilityType> getAbilityTypes();
    
}
