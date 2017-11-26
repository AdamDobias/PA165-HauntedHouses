package fi.muni.pa165.hauntedhouses.service;

import fi.muni.pa165.hauntedhouses.dao.HouseDao;
import fi.muni.pa165.hauntedhouses.dao.PersonDao;
import fi.muni.pa165.hauntedhouses.entity.Ghost;
import fi.muni.pa165.hauntedhouses.entity.House;
import fi.muni.pa165.hauntedhouses.entity.Person;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import org.springframework.dao.DataAccessException;

/**
 *
 * @author Adam Dobiáš
 */
public class HouseServiceImpl implements HouseService{

    @Inject
    private HouseDao houseDao;
    
    @Inject
    private PersonDao personDao;
    
    @Override
    public List<House> findAllHouses() throws DataAccessException {
        return houseDao.findAll();
    }

    @Override
    public House findByName(String name) throws DataAccessException, IllegalArgumentException {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("name cannot be null or empty");
        }
        return houseDao.findByName(name);
    }

    @Override
    public House findByAdress(String adress) throws DataAccessException, IllegalArgumentException {
        if (adress == null || adress.isEmpty()) {
            throw new IllegalArgumentException("adress cannot be null or empty");
        }
        for (House h : houseDao.findAll()) {
            if (h.getAddress().equals(adress)) {
                return h;
            }
        }
        return null;
    }

    @Override
    public House findById(Long id) throws DataAccessException, IllegalArgumentException {
        if (id == null) {
            throw new IllegalArgumentException("cannot search by null id");
        }
        return houseDao.findByID(id);
    }

    @Override
    public void updateHouse(House house) throws DataAccessException, IllegalArgumentException {
        if (house == null) {
            throw new IllegalArgumentException("cannot update null house");
        }
        houseDao.update(house);
    }

    @Override
    public void deleteHouse(House house) throws DataAccessException, IllegalArgumentException {
        if (house == null) {
            throw new IllegalArgumentException("cannot delete null house");
        }
        houseDao.remove(house);
    }

    @Override
    public void createHouse(House house) throws DataAccessException, IllegalArgumentException {
        if (house == null) {
            throw new IllegalArgumentException("cannot create null house");
        }
        houseDao.create(house);
    }

    @Override
    public boolean isHouseHaunted(House house) throws DataAccessException, IllegalArgumentException {
        if (house == null) {
            throw new IllegalArgumentException("house cannot be null");
        }
        return !(house.getGhosts().isEmpty());
    }

    @Override
    public boolean isOccupated(House house) throws DataAccessException, IllegalArgumentException {
        if (house == null) {
            throw new IllegalArgumentException("house cannot be null");
        }
        return !(house.getResidents().isEmpty());
    }

    @Override
    public List<Ghost> getGhosts(House house) throws DataAccessException, IllegalArgumentException {
        if (house == null) {
            throw new IllegalArgumentException("house cannot be null");
        }
        return new ArrayList<>(house.getGhosts());
    }

    @Override
    public List<Person> getInhabitants(House house) throws DataAccessException, IllegalArgumentException {
        if (house == null) {
            throw new IllegalArgumentException("house cannot be null");
        }
        return new ArrayList<>(house.getResidents());
    }

    @Override
    public Person getOwner(House house) throws DataAccessException, IllegalArgumentException {
        if (house == null) {
            throw new IllegalArgumentException("house cannot be null");
        }
        return personDao.findById(house.getOwnerID());
    }
    
}
