package fi.muni.pa165.hauntedhouses.facade;

import fi.muni.pa165.hauntedhouses.dto.AbilityDTO;
import fi.muni.pa165.hauntedhouses.entity.Ability;
import fi.muni.pa165.hauntedhouses.enums.AbilityType;
import fi.muni.pa165.hauntedhouses.service.AbilityService;
import fi.muni.pa165.hauntedhouses.service.BeanMappingService;

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
public class AbilityFacadeImpl implements AbilityFacade {

    private static final Logger log = LoggerFactory.getLogger(AbilityFacadeImpl.class);

    @Inject
    private AbilityService abilityService;

    @Inject
    private BeanMappingService beanMappingService;

    @Override
    public void createAbility(AbilityDTO abilityDTO) {
        log.debug("Creating the ability {}", abilityDTO);
        abilityService.createAbility(beanMappingService.mapTo(abilityDTO, Ability.class));
    }

    @Override
    public void updateAbility(AbilityDTO abilityDTO) {
        log.debug("Updating the ability {}", abilityDTO);
        abilityService.updateAbility(beanMappingService.mapTo(abilityDTO, Ability.class));
    }

    @Override
    public void deleteAbility(Long id) {
        
        log.debug("Deleting the ability with the ID {}", id);
        /*
        Ability ability = beanMappingService.mapTo(findById(id), Ability.class);
        abilityService.deleteAbility(ability);
*/
        //just a workaround
        abilityService.deleteAbility(abilityService.findById(id));
    }

    @Override
    public AbilityDTO findById(Long id) {
        log.debug("Finding an ability by the ID {}", id);
        Ability ability = abilityService.findById(id);
        return ability == null ? null : beanMappingService.mapTo(ability, AbilityDTO.class);
    }

    @Override
    public AbilityDTO findByName(String name) {
        log.debug("Finding an ability by the name {}", name);
        Ability ability = abilityService.findByName(name);
        return beanMappingService.mapTo(ability, AbilityDTO.class);
    }

    @Override
    public List<AbilityDTO> findAllAbilities() {
        log.debug("Fetching all abilities");
        List<Ability> abilities = abilityService.findAllAbilities();
        return abilities == null ? null : beanMappingService.mapTo(abilities, AbilityDTO.class);
    }
    
    @Override
    public List<AbilityType> getAbilityTypes() {
        return abilityService.getAbilityTypes();
    }
}
