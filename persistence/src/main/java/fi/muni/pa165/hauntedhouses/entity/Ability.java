package fi.muni.pa165.hauntedhouses.entity;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;

import fi.muni.pa165.hauntedhouses.enums.AbilityType;
import javax.persistence.CascadeType;

/**
 * @author Adam Dobiáš, 451044
 */

@Entity
public class Ability {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false, unique = true)
    private String name;

    private String description;

    @NotNull
    private AbilityType type;

    @ManyToMany(mappedBy = "abilities",
            cascade = CascadeType.ALL)
    private Set<Ghost> ghosts = new HashSet<>();

    // Constructors:
    
    public Ability() {
    }

    // Getters and setters:

    public void setId(Long id) {
        this.id = id;
    }
      
    public Long getId() {
        return id;
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

    public void addGhost(Ghost ghost) {
        ghosts.add(ghost);
    }

    public void removeGhost(Ghost ghost) {
        this.ghosts.remove(ghost);
    }

    @Override
    public String toString() {
        return "Ability{" + "id=" + id + 
                ", name=" + name + 
                ", description=" + description +
                ", type=" + type +  '}';
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
        if (!Objects.equals(getName(), other.getName())) {
            return false;
        }
        if (getType() != other.getType()) {
            return false;
        }
        return true;
    }

}
