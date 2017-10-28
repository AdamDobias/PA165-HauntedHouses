/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.muni.pa165.hauntedhouses.entity;

import fi.muni.pa165.hauntedhouses.enums.AbilityType;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Adam Dobiáš, 451044
 */
public class Ability {
    
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull
    @Column(nullable = false, unique = true)
    private String name;
    
    private String description;
    
    @NotNull
    private AbilityType type; 
    
    @ManyToMany(mappedBy = "abilities")
    private Set<Ghost> ghosts = new HashSet<>();

    // constructor
    public Ability() {
    }

    public void addGhost(Ghost ghost) {
        ghosts.add(ghost);
    }
    
    //getters, setters
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public AbilityType getType() {
        return type;
    }

    public void setType(AbilityType type) {
        this.type = type;
    }

    public Set<Ghost> getGhosts() {
        return Collections.unmodifiableSet(ghosts);
    }

    public void setGhosts(Set<Ghost> ghosts) {
        this.ghosts = ghosts;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        return prime + ((type == null) ? 0 : type.hashCode())
                     + ((name == null) ? 0 : name.hashCode());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if ((obj == null) || (obj instanceof Ability)) {
            return false;
        }

        final Ability other = (Ability) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (this.type != other.type) {
            return false;
        }
        return true;
    }

}
