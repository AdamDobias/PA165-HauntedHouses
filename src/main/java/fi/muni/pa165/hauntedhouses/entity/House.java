package fi.muni.pa165.hauntedhouses.entity;

import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 * Author: Klara Kufova, 410091.
 * Created on Oct 21, 2017.
 */

@Entity
public class House {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false, unique = true)
    private String name;

    @NotNull
    @Column(nullable = false, unique = true)
    private String address;

    @NotNull
    @Temporal(TemporalType.DATE)
    private Date becameHauntedDate;

    @NotNull
    private String history;
    
    @NotNull
    private Long ownerID;
    
    @OneToMany
    private Set<Ghost> ghosts = new HashSet<>();

    @OneToMany
    private Set<Person> residents = new HashSet<>();

    // Constructors:
    public House() {
    }

    // Setters and getters:
    public void setId(Long houseID) {
        this.id = houseID;
    }
    
    public Long getId() {
        return id;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }
    
    public String getAddress() {
        return address;
    }
    
    public void setBecameHauntedDate(Date becameHauntedDate) {
        this.becameHauntedDate = becameHauntedDate;
    }
    
    public Date getBecameHauntedDate() {
        return becameHauntedDate;
    }
    
    public void setHistory(String history) {
        this.history = history;
    }

    public String getHistory() {
        return history;
    }
    
    public void setOwnerID(Long ownerID) {
        this.ownerID = ownerID;
    }
    
    public Long getOwnerID() {
        return ownerID;
    }
    
    public void setGhosts(Set<Ghost> ghosts) {
        this.ghosts = ghosts;
    }
    
    public Set<Ghost> getGhosts() {
        return Collections.unmodifiableSet(ghosts);
    }
    
    public void addGhost(Ghost ghost) {
        ghosts.add(ghost);
    }
    
    public void removeGhost(Ghost ghost) {
        this.ghosts.remove(ghost);
    }
    
    public void setResidents(Set<Person> residents) {
        this.residents = residents;
    }
    
    public Set<Person> getResidents() {
        return Collections.unmodifiableSet(residents);
    }
    
    public void addResident(Person resident) {
        residents.add(resident);
    }
    
    public void removeResident(Person resident) {
        this.residents.remove(resident);
    }
    
    @Override
    public int hashCode() {
        final int prime = 13;
        return prime + ((name == null) ? 0 : name.hashCode())
                     + ((address == null) ? 0 : address.hashCode());
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if ((object == null) || !(object instanceof House)) {
            return false;
        }
        
        House other = (House) object;
        
        if (getName() != null ? !getName().equals(other.getName()) : other.getName() != null) return false;
        return getAddress() != null ? getAddress().equals(other.getAddress()) : other.getAddress() == null;
    }

}
