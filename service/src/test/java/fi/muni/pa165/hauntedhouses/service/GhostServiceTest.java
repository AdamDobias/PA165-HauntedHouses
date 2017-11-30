package fi.muni.pa165.hauntedhouses.service;


import fi.muni.pa165.hauntedhouses.dao.AbilityDao;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import fi.muni.pa165.hauntedhouses.dao.GhostDao;
import fi.muni.pa165.hauntedhouses.dao.HouseDao;
import fi.muni.pa165.hauntedhouses.entity.Ability;
import fi.muni.pa165.hauntedhouses.entity.Ghost;
import fi.muni.pa165.hauntedhouses.entity.House;
import fi.muni.pa165.hauntedhouses.entity.Person;
import fi.muni.pa165.hauntedhouses.enums.AbilityType;
import java.util.Collection;
import java.util.Objects;
/**
 * 
 * @author Mario Majernik, 422165
 *
 */
public class GhostServiceTest extends AbstractServiceTest{
	@Mock
	private GhostDao ghostDao;
        
        @Mock
        private AbilityDao abilityDao;
        
        @Mock
        private HouseDao houseDao;
	
	@InjectMocks
	@Autowired
	private GhostServiceImpl ghostService;
	
	private Ghost ghost1;
	private Ghost ghost2;
	
	private Ability ability;
	
	private Set<Ability> setOfAbilities;
        private List<House> listOfHouses;
	private House house1;
	private House house2;
	
	private List<Ghost> listOfGhosts;
	
	private List<Ghost> emptyList = Collections.emptyList();
	
	@BeforeMethod
	public void setUp() {	
		MockitoAnnotations.initMocks(this);
		
		ability = new Ability();
		ability.setName("Scream");
		ability.setType(AbilityType.NOISE);
		ability.setDescription("Awful scream");
		
		setOfAbilities = new HashSet<>();
		setOfAbilities.add(ability);
		
		house1 = new House();
		house1.setAddress("Main 1");
		house1.setName("My House");		
		
		house2 = new House();
		house2.setAddress("Pretty street");
		house2.setName("Her house");
                
                listOfHouses = new ArrayList<>();
                listOfHouses.add(house1);
                listOfHouses.add(house2);
                
		
		ghost1 = new Ghost();
		ghost1.setName("Witch");
		ghost1.setDescription("Very powerful witch ghost");
		ghost1.setHauntFrom(new Date(2));
		ghost1.setHauntTo(new Date(4));
		ghost1.setHauntReason("Because it is witch");
		ghost1.setHouse(house1);
		ghost1.setAbilities(setOfAbilities);
		
		ghost2 = new Ghost();
		ghost2.setName("WhiteGhost");
		ghost2.setDescription("Ordinary ghost");
		ghost2.setHauntFrom(new Date(23));
		ghost2.setHauntTo(new Date(5));
		ghost2.setHauntReason("He likes to do it.");
		ghost2.setHouse(house2);
		ghost2.setAbilities(setOfAbilities);
		
		listOfGhosts = new ArrayList<>();
		listOfGhosts.add(ghost1);
		listOfGhosts.add(ghost2);	
	}
	
	@Test
    public void findAllWithEmptyListReturnsEmptyListTest() {
        when(ghostDao.findAll()).thenReturn(emptyList);

        assertThat(ghostService.findAll()).isEqualTo(emptyList);
    }
    
    @Test
    public void findAllWithNonEmptyListReturnsNonEmptyListTest() {
        when(ghostDao.findAll()).thenReturn(listOfGhosts);

        assertThat(ghostService.findAll()).isEqualTo(listOfGhosts);
    }
    
    @Test
    public void findByIdWithWrongIdReturnsNullTest() {
        Long id = 0l;
        when(ghostDao.findById(id)).thenReturn(null);
      
        assertThat(ghostService.findById(id)).isNull();
    }

    @Test
    public void findByIdWithCorrectIdReturnsGhostTest() {
        Long id = 0l;
        when(ghostDao.findById(id)).thenReturn(ghost1);
       
        assertThat(ghostService.findById(id)).isEqualTo(ghost1);
    }
    
    @Test 
    public void findByNameReturnsNullTest() {
    	String ghostName = "Witch";
        when(ghostDao.findByName(ghostName)).thenReturn(null);

        assertThat(ghostService.findByName(ghostName)).isNull();
    }
    
    @Test 
    public void findByNameReturnsGhostTest() {
    	String ghostName = "Witch";
        when(ghostDao.findByName(ghostName)).thenReturn(ghost2);

        assertThat(ghost2).isEqualTo(ghostService.findByName(ghostName));
    }
    
    @Test
    public void findByAbilityReturnsEmptyListTest() {
    	when(ghostDao.findAll()).thenReturn(emptyList); 
    	List<Ghost> ghosts = ghostService.findAll();
    	assertThat(ghosts).isEmpty();
    	
    }
    
    @Test
    public void findByAbilityReturnsGhostsTest() {
    	when(ghostDao.findAll()).thenReturn(listOfGhosts); 
    	List<Ghost> ghosts = ghostService.findAll();
    	assertThat(listOfGhosts.get(0).getAbilities()).isEqualTo(ghosts.get(0).getAbilities());
    }
    
    @Test
    public void giveAbilityToGhostTest() {
        when(ghostDao.findById(ghost1.getId())).thenReturn(ghost1);
        when(abilityDao.findById(ability.getId())).thenReturn(ability);
    	ghostService.giveAbility(ghost1, ability);
        assertThat(ghost1.getAbilities()).contains(ability);
        assertThat(ability.getGhosts()).contains(ghost1);
    	//verify(ghostDao).findById(ghost1.getId()).addAbility(ability);
    }

    @Test
    public void removeAbilityFromGhostTest() {
        when(ghostDao.findById(ghost1.getId())).thenReturn(ghost1);
        when(abilityDao.findById(ability.getId())).thenReturn(ability);
    	ghostService.removeAbility(ghost1, ability);
        assertThat(ghost1.getAbilities()).doesNotContain(ability);
        assertThat(ability.getGhosts()).doesNotContain(ghost1);
    	//verify(ghostDao).findById(ghost1.getId())/*.removeAbility(ability)*/;
    }
    
    @Test
    public void hauntHouseTest() {
        when(ghostDao.findById(ghost1.getId())).thenReturn(ghost1);
        when(houseDao.findByID(house2.getId())).thenReturn(house2);    
        House oldHouse = ghost1.getHouse();
    	ghostService.hauntHouse(house2, ghost1);
        assertThat(ghost1.getHouse()).isEqualToComparingFieldByField(house2);
        assertThat(house2.getGhosts()).contains(ghost1);
        assertThat(oldHouse.getGhosts()).doesNotContain(ghost1);
    	//verify(ghostDao).findById(ghost1.getId()).setHouse(house2);
    }

    @Test
    public void createGhostTest() {
    	ghostService.createGhost(ghost1);
    	verify(ghostDao).create(ghost1);
    }
    
    @Test
    public void updateGhostTest() {
    	ghostService.createGhost(ghost1);
    	verify(ghostDao).create(ghost1);
    	
    	ghost1.setDescription("New description");
    	
    	ghostService.updateGhost(ghost1);
    	verify(ghostDao).update(ghost1);
    }
    
    @Test
    public void deleteGhostTest() {
    	ghostService.createGhost(ghost1);
    	verify(ghostDao).create(ghost1);
    	
    	ghostService.deleteGhost(ghost1);
    	verify(ghostDao).remove(ghost1);
    }

	
}
