package fi.muni.pa165.hauntedhouses.dao;

import fi.muni.pa165.hauntedhouses.entity.Person;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;


/**
 * @author Marek Bohm, 396257
 */
public class PersonDaoImpl implements PersonDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void create(Person p) {
        em.persist(p);
    }

    @Override
    public void update(Person p) {
        em.merge(p);
    }

    @Override
    public void remove(Person p) {
        em.remove(em.contains(p) ? p : em.merge(p));
    }

    @Override
    public Person findPersonByLogin(String login) {
        if (login == null || login.isEmpty())
            throw new IllegalArgumentException("Cannot search for null login");

        try {
            return em
                    .createQuery("SELECT p FROM Person p WHERE p.login = :login",
                            Person.class).setParameter("login", login)
                    .getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }
    }

    @Override
    public Person findById(Long id) {
        return em.find(Person.class, id);
    }

    @Override
    public List<Person> findAll() {
        TypedQuery<Person> query = em.createQuery("SELECT p FROM Person p", Person.class);
        return query.getResultList();
    }

}
