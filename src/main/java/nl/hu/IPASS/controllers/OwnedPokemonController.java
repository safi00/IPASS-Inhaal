package nl.hu.IPASS.controllers;

import nl.hu.IPASS.DAO.IDAO.OwnedPokemonDAO;
import nl.hu.IPASS.DAO.IDAO.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/op")
public class OwnedPokemonController {
    @Autowired
    private OwnedPokemonDAO ownedPokemonDAO;

}
