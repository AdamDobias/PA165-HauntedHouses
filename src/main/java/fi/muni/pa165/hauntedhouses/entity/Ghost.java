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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 * 
 * @author Mario Majernik, 422165
 *
 */
@Entity
public class Ghost {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @NotNull
    @Column(nullable = false, unique = true)
    private String name;
    
    @NotNull
    @Temporal(TemporalType.TIME)
    @Column(nullable = false)
    private Date hauntFrom ;
    
    @NotNull
    @Temporal(TemporalType.TIME)
    @Column(nullable = false)
    private Date hauntTo;
    
    @NotNull
    private String description;
    
    @NotNull
    private String hauntReason;
    
    @NotNull
    @ManyToOne
    private House house;
    
    @ManyToMany
    private Set<Ability> abilities = new HashSet<>();
    
    //Constructors
    public Ghost() {
    }
    
    //Getters and setters
    public long getId() {
        return id;
    }
    
    public void setId(long id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public Date getHauntFrom() {
        return hauntFrom;
    }
    
    public void setHauntFrom(Date hauntFrom) {
        this.hauntFrom = hauntFrom;
    }
    
    public Date getHauntTo() {
        return hauntTo;
    }
    
    public void setHauntTo(Date hauntTo) {
        this.hauntTo = hauntTo;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getHauntReason() {
        return hauntReason;
    }
    
    public void setHauntReason(String hauntReason) {
        this.hauntReason = hauntReason;
    }
    
    public House getHouse() {
        return house;
    }
    
    public void setHouse(House house) {
        this.house = house;
    }
    
    public Set<Ability> getAbilities() {
        return Collections.unmodifiableSet(abilities);
    }
    
    public void setAbilities(Set<Ability> abilities) {
        this.abilities = abilities;
    }
    
    public void addAbility(Ability ability) {
        this.abilities.add(ability);
    }
    
    public void removeAbility(Ability ability) {
	this.abilities.remove(ability);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        return prime + ((hauntFrom == null) ? 0 : hauntFrom.hashCode())
                     + ((hauntTo == null) ? 0 : hauntTo.hashCode())
                     + ((name == null) ? 0 : name.hashCode());
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        
        if (obj == null || !(obj instanceof Ghost)) {
            return false;
        }
        
        Ghost other = (Ghost) obj;
        
        if (getName() != null ? !getName().equals(other.getName()) : other.getName() != null) return false;
        if (getHauntFrom() != null ? !getHauntFrom().equals(other.getHauntFrom()) : other.getHauntFrom() != null) return false;
        return getHauntTo() != null ? getHauntTo().equals(other.getHauntTo()) : other.getHauntTo() == null;
    }
}
