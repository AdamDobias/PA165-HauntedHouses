package fi.muni.pa165.hauntedhouses.facade;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.util.ReflectionTestUtils;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import fi.muni.pa165.hauntedhouses.dto.AbilityDTO;
import fi.muni.pa165.hauntedhouses.entity.Ability;
import fi.muni.pa165.hauntedhouses.enums.AbilityType;
import fi.muni.pa165.hauntedhouses.service.AbilityService;

/**
 *
 * @author Mario Majernik, 422165
 *
 */

public class AbilityFacadeTest extends AbstractFacadeTest {

    @Mock
    private AbilityService abilityService;

    @Autowired
    @InjectMocks
    private AbilityFacadeImpl abilityFacade;

    private Ability ability;
    private AbilityDTO abilityDTO;

    private Long abilityId = 1L;
    private String abilityName = "Fire Attack";

    @BeforeMethod
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        abilityFacade = (AbilityFacadeImpl) unwrapProxy(abilityFacade);
        ReflectionTestUtils.setField(abilityFacade, "abilityService", abilityService);
        ReflectionTestUtils.setField(abilityFacade, "beanMappingService", beanMappingService);

        ability = new Ability();
        ability.setName(abilityName);

        abilityDTO = new AbilityDTO();
        abilityDTO.setName(abilityName);
    }

    @Test
    public void testCreateAbility() {
        when(beanMappingService.mapTo(abilityDTO, Ability.class)).thenReturn(ability);
        abilityFacade.createAbility(abilityDTO);

        verify(abilityService).createAbility(ability);
        verify(beanMappingService).mapTo(abilityDTO, Ability.class);
    }

    @Test
    public void testUpdateAbility() {
        when(beanMappingService.mapTo(abilityDTO, Ability.class)).thenReturn(ability);
        abilityFacade.createAbility(abilityDTO);

        verify(abilityService).createAbility(ability);
        verify(beanMappingService).mapTo(abilityDTO, Ability.class);

        abilityDTO.setType(AbilityType.NOISE);

        abilityFacade.updateAbility(abilityDTO);
        verify(abilityService).updateAbility(ability);
        verify(beanMappingService, times(2)).mapTo(abilityDTO, Ability.class);
    }

    @Test
    public void testDeleteAbility() {
        when(beanMappingService.mapTo(abilityDTO, Ability.class)).thenReturn(ability);
        abilityFacade.createAbility(abilityDTO);

        verify(abilityService).createAbility(ability);
        verify(beanMappingService).mapTo(abilityDTO, Ability.class);

        abilityFacade.deleteAbility(abilityId);
        verify(abilityService).deleteAbility(ability);
        verify(beanMappingService.mapTo(abilityDTO, Ability.class));
    }

    @Test
    public void testFindAll() {
        when(abilityService.findAllAbilities()).thenReturn(Collections.singletonList(ability));

        List<AbilityDTO> abilities = abilityFacade.findAllAbilities();

        assertThat(ability.getName()).isEqualTo(abilities.get(0).getName());
        verify(abilityService).findAllAbilities();
        verify(beanMappingService).mapTo(Collections.singletonList(ability), AbilityDTO.class);
    }

    @Test
    public void testFindAllWithNull() {
        when(abilityService.findAllAbilities()).thenReturn(null);

        List<AbilityDTO> abilities = abilityFacade.findAllAbilities();

        assertThat(abilities).isNull();
        verify(abilityService).findAllAbilities();
        verify(beanMappingService, never()).mapTo(any(), any());
    }

    @Test
    public void testFindById() {
        when(abilityService.findById(abilityId)).thenReturn(ability);

        AbilityDTO abilityDTO = abilityFacade.findById(abilityId);

        assertThat(ability).isNotNull();
        assertThat(ability.getName()).isEqualTo(abilityDTO.getName());

        verify(abilityService).findById(abilityId);
        verify(beanMappingService).mapTo(ability, AbilityDTO.class);
    }

    @Test
    public void testFindByIdWithNull() {
        when(abilityService.findById(abilityId)).thenReturn(null);

        AbilityDTO abilityDTO = abilityFacade.findById(abilityId);

        assertThat(abilityDTO).isNull();
        verify(abilityService).findById(abilityId);
        verify(beanMappingService, never()).mapTo(any(), any());
    }

    @Test
    public void testFindByName() {
        when(abilityService.findByName(abilityName)).thenReturn(ability);

        AbilityDTO abilityDTO = abilityFacade.findByName(abilityName);

        assertThat(ability).isNotNull();
        assertThat(ability.getName()).isEqualTo(abilityDTO.getName());
        verify(abilityService).findByName(abilityName);
        verify(beanMappingService).mapTo(ability, AbilityDTO.class);
    }

    @Test
    public void testFindByNameWithNull() {
        when(abilityService.findByName(abilityName)).thenReturn(null);

        AbilityDTO abilityDTO = abilityFacade.findByName(abilityName);

        assertThat(abilityDTO).isNull();
        verify(abilityService).findByName(abilityName);
        verify(beanMappingService, never()).mapTo(any(), any());
    }
    
}
