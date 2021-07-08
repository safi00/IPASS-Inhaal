package nl.hu.IPASS.DAO.IDAO;

import nl.hu.IPASS.domain.Pokemon;
import org.jetbrains.annotations.NotNull;

public interface PokemonDAO {
    boolean save  (@NotNull Pokemon poke);
    boolean update(@NotNull Pokemon poke);
    boolean delete(@NotNull Pokemon poke);
}
