package nl.hu.IPASS.DAO.IDAO;

import nl.hu.IPASS.domain.OwnedPokemon;
import org.jetbrains.annotations.NotNull;
import java.util.List;

public interface OwnedPokemonDAO {
    boolean save  (@NotNull OwnedPokemon ownedPokemon);
    boolean update(@NotNull OwnedPokemon ownedPokemon);
    boolean delete(@NotNull OwnedPokemon ownedPokemon);
    OwnedPokemon getOwnedPokemonByUserNameAndPokemonName();
    OwnedPokemon getOwnedPokemonByUserIDAndPokeDexNumber();
    List<OwnedPokemon> getAllOwnedPokemonByPokemonName(String pokemonName);
    List<OwnedPokemon> getAllUserByUsername(String username);
    List<OwnedPokemon> getAllUserByID(int id);
    List<OwnedPokemon> getAllOwnedPokemon();
}
