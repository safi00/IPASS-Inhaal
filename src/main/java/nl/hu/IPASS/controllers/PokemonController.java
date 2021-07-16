package nl.hu.IPASS.controllers;

import nl.hu.IPASS.DAO.IDAO.OwnedPokemonDAO;
import nl.hu.IPASS.DAO.IDAO.PokemonDAO;
import nl.hu.IPASS.DAO.IDAO.UserDAO;
import nl.hu.IPASS.domain.OwnedPokemon;
import nl.hu.IPASS.domain.Pokemon;
import nl.hu.IPASS.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.FormParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.AbstractMap;
import java.util.List;

@RestController
@RequestMapping("/api")
public class PokemonController {
    @Autowired
    private PokemonDAO pokemonDao;
    @Autowired
    private UserDAO userDao;
    @Autowired
    private OwnedPokemonDAO opDao;

    @PostMapping("/pokemon/add/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addPokemon(@FormParam("pokemonName") String pokemonName, @FormParam("username") String username){
    try{
        if (!username.isEmpty()&&!pokemonName.isEmpty()){
            User pokemonReceiver = userDao.getUserByUsername(username);
            if (!(pokemonReceiver == null)){
               Pokemon pokemon = pokemonDao.getPokemonByName(pokemonName);
                if (!(pokemon == null)){
                    pokemonReceiver.addPokemon(pokemon);
                    OwnedPokemon op = pokemonReceiver.getOwnedPokemon(pokemon);
                    return Response.ok(opDao.save(op)).build();
                }
            }
        }
    }catch (Exception e){
        e.printStackTrace();
        return Response.status(Response.Status.NOT_FOUND).entity(new AbstractMap.SimpleEntry<>("message", "params are not fully filled out!")).build();
    }
        return Response.status(Response.Status.NOT_FOUND).entity(new AbstractMap.SimpleEntry<>("message", "params are not fully filled out!")).build();
    }

    @PostMapping("/pokemon/remove/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response removePokemon(@FormParam("deletePokemonName") String deletePokemonName, @FormParam("deleteUsername") String deleteUsername){
        try{
            if (!deleteUsername.isEmpty() && !deletePokemonName.isEmpty()){
                User pokemonReceiver = userDao.getUserByUsername(deleteUsername);
                if (!(pokemonReceiver == null)){
                    Pokemon pokemon = pokemonDao.getPokemonByName(deletePokemonName);
                    if (!(pokemon == null)){
                        OwnedPokemon ownedPokemon = opDao.getOwnedPokemonByUserAndPokemon(pokemonReceiver, pokemon);
                        if (!(ownedPokemon == null)){
                            return Response.ok(opDao.delete(ownedPokemon)).build();
                        }
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            return Response.status(Response.Status.NOT_FOUND).entity(new AbstractMap.SimpleEntry<>("message", "params are not fully filled out!")).build();
        }
        return Response.status(Response.Status.NOT_FOUND).entity(new AbstractMap.SimpleEntry<>("message", "params are not fully filled out!")).build();
    }

    @PostMapping("/pokemon/addFav/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addPokemonToFav(@FormParam("PokemonNameFav") String deletePokemonName, @FormParam("UsernameFav") String deleteUsername){
        try{
            if (!deleteUsername.isEmpty() && !deletePokemonName.isEmpty()){
                User pokemonReceiver = userDao.getUserByUsername(deleteUsername);
                if (!(pokemonReceiver == null)){
                    Pokemon pokemon = pokemonDao.getPokemonByName(deletePokemonName);
                    if (!(pokemon == null)){
                        OwnedPokemon ownedPokemon = opDao.getOwnedPokemonByUserAndPokemon(pokemonReceiver, pokemon);
                        ownedPokemon.setFavourite(true);
                        return Response.ok(opDao.update(ownedPokemon)).build();
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            return Response.status(Response.Status.NOT_FOUND).entity(new AbstractMap.SimpleEntry<>("message", "params are not fully filled out!")).build();
        }
        return Response.status(Response.Status.NOT_FOUND).entity(new AbstractMap.SimpleEntry<>("message", "params are not fully filled out!")).build();
    }

    @PostMapping("/pokemon/removeFav/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response removePokemonFromFav(@FormParam("deletePokemonNameFav") String deletePokemonName, @FormParam("deleteUsernameFav") String deleteUsername){
        try{
            if (!deleteUsername.isEmpty() && !deletePokemonName.isEmpty()){
                User pokemonReceiver = userDao.getUserByUsername(deleteUsername);
                if (!(pokemonReceiver == null)){
                    Pokemon pokemon = pokemonDao.getPokemonByName(deletePokemonName);
                    if (!(pokemon == null)){
                        OwnedPokemon ownedPokemon = opDao.getOwnedPokemonByUserAndPokemon(pokemonReceiver, pokemon);
                        ownedPokemon.setFavourite(false);
                        return Response.ok(opDao.update(ownedPokemon)).build();
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            return Response.status(Response.Status.NOT_FOUND).entity(new AbstractMap.SimpleEntry<>("message", "params are not fully filled out!")).build();
        }
        return Response.status(Response.Status.NOT_FOUND).entity(new AbstractMap.SimpleEntry<>("message", "params are not fully filled out!")).build();
    }

    @GetMapping("/api/pokemon/view/{username}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response viewPokemon(@PathVariable String username){
        try{
            if (!username.isEmpty()){
                User pokemonOwner = userDao.getUserByUsername(username);
                if (!(pokemonOwner == null)){
                    List<OwnedPokemon> ownedPokemon = opDao.getAllOwnedPokemonByUser(pokemonOwner);
                    if (ownedPokemon.isEmpty()) {
                        return Response.ok(ownedPokemon).build();
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            return Response.status(Response.Status.NOT_FOUND).entity(new AbstractMap.SimpleEntry<>("message", "params are not fully filled out!")).build();
        }
        return Response.status(Response.Status.NOT_FOUND).entity(new AbstractMap.SimpleEntry<>("message", "params are not fully filled out!")).build();
    }
}
