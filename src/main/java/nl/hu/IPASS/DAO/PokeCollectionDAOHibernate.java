package nl.hu.IPASS.DAO;

import nl.hu.IPASS.DAO.IDAO.PokemonDAO;
import nl.hu.IPASS.domain.Pokemon;
import org.jetbrains.annotations.NotNull;

public class PokeCollectionDAOHibernate implements PokemonDAO {
    @Override
    public boolean save(@NotNull Pokemon poke) {
        return false;
    }

    @Override
    public boolean update(@NotNull Pokemon poke) {
        return false;
    }

    @Override
    public boolean delete(@NotNull Pokemon poke) {
        return false;
    }
}
