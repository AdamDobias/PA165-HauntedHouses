package fi.muni.pa165.hauntedhouses.facade;

import fi.muni.pa165.hauntedhouses.dto.AbilityDTO;
import fi.muni.pa165.hauntedhouses.dto.GhostDTO;
import fi.muni.pa165.hauntedhouses.dto.HouseDTO;
import fi.muni.pa165.hauntedhouses.entity.Ability;
import fi.muni.pa165.hauntedhouses.entity.Ghost;
import fi.muni.pa165.hauntedhouses.entity.House;
import fi.muni.pa165.hauntedhouses.service.AbilityService;
import fi.muni.pa165.hauntedhouses.service.BeanMappingService;
import fi.muni.pa165.hauntedhouses.service.GhostService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Marek Bohm, 396257
 */

@Service
@Transactional
public class GhostFacadeImpl implements GhostFacade {

    private static final Logger log = LoggerFactory.getLogger(GhostFacadeImpl.class);

    @Inject
    private GhostService ghostService;
    
    //part of the workaround
    @Inject
    private AbilityService abilityService;

    @Inject
    private BeanMappingService beanMappingService;

    @Override
    public GhostDTO createGhost(GhostDTO ghostDTO) {
        log.debug("Creating the ghost {}", ghostDTO);
        Ghost ghost = beanMappingService.mapTo(ghostDTO, Ghost.class);
        ghostService.createGhost(ghost);
        ghostDTO.setId(ghost.getId());
        return ghostDTO;
    }

    @Override
    public void updateGhost(GhostDTO ghostDTO) {
        log.debug("Updating the ghost {}", ghostDTO);
        Ghost ghost = beanMappingService.mapTo(ghostDTO, Ghost.class);
        ghostService.updateGhost(ghost);
    }

    @Override
    public void deleteGhost(Long id) {
        log.debug("Deleting the ghost with the ID {}", id);
        Ghost ghost = beanMappingService.mapTo(findById(id), Ghost.class);
        ghostService.deleteGhost(ghost);
    }

    @Override
    public GhostDTO findById(Long id) {
        log.debug("Finding a ghost by the ID {}", id);
        return beanMappingService.mapTo(ghostService.findById(id), GhostDTO.class);
    }

    @Override
    public GhostDTO findByName(String name) {
        log.debug("Finding a ghost by the name {}", name);
        return beanMappingService.mapTo(ghostService.findByName(name), GhostDTO.class);
    }

    @Override
    public List<GhostDTO> findAllGhosts() {
        log.debug("Fetching all ghosts");
        List<Ghost> ghosts = ghostService.findAll();
        return ghosts == null ? null : beanMappingService.mapTo(ghosts, GhostDTO.class);
    }

    @Override
    public List<GhostDTO> findByAbility(AbilityDTO abilityDTO) {
        log.debug("Finding all ghosts with the ability {}", abilityDTO);
        //another workaround
        //this is the original
        //Ability ability = beanMappingService.mapTo(abilityDTO, Ability.class);
        
        return beanMappingService.mapTo(ghostService.findByAbility(abilityService.findById(abilityDTO.getId())), GhostDTO.class);
    }

    @Override
    public void giveAbility(GhostDTO ghostDTO, AbilityDTO abilityDTO) {
        log.debug("Giving the ability {} to the ghost {}", abilityDTO, ghostDTO);
        ghostService.giveAbility(
                beanMappingService.mapTo(ghostDTO, Ghost.class),
                beanMappingService.mapTo(abilityDTO, Ability.class)
        );
    }

    @Override
    public void removeAbility(GhostDTO ghostDTO, AbilityDTO abilityDTO) {
        log.debug("Removing the ability {} from the ghost {}", abilityDTO, ghostDTO);
        ghostService.removeAbility(
                beanMappingService.mapTo(ghostDTO, Ghost.class),
                beanMappingService.mapTo(abilityDTO, Ability.class)
        );
    }

    @Override
    public void hauntHouse(HouseDTO houseDTO, GhostDTO ghostDTO) {
        log.debug("Haunting the house {} with the ghost {}", houseDTO, ghostDTO);
        ghostService.hauntHouse(
                beanMappingService.mapTo(houseDTO, House.class),
                beanMappingService.mapTo(ghostDTO, Ghost.class)
        );
    }

    @Override
    public boolean isGhostHaunting(GhostDTO ghostDTO) {
        log.debug("Checking whether the ghost {} is haunting", ghostDTO);
        Ghost ghost = beanMappingService.mapTo(ghostDTO, Ghost.class);
        return ghostService.isGhostHaunting(ghost);
    }
}
