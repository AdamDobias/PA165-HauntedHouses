package fi.muni.pa165.hauntedhouses.facade;

import fi.muni.pa165.hauntedhouses.dto.AbilityDTO;
import fi.muni.pa165.hauntedhouses.enums.AbilityType;
import fi.muni.pa165.hauntedhouses.enums.OBSOLETEAbilityType;

import java.util.List;

/**
 * Created by Marek Bohm, 396257.
 */

public interface AbilityFacade {

    /**
     * Creates a new ability.
     *
     * @param ability the ability to be created
     */
    void createAbility(AbilityDTO ability);

    /**
     * Updates the given ability.
     *
     * @param ability the ability to be updated
     */
    void updateAbility(AbilityDTO ability);

    /**
     * Deletes the ability with the given ID.
     *
     * @param id of the ability to be deleted
     */
    void deleteAbility(Long id);

    /**
     * Finds the ability with the given ID.
     *
     * @param id of the ability to be found
     * @return the ability with the given ID
     */
    AbilityDTO findById(Long id);

    /**
     * Finds the ability with the given name.
     *
     * @param name of the ability to be found
     * @return the ability with the given name
     */
    AbilityDTO findByName(String name);

    /**
     * Finds all abilities.
     *
     * @return list of all abilities
     */
    List<AbilityDTO> findAllAbilities();
    
    List<AbilityType> getAbilityTypes();
}
