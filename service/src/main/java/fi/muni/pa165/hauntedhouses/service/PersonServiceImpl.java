package fi.muni.pa165.hauntedhouses.service;

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
 * @author Adam Dobias
 */

@Service
public class PersonServiceImpl implements PersonService {

    @Inject
    private PersonDao personDao;

    @Inject
    private HouseDao houseDao;

    @Override
    public void registerPerson(Person person, String password) throws DataAccessException, IllegalArgumentException {
        person.setPasswordHash(createHash(password));
        personDao.create(person);
    }

    @Override
    public void removePerson(Person person) throws DataAccessException, IllegalArgumentException {
        if (person == null) {
            throw new IllegalArgumentException("The person cannot be null!");
        }
        personDao.remove(person);
    }

    @Override
    public List<Person> getAllPeople() throws DataAccessException {
        return personDao.findAll();
    }

    @Override
    public boolean authenticate(Person person, String password) throws DataAccessException, IllegalArgumentException {
        return person != null && validatePassword(password, person.getPassword());
    }

    @Override
    public boolean isAllowed(Person person, List<Role> acessConstraint) throws DataAccessException, IllegalArgumentException {
        if (person == null) {
            throw new IllegalArgumentException("The person cannot be null!");
        }
        if (acessConstraint == null || acessConstraint.isEmpty()) {
            throw new IllegalArgumentException("Neither of the access constraints can be null and the list of the constraints cannot be empty!");
        }
        return acessConstraint.contains(person.getRole());
    }

    @Override
    public Person findPersonById(Long id) throws DataAccessException, IllegalArgumentException {
        if (id == null) {
            throw new IllegalArgumentException("The ID cannot be null!");
        }
        return personDao.findById(id);
    }

    @Override
    public Person findPersonByLogin(String username) throws DataAccessException, IllegalArgumentException {
        if (username == null) {
            throw new IllegalArgumentException("The username cannot be null!");
        }
        return personDao.findPersonByLogin(username);
    }

    @Override
    public List<Person> findPersonByName(String name) throws DataAccessException, IllegalArgumentException {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("The name cannot be null or an empty string!");
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
            throw new IllegalArgumentException("The person cannot be null!");
        }
        personDao.update(person);
    }

    @Override
    public void inhabitHouse(House house, Person person) throws DataAccessException, IllegalArgumentException {
        if (person == null || personDao.findById(person.getId()) == null) {
            throw new IllegalArgumentException("The person must be present in the database and cannot be null!");
        }
        if (person == null || houseDao.findByID(house.getId()) == null) {
            throw new IllegalArgumentException("The house must be present in the database and cannot be null!");
        }
        person.setHouse(house);
        house.addResident(person);
    }

    private static String createHash(String password) {
        final int SALT_BYTE_SIZE = 24;
        final int HASH_BYTE_SIZE = 24;
        final int PBKDF2_ITERATIONS = 1000;

        // Generate a random salt:
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[SALT_BYTE_SIZE];
        random.nextBytes(salt);

        // Hash the password:
        byte[] hash = pbkdf2(password.toCharArray(), salt, PBKDF2_ITERATIONS, HASH_BYTE_SIZE);

        // Format "iterations:salt:hash":
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
        if (password == null) {
            return false;
        }
        if (correctHash == null) {
            throw new IllegalArgumentException("The password hash is null!");
        }
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
     * Compares two byte arrays in length-constant time. This comparison method is used so that password hashes cannot be
     * extracted from an on-line system using a timing attack and then attacked off-line.
     *
     * @param a the first byte array
     * @param b the second byte array
     * @return true if both byte arrays are the same, false otherwise
     */
    private static boolean slowEquals(byte[] a, byte[] b) {
        int diff = a.length ^ b.length;
        for (int i = 0; i < a.length && i < b.length; i++) {
            diff |= a[i] ^ b[i];
        }
        return diff == 0;
    }

}
