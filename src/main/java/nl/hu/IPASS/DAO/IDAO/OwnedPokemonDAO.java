package nl.hu.IPASS.DAO.IDAO;

import nl.hu.IPASS.domain.OwnedPokemon;
import nl.hu.IPASS.domain.Pokemon;
import nl.hu.IPASS.domain.User;
import org.jetbrains.annotations.NotNull;
import java.util.List;

public interface OwnedPokemonDAO {
    boolean save  (@NotNull OwnedPokemon ownedPokemon);
    boolean update(@NotNull OwnedPokemon ownedPokemon);
    boolean delete(@NotNull OwnedPokemon ownedPokemon);
    boolean doesUserOwnPokemon(@NotNull User user, Pokemon pokemon);
    boolean doesUserHavePokemonInFav(@NotNull User user, Pokemon pokemon);
    OwnedPokemon getOwnedPokemonByUserAndPokemon(@NotNull User user, Pokemon pokemon);
    List<OwnedPokemon> getAllOwnedPokemonByPokemon(Pokemon pokemon);
    List<OwnedPokemon> getAllOwnedPokemonByUser(User user);
    List<OwnedPokemon> getAllFavPokemonByUser(User user);
    List<OwnedPokemon> getAllOwnedPokemon();
}
