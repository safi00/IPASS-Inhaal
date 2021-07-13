package nl.hu.IPASS.controllers;

import nl.hu.IPASS.DAO.IDAO.PokemonDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pokemon")
public class PokemonController {
    @Autowired
    private PokemonDAO pokemonDAO;


}
