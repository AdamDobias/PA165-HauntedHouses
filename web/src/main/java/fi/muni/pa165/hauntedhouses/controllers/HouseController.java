package fi.muni.pa165.hauntedhouses.controllers;

import fi.muni.pa165.hauntedhouses.ApiContract;
import fi.muni.pa165.hauntedhouses.dto.GhostDTO;
import fi.muni.pa165.hauntedhouses.dto.HouseCreateDTO;
import fi.muni.pa165.hauntedhouses.dto.HouseDTO;
import fi.muni.pa165.hauntedhouses.dto.PersonDTO;
import fi.muni.pa165.hauntedhouses.exceptions.ResourceConflict;
import fi.muni.pa165.hauntedhouses.exceptions.ResourceNotFound;
import fi.muni.pa165.hauntedhouses.exceptions.ResourceNotValid;
import fi.muni.pa165.hauntedhouses.facade.HouseFacade;
import fi.muni.pa165.hauntedhouses.facade.PersonFacade;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Klara Kufova, 410091
 */

@RestController
@RequestMapping(ApiContract.House.BASE)
public class HouseController {

    @Inject
    private HouseFacade houseFacade;
    
    @Inject
    private PersonFacade personFacade;

    @RequestMapping(value = ApiContract.House.CREATE, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public final void buildHouse(@Valid @RequestBody HouseCreateDTO house, BindingResult result) {
        if (result.hasErrors()) {
            throw new ResourceNotValid();
        }

        try {
            houseFacade.buildHouse(house);
        } catch (DataAccessException exception) {
            throw new ResourceConflict();
        } catch (IllegalArgumentException exception) {
            throw new ResourceNotFound();
        }
    }

    @RequestMapping(value = ApiContract.House.ID, method = RequestMethod.DELETE)
    public final void deleteHouse(@PathVariable(ApiContract.House.PATH_ID) long id) {
        try {
            houseFacade.deleteHouse(id);
        } catch (DataAccessException exception) {
            throw new ResourceNotFound();
        }
    }

    @RequestMapping(value = ApiContract.House.ID, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final HouseDTO getHouseByID(@PathVariable(ApiContract.House.PATH_ID) long id) {
        HouseDTO house = houseFacade.findById(id);

        if (house != null) {
            return house;
        } else {
            throw new ResourceNotFound();
        }
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final List<HouseDTO> getAllHouses() {
        List<HouseDTO> result = houseFacade.findAllHouses();

        if (result == null) {
            result = Collections.emptyList();
        }

        return result;
    }
    
    @RequestMapping(value = ApiContract.House.PEOPLE, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final List<PersonDTO> getAllPeople() {
        List<PersonDTO> result = personFacade.getAllPeople(); ;

        if (result == null) {
            result = Collections.emptyList();
        }

        return result;
    }

    @RequestMapping(value = ApiContract.House.GHOSTS, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final List<GhostDTO> getGhostsHauntingInHouse(@PathVariable(ApiContract.House.PATH_ID) long id) {
        HouseDTO house = houseFacade.findById(id);
        if (house == null) {
            throw new ResourceNotFound();
        }

        List<GhostDTO> ghosts = houseFacade.getGhosts(house);
        if (ghosts == null) {
            ghosts = Collections.emptyList();
        }

        return ghosts;
    }

    @RequestMapping(value = ApiContract.House.TENANTS, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final List<PersonDTO> getTenantsLivingInHouse(@PathVariable(ApiContract.House.PATH_ID) long id) {
        HouseDTO house = houseFacade.findById(id);
        if (house == null) {
            throw new ResourceNotFound();
        }

        List<PersonDTO> tenants = houseFacade.getInhabitants(house);
        if (tenants == null) {
            tenants = Collections.emptyList();
        }

        return tenants;
    }

    @RequestMapping(value = ApiContract.House.OWNER, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final PersonDTO getHouseOwner(@PathVariable(ApiContract.House.PATH_ID) long id) {
        HouseDTO house = houseFacade.findById(id);
        if (house == null) {
            throw new ResourceNotFound();
        }

        PersonDTO owner = houseFacade.getOwner(house);
        if (owner == null) {
            throw new ResourceNotFound();
        }

        return owner;
    }

}
