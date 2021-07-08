package nl.hu.IPASS.DAO.IDAO;

import nl.hu.IPASS.domain.OwnedPokemon;
import org.jetbrains.annotations.NotNull;

public interface PokeCollectionDAO {
    boolean save  (@NotNull OwnedPokemon pokeCol);
    boolean update(@NotNull OwnedPokemon pokeCol);
    boolean delete(@NotNull OwnedPokemon pokeCol);
}
