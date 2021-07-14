package nl.hu.IPASS.domain.manager;

import nl.hu.IPASS.DAO.IDAO.EmployeeDAO;
import nl.hu.IPASS.DAO.IDAO.OwnedPokemonDAO;
import nl.hu.IPASS.DAO.IDAO.PokemonDAO;
import nl.hu.IPASS.DAO.IDAO.UserDAO;
import nl.hu.IPASS.domain.Employee;
import nl.hu.IPASS.domain.Pokemon;
import nl.hu.IPASS.domain.User;

import java.util.List;

public class PokeColManager {
    //    public static final boolean debugMode = false;
    private static final PokeColManager pcm = new PokeColManager();
    private UserDAO uDao;
    private EmployeeDAO eDao;
    private PokemonDAO pDao;
    private OwnedPokemonDAO oDao;

    public static PokeColManager getInstance() {
        return pcm;
    }

    public User createUser(String username, String password, String email, String name){
        User newUser = new User(username, password, email, name);
        uDao.save(newUser);
        return uDao.getUserByUsername(username);
    }

    public boolean changePassword(User user, String oldPassword, String newPassword){
        if (user.checkPassword(oldPassword)){
            user.setPassword(newPassword);
        }
        uDao.update(user);
        return uDao.getUserByUsername(user.getUsername()).checkPassword(newPassword);
    }

    public List<Pokemon> viewPokemonToFav(User user){
        return user.getPokemonList();
    }

    public boolean updateUser(User user,String password, String email, String name, String aboutMe){
        boolean returnBoolean = false;
        if (user.checkPassword(password)){
            user.setName(name);
            user.setAboutMe(aboutMe);
            user.setEmail(email);
            returnBoolean = true;
        }
        return returnBoolean;
    }

    public User getUserByName(String username){
        return uDao.getUserByUsername(username);
    }

    public boolean EmployeeUpdateUser(Employee employee, User user, String email, String name, String aboutMe){
        boolean returnBoolean = false;
        if (employee.getAccountType().equals("employee")){
            user.setName(name);
            user.setAboutMe(aboutMe);
            user.setEmail(email);
            returnBoolean = true;
        }
        return returnBoolean;
    }

    public boolean deleteUser(Employee employee,  User user){
        if (employee.getAccountType().equals("employee")){
            uDao.delete(user);
        }
        return !uDao.isUserSaved(user);
    }

    public boolean addPokemonToUser(User user, String pokemonName){
        Pokemon poke = pDao.getPokemonByName(pokemonName);
        if (!oDao.doesUserOwnPokemon(user, poke)){
            user.addPokemon(poke);
            oDao.save(user.getOwnedPokemon(poke));
            uDao.update(user);
        }
        return oDao.doesUserOwnPokemon(user, poke);
    }

    public boolean removePokemonFromUser(User user, String pokemonName){
        Pokemon poke = pDao.getPokemonByName(pokemonName);
        if (oDao.doesUserOwnPokemon(user, poke)){
            user.removePokemon(poke);
            oDao.delete(user.getOwnedPokemon(poke));
            uDao.update(user);
        }
        return !oDao.doesUserOwnPokemon(user, poke);
    }

    public boolean addPokemonToUsersFav(User user, String pokemonName){
        Pokemon poke = pDao.getPokemonByName(pokemonName);
        if (oDao.doesUserOwnPokemon(user, poke)){
            user.addPokemonToFav(poke);
            oDao.update(user.getOwnedPokemon(poke));
            uDao.update(user);
        }
        return oDao.doesUserHavePokemonInFav(user, poke);
    }

    public boolean removePokemonFromUsersFav(User user, String pokemonName){
        Pokemon poke = pDao.getPokemonByName(pokemonName);
        if (oDao.doesUserHavePokemonInFav(user, poke)){
            user.removePokemonFromFav(poke);
            oDao.update(user.getOwnedPokemon(poke));
            uDao.update(user);
        }
        return !oDao.doesUserHavePokemonInFav(user, poke);
    }
}
