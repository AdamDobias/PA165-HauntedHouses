package fi.muni.pa165.hauntedhouses;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * TEAM 1
 * Haunted Houses
 */

public class MainClass {
    
    public static void main(String args[]) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(PersistenceApplicationContext.class);
        System.out.println("Hello PA165!");
    }
    
}
