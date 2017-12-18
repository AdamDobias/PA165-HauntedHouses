package fi.muni.pa165.hauntedhouses.controllers;

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

import fi.muni.pa165.hauntedhouses.ApiContract;
import fi.muni.pa165.hauntedhouses.dto.AbilityDTO;
import fi.muni.pa165.hauntedhouses.dto.GhostDTO;
import fi.muni.pa165.hauntedhouses.dto.HouseDTO;
import fi.muni.pa165.hauntedhouses.exceptions.ResourceConflict;
import fi.muni.pa165.hauntedhouses.exceptions.ResourceNotFound;
import fi.muni.pa165.hauntedhouses.exceptions.ResourceNotValid;
import fi.muni.pa165.hauntedhouses.facade.GhostFacade;

/**
 * 
 * @author Mario Majernik, 422165
 *
 */
@RestController
@RequestMapping(ApiContract.Ghost.BASE)
public class GhostController {

    @Inject
    private GhostFacade ghostFacade;

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public final void createGhost(@Valid @RequestBody GhostDTO ghost, BindingResult result) {
        if (result.hasErrors()) {
            throw new ResourceNotValid();
        }

        try {
            ghostFacade.createGhost(ghost);
        } catch (DataAccessException exception) {
            throw new ResourceConflict();
        } catch (IllegalArgumentException exception) {
            throw new ResourceNotFound();
        }
    }

    @RequestMapping(value = ApiContract.Ghost.ID, method = RequestMethod.DELETE)
    public final void deleteGhost(@PathVariable(ApiContract.Ghost.PATH_ID) long id) {
        try {
            ghostFacade.deleteGhost(id);
        } catch (DataAccessException exception) {
            throw new ResourceNotFound();
        }
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final List<GhostDTO> getAllGhosts() {
        List<GhostDTO> result = ghostFacade.findAllGhosts();

        if (result == null) {
            result = Collections.emptyList();
        }
        return result;
    }

    @RequestMapping(value = ApiContract.Ghost.ID, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final GhostDTO getGhostByID(@PathVariable(ApiContract.Ghost.PATH_ID) long id) {
        GhostDTO ghost = ghostFacade.findById(id);
        if (ghost != null) {
            return ghost;
        } else {
            throw new ResourceNotFound();
        }
    }

    @RequestMapping(value = ApiContract.Ghost.ABILITIES, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public final void giveAbilityToGhost(@Valid @RequestBody AbilityDTO ability, BindingResult result,
            @PathVariable(ApiContract.Ghost.PATH_ID) long id) {
        if (result.hasErrors()) {
            throw new ResourceNotValid();
        }

        try {
            ghostFacade.giveAbility(ghostFacade.findById(id), ability);
        } catch (DataAccessException exception) {
            throw new ResourceConflict();
        } catch (IllegalArgumentException exception) {
            throw new ResourceNotFound();
        }
    }

    @RequestMapping(value = ApiContract.Ghost.ID, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public final void removeAbilityFromGhost(@Valid @RequestBody AbilityDTO ability, BindingResult result,
            @PathVariable(ApiContract.Ghost.PATH_ID) long id) {
        if (result.hasErrors()) {
            throw new ResourceNotValid();
        }

        try {
            ghostFacade.removeAbility(ghostFacade.findById(id), ability);
        } catch (DataAccessException exception) {
            throw new ResourceConflict();
        } catch (IllegalArgumentException exception) {
            throw new ResourceNotFound();
        }
    }

    @RequestMapping(value = ApiContract.Ghost.HOUSE, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public final void setGhostToHauntInHouse(@Valid @RequestBody HouseDTO house, BindingResult result,
            @PathVariable(ApiContract.Ghost.PATH_ID) long id) {
        if (result.hasErrors()) {
            throw new ResourceNotValid();
        }

        try {
            ghostFacade.hauntHouse(house, ghostFacade.findById(id));
        } catch (DataAccessException exception) {
            throw new ResourceConflict();
        } catch (IllegalArgumentException exception) {
            throw new ResourceNotFound();
        }
    }

}
