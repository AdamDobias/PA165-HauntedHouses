package fi.muni.pa165.hauntedhouses.service;

import static com.sun.corba.se.impl.util.RepositoryId.fromHex;
import fi.muni.pa165.hauntedhouses.dao.HouseDao;
import fi.muni.pa165.hauntedhouses.dao.PersonDao;
import fi.muni.pa165.hauntedhouses.entity.House;
import fi.muni.pa165.hauntedhouses.entity.Person;
import fi.muni.pa165.hauntedhouses.enums.Role;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.inject.Inject;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

/**
 *
 * @author Adam Dobias
 */
@Service
public class PersonServiceImpl implements PersonService{

    @Inject
    private PersonDao personDao;
    
    @Inject
    private HouseDao houseDao;
    
    
    @Override
    public void registerPerson(Person p, String password) throws DataAccessException, IllegalArgumentException {
        p.setPasswordHash(createHash(password));
        personDao.create(p);
    }

    @Override
    public void removePerson(Person p) throws DataAccessException, IllegalArgumentException {
        if (p == null) {
            throw new IllegalArgumentException("cannot remove null person");
        }
        personDao.remove(p);
    }

    @Override
    public List<Person> getAllPeople() throws DataAccessException {
        return personDao.findAll();
    }

    @Override
    public boolean authenticate(Person p, String password) throws DataAccessException, IllegalArgumentException {
        return validatePassword(password, p.getPasswordHash());
    }

    @Override
    public boolean isAllowed(Person p, List<Role> acessConstraint) throws DataAccessException, IllegalArgumentException {
        if (p == null) {
            throw new IllegalArgumentException("percon cannot be null");
        }
        if (acessConstraint == null || acessConstraint.isEmpty()) {
            throw new IllegalArgumentException("acess constraint cannot be null or empty");
        }
        return acessConstraint.contains(p.getType());
    }

    @Override
    public Person findPersonById(Long id) throws DataAccessException, IllegalArgumentException {
        if (id == null) {
            throw new IllegalArgumentException("cannot search by null id");
        }
        return personDao.findById(id);
    }

    @Override
    public List<Person> findPersonByName(String name) throws DataAccessException, IllegalArgumentException {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("cannot search by empty or null name");
        }
        List<Person> result = new ArrayList<>();
        for (Person p : personDao.findAll()) {
            if (p.getName().equals(name) || p.getSurname().equals(name) || p.getLogin().equals(name)) {
                result.add(p);
            }
        }
        return result;
    }

    @Override
    public void updatePerson(Person person) throws DataAccessException, IllegalArgumentException {
        if (person == null) {
            throw new IllegalArgumentException("percon cannot be null");
        }
        personDao.update(person);
    }

    @Override
    public void inhabitHouse(House house, Person person) throws DataAccessException, IllegalArgumentException {
        if (person == null || personDao.findById(person.getId()) == null) {
            throw new IllegalArgumentException("person must be in database and must not be null");
        }
        if (person == null || houseDao.findByID(house.getId()) == null) {
            throw new IllegalArgumentException("house must be in database and must not be null");
        }
        person.setHouse(house);
    }
    
    private static String createHash(String password) {
        final int SALT_BYTE_SIZE = 24;
        final int HASH_BYTE_SIZE = 24;
        final int PBKDF2_ITERATIONS = 1000;
        // Generate a random salt
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[SALT_BYTE_SIZE];
        random.nextBytes(salt);
        // Hash the password
        byte[] hash = pbkdf2(password.toCharArray(), salt, PBKDF2_ITERATIONS, HASH_BYTE_SIZE);
        // format iterations:salt:hash
        return PBKDF2_ITERATIONS + ":" + toHex(salt) + ":" + toHex(hash);
    }

    private static byte[] pbkdf2(char[] password, byte[] salt, int iterations, int bytes) {
        try {
            PBEKeySpec spec = new PBEKeySpec(password, salt, iterations, bytes * 8);
            return SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256").generateSecret(spec).getEncoded();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean validatePassword(String password, String correctHash) {
        if(password==null) return false;
        if(correctHash==null) throw new IllegalArgumentException("password hash is null");
        String[] params = correctHash.split(":");
        int iterations = Integer.parseInt(params[0]);
        byte[] salt = fromHex(params[1]);
        byte[] hash = fromHex(params[2]);
        byte[] testHash = pbkdf2(password.toCharArray(), salt, iterations, hash.length);
        return slowEquals(hash, testHash);
    }
    
    private static byte[] fromHex(String hex) {
        byte[] binary = new byte[hex.length() / 2];
        for (int i = 0; i < binary.length; i++) {
            binary[i] = (byte) Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
        }
        return binary;
    }

    private static String toHex(byte[] array) {
        BigInteger bi = new BigInteger(1, array);
        String hex = bi.toString(16);
        int paddingLength = (array.length * 2) - hex.length();
        return paddingLength > 0 ? String.format("%0" + paddingLength + "d", 0) + hex : hex;
    }
    
    /**
     * Compares two byte arrays in length-constant time. This comparison method
     * is used so that password hashes cannot be extracted from an on-line
     * system using a timing attack and then attacked off-line.
     *
     * @param a the first byte array
     * @param b the second byte array
     * @return true if both byte arrays are the same, false if not
     */
    private static boolean slowEquals(byte[] a, byte[] b) {
        int diff = a.length ^ b.length;
        for (int i = 0; i < a.length && i < b.length; i++)
            diff |= a[i] ^ b[i];
        return diff == 0;
    }

    
}
