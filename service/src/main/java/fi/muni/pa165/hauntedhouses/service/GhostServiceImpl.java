package fi.muni.pa165.hauntedhouses.service;

import fi.muni.pa165.hauntedhouses.dao.AbilityDao;
import fi.muni.pa165.hauntedhouses.dao.GhostDao;
import fi.muni.pa165.hauntedhouses.dao.HouseDao;
import fi.muni.pa165.hauntedhouses.entity.Ability;
import fi.muni.pa165.hauntedhouses.entity.Ghost;
import fi.muni.pa165.hauntedhouses.entity.House;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

/**
 * @author Adam Dobias
 */

@Service
public class GhostServiceImpl implements GhostService {

    @Inject
    private GhostDao ghostDao;

    @Inject
    private AbilityDao abilityDao;

    @Inject
    private HouseDao houseDao;

    @Override
    public Ghost findById(Long id) throws DataAccessException, IllegalArgumentException {
        if (id == null) {
            throw new IllegalArgumentException("The ID cannot be null!");
        }
        return ghostDao.findById(id);
    }

    @Override
    public Ghost findByName(String name) throws DataAccessException, IllegalArgumentException {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("The name cannot be null or an empty string!");
        }
        return ghostDao.findByName(name);
    }

    @Override
    public List<Ghost> findAll() throws DataAccessException {
        return ghostDao.findAll();
    }

    @Override
    public void deleteGhost(Ghost ghost) throws DataAccessException, IllegalArgumentException {
        if (ghost == null) {
            throw new IllegalArgumentException("The ghost to be removed cannot be null!");
        }
        ghostDao.remove(ghost);
    }

    @Override
    public void updateGhost(Ghost ghost) throws DataAccessException, IllegalArgumentException {
        if (ghost == null) {
            throw new IllegalArgumentException("The ghost to be updated cannot be null!");
        }
        ghostDao.update(ghost);
    }

    @Override
    public void createGhost(Ghost ghost) throws DataAccessException, IllegalArgumentException {
        if (ghost == null) {
            throw new IllegalArgumentException("The ghost to be created cannot be null!");
        }
        ghostDao.create(ghost);
    }

    @Override
    public List<Ghost> findByAbility(Ability ability) throws DataAccessException, IllegalArgumentException {
        if (ability == null) {
            throw new IllegalArgumentException("The ability cannot be null!");
        }
        List<Ghost> result = new ArrayList<>();
        for (Ghost g : ghostDao.findAll()) {
            if (g.getAbilities().contains(ability)) {
                result.add(g);
            }
        }
        return result;
    }

    @Override
    public void giveAbility(Ghost ghost, Ability ability) throws DataAccessException, IllegalArgumentException {
        if (ghost == null || ghostDao.findById(ghost.getId()) == null) {
            throw new IllegalArgumentException("The ghost must be present in the database and cannot be null!");
        }
        if (ability == null || abilityDao.findById(ability.getId()) == null) {
            throw new IllegalArgumentException("The ability must be present in the database and cannot be null!");
        }
        ghost.addAbility(ability);
        ability.addGhost(ghost);
        //not sure if both needed
        ghostDao.update(ghost);
        abilityDao.update(ability);
    }

    @Override
    public void removeAbility(Ghost ghost, Ability ability) throws DataAccessException, IllegalArgumentException {
        if (ghost == null || ghostDao.findById(ghost.getId()) == null) {
            throw new IllegalArgumentException("The ghost must be present in the database and cannot be null!");
        }
        if (ability == null || abilityDao.findById(ability.getId()) == null) {
            throw new IllegalArgumentException("The ability must be present in the database and cannot be null!");
        }
        ghost.removeAbility(ability);
        ability.removeGhost(ghost);
        ghostDao.update(ghost);
        abilityDao.update(ability);
    }

    @Override
    public void hauntHouse(House house, Ghost ghost) throws DataAccessException, IllegalArgumentException {
        if (ghost == null || ghostDao.findById(ghost.getId()) == null) {
            throw new IllegalArgumentException("The ghost must be present in the database and cannot be null!");
        }
        if (house == null || houseDao.findByID(house.getId()) == null) {
            throw new IllegalArgumentException("The house must be present in the database and cannot be null!");
        }
        House oldHouse = ghost.getHouse();
        oldHouse.removeGhost(ghost);
        ghost.setHouse(house);
        house.addGhost(ghost);
        houseDao.update(oldHouse);
        houseDao.update(house);
        ghostDao.update(ghost);
    }

    @Override
    public boolean isGhostHaunting(Ghost ghost) throws DataAccessException, IllegalArgumentException {
        if (ghost == null || ghostDao.findById(ghost.getId()) == null) {
            throw new IllegalArgumentException("The ghost must be present in the database and cannot be null!");
        }
        Date now = new Date();
        return (ghost.getHauntFrom().before(now) && ghost.getHauntTo().after(now));
    }

}
