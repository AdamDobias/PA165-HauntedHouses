package fi.muni.pa165.hauntedhouses.service;

import fi.muni.pa165.hauntedhouses.dao.AbilityDao;
import fi.muni.pa165.hauntedhouses.entity.Ability;
import fi.muni.pa165.hauntedhouses.enums.AbilityType;

import java.util.List;

import javax.inject.Inject;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

/**
 * @author Adam Dobias
 */

@Service
public class AbilityServiceImpl implements AbilityService {

    @Inject
    private AbilityDao ad;

    @Override
    public List<Ability> findAllAbilities() throws DataAccessException {
        return ad.findAll();
    }

    @Override
    public Ability findById(Long id) throws DataAccessException, IllegalArgumentException {
        if (id == null) {
            throw new IllegalArgumentException("The ID cannot be null!");
        }

        return ad.findById(id);
    }

    @Override
    public Ability findByName(String name) throws DataAccessException, IllegalArgumentException {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("The name cannot be null!");
        }
        return ad.findByName(name);
    }

    @Override
    public void createAbility(Ability ability) throws DataAccessException, IllegalArgumentException {
        if (ability == null) {
            throw new IllegalArgumentException("The ability cannot be null!");
        }
        ad.create(ability);
    }

    @Override
    public void updateAbility(Ability ability) throws DataAccessException, IllegalArgumentException {
        if (ability == null) {
            throw new IllegalArgumentException("The ability cannot be null!");
        }
        ad.update(ability);
    }

    @Override
    public void deleteAbility(Ability ability) throws DataAccessException, IllegalArgumentException {
        if (ability == null) {
            throw new IllegalArgumentException("The ability cannot be null!");
        }
        ad.remove(ability);
    }

    @Override
    public List<AbilityType> getAbilityTypes() {
        return ad.getAbilityTypes();
    }
}
