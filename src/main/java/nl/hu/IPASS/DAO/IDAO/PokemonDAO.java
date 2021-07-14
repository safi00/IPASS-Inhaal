package nl.hu.IPASS.DAO.IDAO;

import nl.hu.IPASS.domain.Pokemon;
import org.jetbrains.annotations.NotNull;
import java.util.List;

public interface PokemonDAO {
    boolean save  (@NotNull Pokemon pokemon);
    boolean update(@NotNull Pokemon pokemon);
    boolean delete(@NotNull Pokemon pokemon);
    Pokemon getPokemonByName(String pokemonName);
    Pokemon getPokemonByPokedexNumber(int id);
    List<Pokemon> getAllPokemon();
}
