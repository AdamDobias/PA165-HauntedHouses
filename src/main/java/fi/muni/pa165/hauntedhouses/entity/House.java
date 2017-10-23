package fi.muni.pa165.hauntedhouses.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
    @Column(unique = true)
    private String address;

    @NotNull
    @Temporal(TemporalType.DATE)
    private Date becameHauntedDate;

    @NotNull
    private String history;

    // Constructors:
    public House() {
    }

    public House(Long houseID) {
        this.id = houseID;
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
    
    @Override
    public int hashCode() {
        final int prime = 13;
        return prime + ((name == null) ? 0 : name.hashCode());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if ((obj == null) || !(obj instanceof House)) {
            return false;
        }
        House other = (House) obj;
        if (name == null || address == null) {
            if ((other.getName() != null) || other.getAddress() != null) {
                return false;
            }
        } else if ((!name.equals(other.getName())) || (!address.equals(other.getAddress()))) {
            return false;
        }
        return true;
    }

}