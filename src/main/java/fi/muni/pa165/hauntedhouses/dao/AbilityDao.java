package fi.muni.pa165.hauntedhouses.dao;

import fi.muni.pa165.hauntedhouses.entity.Ability;
import java.util.List;

/**
 * @author Adam Dobiáš, 451044
 */
public interface AbilityDao {
    
    /**
     * creates new Ability entry in database
     * @param ability 
     */
    public void create(Ability ability);
    
    /**
     * updates given item
     * @param ability 
     */
    public void update(Ability ability);
    
    /**
     * removes given ability from DB
     * @param ability 
     */
    public void remove(Ability ability);
    
    /**
     * finds item
     * @param Id
     * @return ability specified by id, null otherwise
     */
    public Ability findById(Long Id);
    
    /**
     * finds item
     * @param abilityName
     * @return ability specified by (unique) name, null otherwise
     */
    public Ability findByName(String abilityName);
    
    /**
     * finds all abilities currently stored in DB
     * @return list of currently stored abilities
     */
    public List<Ability> findAll();
    
}
