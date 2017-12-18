package fi.muni.pa165.hauntedhouses.entity;

import fi.muni.pa165.hauntedhouses.enums.Role;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import javax.validation.constraints.NotNull;

/**
 * @author Marek Bohm 396257
 */

@Entity
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String surname;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Role role;

    @NotNull
    @Column(nullable = false, unique = true)
    private String login;

    private String password;

    @ManyToOne
    private House house;

    // Constructors:
    
    public Person() {
    }

    // Getters and setters:
    
    public Long getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPasswordHash(String password) {
        this.password = password;
    }

    public House getHouse() {
        return house;
    }

    public void setHouse(House house) {
        this.house = house;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || !(o instanceof Person)) {
            return false;
        }
        Person person = (Person) o;
        return getLogin() != null ? getLogin().equals(person.getLogin()) : person.getLogin() == null;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        return prime + ((login == null) ? 0 : login.hashCode());
    }

    @Override
    public String toString() {
        return "Person{"
                + "id=" + id
                + ", name='" + name + '\''
                + ", surname='" + surname + '\''
                + ", type=" + role
                + ", login='" + login + '\''
                + '}';
    }
}
