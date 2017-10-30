package fi.muni.pa165.hauntedhouses.dao;

import fi.muni.pa165.hauntedhouses.entity.House;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

/**
 * Author: Klara Kufova, 410091.
 * Created on Oct 22, 2017.
 */

@Repository
public class HouseDaoImpl implements HouseDao {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public void create(House house) {
        manager.persist(house);
    }
    
    @Override
    public void remove(House house) {
        manager.remove(house);
    }
    
    @Override
    public void update(House house) {
        manager.merge(house);
    }
    
    @Override
    public House findByID(Long id) {
        return manager.find(House.class, id);
    }
    
    @Override
    public House findByName(String name) {
        try {
            return manager.createQuery("SELECT h FROM House h WHERE h.name = :name", House.class)
                    .setParameter("name", name)
                    .getSingleResult();
        } catch (NoResultException exception) {
            return null;
        }
    }
    
    @Override
    public List<House> findAll() {
        return manager.createQuery("SELECT h FROM House h", House.class).getResultList();
    }
}
