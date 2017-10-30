/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.muni.pa165.hauntedhouses.dao;

import fi.muni.pa165.hauntedhouses.PersistenceApplicationContext;
import fi.muni.pa165.hauntedhouses.entity.Person;
import fi.muni.pa165.hauntedhouses.enums.Role;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

//import org.junit.After;
//import org.junit.AfterClass;
//import org.junit.Before;
//import org.junit.BeforeClass;
//import org.junit.Test;
//import static org.junit.Assert.*;
import static org.assertj.core.api.Assertions.*;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;


/**
 *
 * @author Adam Dobiáš, 451044
 */
@ContextConfiguration(classes = PersistenceApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class PersonDaoTest extends AbstractTestNGSpringContextTests {
    
    @Autowired
    private PersonDao personDao;
    
    private Person p1, p2;
    
    @BeforeMethod
    public void setUp() {
        p1 = new Person();        
        p1.setName("frank");
        p1.setSurname("somington");
        p1.setLogin("feri");
        p1.setType(Role.OWNER);

        p2 = new Person();
        p2.setName("john");
        p2.setSurname("blablington");
        p2.setLogin("johnny");
        p2.setType(Role.RESIDENT);
    }
    
    @Test
    public void fooTest() {

        assertThat(p1.getId()).isNull();
        assertThat(p2.getId()).isNull();
        
        int before = personDao.findAll().size();
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
        
        //works the same without this -> update method is unnecessary?
        personDao.update(p2);
        
        assertThat(n1).startsWith(p1.getName()).endsWith(p1.getName());
        assertThat(n2+"aaa").startsWith(p2.getName()).endsWith(p2.getName());

    }   
    
    @Test
    public void removeTest() {
        
        personDao.create(p1);
        personDao.create(p2);
        
        personDao.remove(p1);
        
        
        assertThat(personDao.findAll()).doesNotContain(p1);
        
        
    }
}
