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
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@SuppressWarnings("ThrowablePrintedToSystemOut")
@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserDAO userDao;

    @PostMapping("/user/create/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response register(@FormParam("username") String username,
                             @FormParam("password") String password,
                             @FormParam("email") String email,
                             @FormParam("name") String name){
    try{
        if (!username.isEmpty() && !password.isEmpty() && !email.isEmpty() && !name.isEmpty()){
            User newUser = new User(username, password, email, name);
            userDao.save(newUser);
            return Response.ok(userDao.getUserByUsername(newUser.getUsername())).build();
        }
        }catch (Exception e){
            System.out.println(e);
            return Response.status(Response.Status.NOT_FOUND).entity(new AbstractMap.SimpleEntry<>("message", "params are not fully filled out!")).build();
        }
        return Response.status(Response.Status.NOT_FOUND).entity(new AbstractMap.SimpleEntry<>("message", "params are not fully filled out!")).build();
    }

    @PostMapping("/user/change/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response changeUserInfo(@FormParam("username") String username,
                                   @FormParam("password") String password,
                                   @FormParam("name") String name,
                                   @FormParam("userAboutMe") String userAboutMe,
                                   @FormParam("userEmail") String userEmail){
        try{
            if (!username.isEmpty() && !password.isEmpty() && !name.isEmpty() && !userAboutMe.isEmpty() && !userEmail.isEmpty()){
                User user = userDao.getUserByUsername(username);
                if (!(user == null)){
                    if (user.checkPassword(password)){
                        user.setName(name);
                        user.setEmail(userEmail);
                        user.setAboutMe(userAboutMe);
                        return Response.ok(userDao.update(user)).build();
                    }
                }
            }
        }catch (Exception e){
            System.out.println(e);
            return Response.status(Response.Status.NOT_FOUND).entity(new AbstractMap.SimpleEntry<>("message", "params are not fully filled out!")).build();
        }
        return Response.status(Response.Status.NOT_FOUND).entity(new AbstractMap.SimpleEntry<>("message", "params are not fully filled out!")).build();
    }

    @PostMapping("/user/passChange/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response changePassword(@FormParam("username") String username,
                                   @FormParam("oldPassword") String oldPassword,
                                   @FormParam("newPassword1") String newPassword1,
                                   @FormParam("newPassword2") String newPassword2){
        try{
            if (!username.isEmpty() && !oldPassword.isEmpty() && !newPassword1.isEmpty() && !newPassword2.isEmpty()){
                User user = userDao.getUserByUsername(username);
                if (!(user == null)){
                    if (user.checkPassword(oldPassword)){
                        if (newPassword1.equals(newPassword2)){
                            user.setPassword(newPassword1);
                            return Response.ok(userDao.update(user)).build();
                        }
                    }
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
    public void deleteUser(@PathVariable String username) {
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
