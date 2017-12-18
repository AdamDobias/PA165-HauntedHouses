package fi.muni.pa165.hauntedhouses.dao;

import static org.assertj.core.api.Assertions.assertThat;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import fi.muni.pa165.hauntedhouses.entity.Person;
import fi.muni.pa165.hauntedhouses.enums.Role;

/**
 *
 * @author Adam Dobiáš, 451044
 */

public class PersonDaoTest extends AbstractDaoTest {

    @Autowired
    private PersonDao personDao;

    private Person p1, p2;

    @BeforeMethod
    public void setUp() {
        p1 = new Person();
        p1.setName("frank");
        p1.setSurname("somington");
        p1.setLogin("feri");
        p1.setRole(Role.OWNER);

        p2 = new Person();
        p2.setName("john");
        p2.setSurname("blablington");
        p2.setLogin("johnny");
        p2.setRole(Role.RESIDENT);
    }

    @Test
    public void createTest() {
        assertThat(p1.getId()).isNull();
        assertThat(p2.getId()).isNull();

        personDao.create(p1);
        personDao.create(p2);

        assertThat(p1.getId()).isNotNull();
        assertThat(p2.getId()).isNotNull();
        assertThat(p1.getId()).isNotSameAs(p2.getId());
    }

    @Test
    public void updateTest() {
        personDao.create(p1);
        personDao.create(p2);

        String n1 = p1.getName();
        String n2 = p2.getName();

        p2.setName(n2 + "aaa");

        // It works the same without this -> is the update method unnecessary?
        personDao.update(p2);

        assertThat(n1).startsWith(p1.getName()).endsWith(p1.getName());
        assertThat(n2 + "aaa").startsWith(p2.getName()).endsWith(p2.getName());
    }

    @Test
    public void removeTest() {
        personDao.create(p1);
        personDao.create(p2);

        personDao.remove(p1);

        assertThat(personDao.findById(p1.getId())).isNull();
        assertThat(personDao.findById(p2.getId())).isNotNull();
    }

    @Test
    public void removeNotInDBTest() {
        personDao.create(p2);
        personDao.remove(p1);
        
        assertThat(personDao.findAll()).containsOnly(p2);
    }

    @Test
    public void findByIdTest() {
        personDao.create(p1);
        personDao.create(p2);

        assertThat(personDao.findById(p1.getId())).isEqualToComparingFieldByField(p1);
    }

    @Test
    public void findByNonExistingIdTest() {
        assertThat(personDao.findById(Long.MAX_VALUE)).isNull();
    }

    @Test
    public void findRemovedByIdTest() {
        personDao.create(p1);
        personDao.create(p2);
        personDao.remove(p2);

        assertThat(personDao.findById(p2.getId())).isNull();
    }

    @Test
    public void findPersonByLoginTest() {
        personDao.create(p1);
        personDao.create(p2);

        assertThat(personDao.findPersonByLogin(p1.getLogin())).isEqualToComparingFieldByField(p1);
    }

    @Test
    public void findPersonByNonexistingLoginTest() {
        assertThat(personDao.findPersonByLogin("a-sdg-df-b-dxf-bcv-sdzfx-cbvdzsxc")).isNull();
    }

    @Test
    public void findPersonRemovedByLoginTest() {
        personDao.create(p1);
        personDao.create(p2);
        personDao.remove(p2);

        assertThat(personDao.findPersonByLogin(p2.getLogin())).isNull();
    }

    @Test
    public void findAll() {
        Person remove = new Person();
        remove.setName("romie");
        remove.setSurname("removington");
        remove.setLogin("rom");
        remove.setRole(Role.RESIDENT);

        personDao.create(p1);
        personDao.create(p2);
        personDao.create(remove);
        assertThat(personDao.findAll()).containsOnly(p1, p2, remove);
        personDao.remove(remove);
        assertThat(personDao.findAll()).containsOnly(p1, p2);
    }

}
