package fi.muni.pa165.hauntedhouses.config;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import fi.muni.pa165.hauntedhouses.PersistenceApplicationContext;
import fi.muni.pa165.hauntedhouses.dto.AbilityDTO;
import fi.muni.pa165.hauntedhouses.dto.GhostDTO;
import fi.muni.pa165.hauntedhouses.dto.HouseDTO;
import fi.muni.pa165.hauntedhouses.dto.PersonDTO;
import fi.muni.pa165.hauntedhouses.entity.Ability;
import fi.muni.pa165.hauntedhouses.entity.Ghost;
import fi.muni.pa165.hauntedhouses.entity.House;
import fi.muni.pa165.hauntedhouses.entity.Person;
import fi.muni.pa165.hauntedhouses.facade.AbilityFacadeImpl;
import fi.muni.pa165.hauntedhouses.facade.GhostFacadeImpl;
import fi.muni.pa165.hauntedhouses.facade.HouseFacadeImpl;
import fi.muni.pa165.hauntedhouses.facade.PersonFacadeImpl;
import fi.muni.pa165.hauntedhouses.service.AbilityServiceImpl;
import fi.muni.pa165.hauntedhouses.service.GhostServiceImpl;
import fi.muni.pa165.hauntedhouses.service.HouseServiceImpl;
import fi.muni.pa165.hauntedhouses.service.PersonServiceImpl;
import org.dozer.loader.api.BeanMappingBuilder;

/**
 * @author Adam Dobias
 */

@Configuration
@Import(PersistenceApplicationContext.class)
@ComponentScan(basePackageClasses = {
    PersonServiceImpl.class, 
    PersonFacadeImpl.class,
    AbilityServiceImpl.class,
    AbilityFacadeImpl.class,
    GhostServiceImpl.class,
    GhostFacadeImpl.class,
    HouseServiceImpl.class,
    HouseFacadeImpl.class})
public class ServiceConfiguration {

    @Bean
    public Mapper dozer() {
        DozerBeanMapper dozer = new DozerBeanMapper();
        dozer.addMapping(new DozerCustomConfig());
        return dozer;
    }
    
    //dont know if this actually does something, might be deleted. dunno
    public class DozerCustomConfig extends BeanMappingBuilder {
		@Override
		protected void configure() {
			mapping(Ability.class, AbilityDTO.class);
			mapping(Person.class, PersonDTO.class);
			mapping(Ghost.class, GhostDTO.class);
			mapping(House.class, HouseDTO.class);
		}
	}
}
