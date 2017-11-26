package config;

import fi.muni.pa165.hauntedhouses.PersistenceApplicationContext;
import fi.muni.pa165.hauntedhouses.service.PersonServiceImpl;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.dozer.loader.api.BeanMappingBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 *
 * @author Adam Dobias
 */
@Configuration
@Import(PersistenceApplicationContext.class)
//uncomment when the facade is done
//@ComponentScan(basePackageClasses = {PersonServiceImpl.class, PersonFacadeImpl.class})
public class ServiceConfiguration {
    
    @Bean
    public Mapper dozer(){
            DozerBeanMapper dozer = new DozerBeanMapper();		
            return dozer;
    }

}
