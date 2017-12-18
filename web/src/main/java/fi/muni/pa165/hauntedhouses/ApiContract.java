package fi.muni.pa165.hauntedhouses;

public abstract class ApiContract {

    public static final String REST = "/rest";

    public static final class Ability {
        public static final String BASE = "/ability";
        public static final String ID = "/{id}";
        public static final String PATH_ID = "id";
        public static final String GHOSTS = "/ghosts/{id}";
        public static final String TYPES = "/types";
        public static final String CREATE = "/create";
        public static final String DELETE = "/delete/{id}";
       
    }

    public static final class Ghost {
        public static final String BASE = "/ghost";
        public static final String ID = "/{id}";
        public static final String PATH_ID = "id";
        public static final String ABILITIES = "/{id}/abilities";
        public static final String HOUSE = "/{id}/house";
    }

    public static final class House {
        public static final String BASE = "/house";
        public static final String ID = "/{id}";
        public static final String PATH_ID = "id";
        public static final String GHOSTS = "/{id}/ghosts";
        public static final String TENANTS = "/{id}/tenants";
        public static final String OWNER = "/{id}/owner";
        public static final String CREATE = "/create";
        public static final String PEOPLE = "/people";
        
    }

    public static final class Person {
        public static final String BASE = "/person";
        public static final String ID = "/{id}";
        public static final String PATH_ID = "id";
        public static final String PEOPLE = "/person/getAll";
        public static final String LOGIN = "/login";
        public static final String NAME = "/name";
    }

}
