package nl.hu.IPASS.DAO;

import nl.hu.IPASS.DAO.IDAO.PokemonDAO;
import nl.hu.IPASS.domain.Pokemon;
import nl.hu.IPASS.domain.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import javax.persistence.PersistenceException;
import java.util.List;

@Repository
public class PokemonDAOHibernate implements PokemonDAO {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public boolean save(@NotNull Pokemon poke) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        //noinspection TryFinallyCanBeTryWithResources
        try {
            session.save(poke);
            transaction.commit();
            return true;
        } catch (PersistenceException exception) {
            transaction.rollback();
            Throwable cause = exception.getCause();
            while (cause != null) {
                if (cause instanceof ConstraintViolationException)
                    return false;
                cause = cause.getCause();
            }
            throw exception;
        } finally {
            session.close();
        }
    }

    @Override
    public boolean update(@NotNull Pokemon poke) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        //noinspection TryFinallyCanBeTryWithResources
        try {
            session.update(poke);
            transaction.commit();
            return true;
        } catch (PersistenceException exception) {
            transaction.rollback();
            Throwable cause = exception.getCause();
            while (cause != null) {
                if (cause instanceof ConstraintViolationException)
                    return false;
                cause = cause.getCause();
            }
            throw exception;
        } finally {
            session.close();
        }
    }

    @Override
    public boolean delete(@NotNull Pokemon poke) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        //noinspection TryFinallyCanBeTryWithResources
        try {
            session.delete(poke);
            transaction.commit();
            return true;
        } catch (PersistenceException exception) {
            transaction.rollback();
            Throwable cause = exception.getCause();
            while (cause != null) {
                if (cause instanceof ConstraintViolationException)
                    return false;
                cause = cause.getCause();
            }
            throw exception;
        } finally {
            session.close();
        }
    }

    @Override
    public Pokemon getPokemonByName(String pokemonName) {
        Session session = sessionFactory.openSession();
        Pokemon returnPok = null;
        for (Pokemon pok : getAllPokemon()){
            if (pok.getName().equals(pokemonName)){
                returnPok = pok;
            }
        }
        session.close();
        return returnPok;
    }

    @Override
    public Pokemon getPokemonByPokedexNumber(int id) {
        Session session = sessionFactory.openSession();
        Pokemon pok = session.get(Pokemon.class, id);
        session.close();
        return pok;
    }

    @Override
    public List<Pokemon> getAllPokemon() {
        Session session = sessionFactory.openSession();
        //noinspection unchecked
        List<Pokemon> pokemon = session.createQuery("from Pokemon").list();
        session.close();
        return pokemon;
    }
}
