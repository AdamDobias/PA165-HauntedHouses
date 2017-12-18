package fi.muni.pa165.hauntedhouses.controllers;

import fi.muni.pa165.hauntedhouses.ApiContract;
import fi.muni.pa165.hauntedhouses.dto.HouseDTO;
import fi.muni.pa165.hauntedhouses.dto.PersonDTO;
import fi.muni.pa165.hauntedhouses.exceptions.ResourceConflict;
import fi.muni.pa165.hauntedhouses.exceptions.ResourceNotFound;
import fi.muni.pa165.hauntedhouses.exceptions.ResourceNotValid;
import fi.muni.pa165.hauntedhouses.facade.PersonFacade;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

/**
 * @author Marek Bohm 396257
 */

@RestController
@RequestMapping(ApiContract.Person.BASE)
public class PersonController {

    @Inject
    private PersonFacade personFacade;

    @RequestMapping(method = RequestMethod.GET)
    private String getUserPrincipal() {
        List<?> authorities = new ArrayList<>(SecurityContextHolder.getContext().getAuthentication().getAuthorities());

        return "{\"username\":\"" + getLogin() + "\", \"role\":\"" + authorities.get(0) + "\"}";
    }

    private String getLogin() {
        return (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public final void registerPerson(@Valid @RequestBody PersonDTO person, BindingResult result) {
        if (result.hasErrors()) {
            throw new ResourceNotValid();
        }
        try {
            personFacade.registerPerson(person);
        } catch (DataAccessException exception) {
            throw new ResourceConflict();
        } catch (IllegalArgumentException exception) {
            throw new ResourceNotFound();
        }
    }

    @RequestMapping(value = ApiContract.Person.ID, method = RequestMethod.DELETE)
    public final void deletePerson(@PathVariable(ApiContract.Person.PATH_ID) long id) {
        try {
            personFacade.removePerson(id);
        } catch (DataAccessException exception) {
            throw new ResourceNotFound();
        }
    }

    @RequestMapping(value = ApiContract.Person.LOGIN, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final PersonDTO getPersonByLogin(@PathVariable(ApiContract.Person.PATH_ID) String login) {
        PersonDTO person = personFacade.findPersonByLogin(login);
        if (person != null) {
            return person;
        } else {
            throw new ResourceNotFound();
        }
    }

    @RequestMapping(value = ApiContract.Person.NAME, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final List<PersonDTO> getPersonByName(@PathVariable(ApiContract.Person.PATH_ID) String name) {
        List<PersonDTO> people = personFacade.findPersonByName(name);
        if (people == null) {
            people = Collections.emptyList();
        }
        return people;
    }
    
    @RequestMapping(value = ApiContract.Person.PEOPLE, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final List<PersonDTO> getAllPeople() {
        List<PersonDTO> people = personFacade.getAllPeople();
        if (people == null) {
            people = Collections.emptyList();
        }
        return people;
    }

    @RequestMapping(value = ApiContract.Person.PEOPLE, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public final void inhabitHouse(@Valid @RequestBody HouseDTO house, PersonDTO person, BindingResult result) {
        if (result.hasErrors()) {
            throw new ResourceNotValid();
        }

        try {
            personFacade.inhabitHouse(house, person);
        } catch (DataAccessException exception) {
            throw new ResourceConflict();
        } catch (IllegalArgumentException exception) {
            throw new ResourceNotFound();
        }
    }
    
}
