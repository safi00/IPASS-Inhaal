package nl.hu.IPASS.controllers;

import nl.hu.IPASS.DAO.IDAO.UserDAO;

import java.util.AbstractMap;
import java.util.List;
import nl.hu.IPASS.domain.Account;
import nl.hu.IPASS.domain.Pokemon;
import nl.hu.IPASS.domain.User;
import nl.hu.IPASS.domain.manager.PokeColManager;
import nl.hu.IPASS.security.MyUserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import javax.persistence.EntityNotFoundException;
import javax.ws.rs.core.Response;

@SuppressWarnings("ThrowablePrintedToSystemOut")
@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserDAO userDao;

    @GetMapping("/user/create/")
    public Response register(@RequestParam String username,
                             @RequestParam String password,
                             @RequestParam String email,
                             @RequestParam String name){
    try{
        if (!username.isBlank() && !password.isBlank() && !email.isBlank() && !name.isBlank()){
            User user = PokeColManager.getInstance().createUser(username, password, email, name);
            if (!(user ==null)) {
                User getUser = PokeColManager.getInstance().getUserByName(user.getUsername());
                return Response.ok(getUser).build();
            }
        }
        }catch (Exception e){
            System.out.println(e);
            return Response.status(Response.Status.NOT_FOUND).entity(new AbstractMap.SimpleEntry<>("message", "params are not fully filled out!")).build();
        }
        return Response.status(Response.Status.NOT_FOUND).entity(new AbstractMap.SimpleEntry<>("message", "params are not fully filled out!")).build();
    }

    @GetMapping("/user/profile/{username}")
    public Response getUserProfile(@PathVariable String username){
        try{
            if (!username.isBlank()){
                User user = userDao.getUserByUsername(username);
                if (!(user == null)){
                    return Response.ok(user.getProfileDetails()).build();
                }
            }
        }catch (Exception e){
            System.out.println(e);
            return Response.status(Response.Status.NOT_FOUND).entity(new AbstractMap.SimpleEntry<>("message", "params are not fully filled out!")).build();
        }
        return Response.status(Response.Status.NOT_FOUND).entity(new AbstractMap.SimpleEntry<>("message", "params are not fully filled out!")).build();
    }

    @GetMapping("/user/profile/{username}/fav")
    public List<Pokemon> getUsersPokemon(Authentication authentication, @PathVariable String username){
        Account account = ((MyUserPrincipal) authentication.getPrincipal()).getAccount();
        User user = userDao.getUserByUsername(username);
        if (user == null)
            throw new EntityNotFoundException("User with username " + username + " not found!");
        if (user.getType().equals("employee"))
            throw new AccessDeniedException(account.getUsername() + " can't acces this");
        return user.getFavoritePokemonList();
    }

    @DeleteMapping("/user/delete/{username}")
    public void cancelVlucht(@PathVariable String username) {
        User user = userDao.getUserByUsername(username);
        if (user == null)
            throw new EntityNotFoundException("User with username " + username + " not found!");
        userDao.delete(user);
    }


    @GetMapping("/user/get/{username}")
    public User getUser(Authentication authentication, @PathVariable String username){
        Account employee = ((MyUserPrincipal) authentication.getPrincipal()).getAccount();
        User user = userDao.getUserByUsername(username);
        if (user == null)
            throw new EntityNotFoundException("User with username " + username + " not found!");
        if (employee.getType().equals("user"))
            throw new AccessDeniedException(employee.getUsername() + " can't acces this");
        return user;
    }

    @GetMapping("/user/getAll")
    public List<User> getAllUsers(){
        return userDao.getAllUsers();
    }
}
