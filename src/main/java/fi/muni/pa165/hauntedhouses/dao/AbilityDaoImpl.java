/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.muni.pa165.hauntedhouses.dao;

import fi.muni.pa165.hauntedhouses.entity.Ability;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Adam Dobiáš, 451044
 */
public class AbilityDaoImpl implements AbilityDao {

    @PersistenceContext
    private EntityManager em;
    
    @Override
    public void create(Ability ability) {
        em.persist(ability);
    }

    @Override
    public void update(Ability ability) {
        em.merge(ability);
    }

    @Override
    public void remove(Ability ability) {
        em.remove(ability);
    }

    @Override
    public Ability findById(Long id) {
        return em.find(Ability.class, id);
    }

    @Override
    public Ability findByName(String abilityName) {
        try {
            return em.createQuery("SELECT a FROM Ability a WHERE a.name = :name", Ability.class)
                    .setParameter("name", abilityName)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public List<Ability> findAll() {
        return em.createQuery("SELECT a FROM Ability a", Ability.class).getResultList();
    }
    
}
