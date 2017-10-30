package fi.muni.pa165.hauntedhouses.entity;

import fi.muni.pa165.hauntedhouses.enums.Role;

import javax.persistence.*;
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
    private Role type;

    @NotNull
    @Column(nullable = false, unique = true)
    private String login;

    private String passwordHash;

    @ManyToOne
    private House house;

    // Constructor
    public Person() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Role getType() {
        return type;
    }

    public void setType(Role type) {
        this.type = type;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public House getHouse() {
        return house;
    }

    public void setHouse(House house) {
        this.house = house;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return login.equals(person.login);
    }

    @Override
    public int hashCode() {
        return id.hashCode() * login.hashCode();
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", type=" + type +
                ", login='" + login + '\'' +
                '}';
    }
}
