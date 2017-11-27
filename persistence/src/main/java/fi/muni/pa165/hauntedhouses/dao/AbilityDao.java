package fi.muni.pa165.hauntedhouses.dao;

import fi.muni.pa165.hauntedhouses.entity.Ability;

import java.util.List;

/**
 * @author Adam Dobias, 451044
 */

public interface AbilityDao {

    /**
     * Creates a new ability entry in the database.
     *
     * @param ability
     */
    public void create(Ability ability);

    /**
     * Updates the given ability.
     *
     * @param ability
     */
    public void update(Ability ability);

    /**
     * Removes the given ability from the database.
     *
     * @param ability
     */
    public void remove(Ability ability);

    /**
     * Finds an ability.
     *
     * @param Id
     * @return an ability specified by its ID, null otherwise
     */
    public Ability findById(Long Id);

    /**
     * Finds an ability.
     *
     * @param abilityName
     * @return an ability specified by its (unique) name, null otherwise
     */
    public Ability findByName(String abilityName);

    /**
     * Finds all abilities currently stored in the database.
     *
     * @return a list of the currently stored abilities
     */
    public List<Ability> findAll();

}
