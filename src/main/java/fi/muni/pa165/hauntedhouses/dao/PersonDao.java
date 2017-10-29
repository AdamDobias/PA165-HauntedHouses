package fi.muni.pa165.hauntedhouses.dao;

import fi.muni.pa165.hauntedhouses.entity.Person;
import java.util.List;

/**
 * @author Marek Bohm, 396257
 */
    public interface PersonDao {
        /**
         * Create persistent representation of person in database.
         * @param p Person to persist
         */
        public void create(Person p);

        /**
         * Update person in database.
         * @param p person to update.
         */
        public void update(Person p);

        /**
         * Delete person from database.
         * @param p person to delete.
         */
        public void delete(Person p);

        /**
         * Find person in database by given id.
         * @param id person id to be found.
         * @return Person with given id.
         */
        public Person findById(Long id);

        /**
         * Find person by given login.
         * @param login person's login to be found.
         * @return Person with given login.
         */
        public Person findPersonByLogin(String login);

        /**
         * Method for retrieving all persons form database.
         * @return All persons from database.
         */
        public List<Person> findAll();
}

