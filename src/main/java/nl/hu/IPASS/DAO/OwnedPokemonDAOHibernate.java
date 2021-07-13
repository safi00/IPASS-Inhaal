package nl.hu.IPASS.DAO;

import nl.hu.IPASS.DAO.IDAO.OwnedPokemonDAO;
import nl.hu.IPASS.domain.OwnedPokemon;
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
public class OwnedPokemonDAOHibernate implements OwnedPokemonDAO {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public boolean save(@NotNull OwnedPokemon pokeCol) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        //noinspection TryFinallyCanBeTryWithResources
        try {
            session.save(pokeCol);
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
    public boolean update(@NotNull OwnedPokemon pokeCol) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        //noinspection TryFinallyCanBeTryWithResources
        try {
            session.update(pokeCol);
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
    public boolean delete(@NotNull OwnedPokemon pokeCol) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        //noinspection TryFinallyCanBeTryWithResources
        try {
            session.delete(pokeCol);
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
    public OwnedPokemon getOwnedPokemonByUserNameAndPokemonName() {
        return null;
    }

    @Override
    public OwnedPokemon getOwnedPokemonByUserIDAndPokeDexNumber() {
        return null;
    }

    @Override
    public List<OwnedPokemon> getAllOwnedPokemonByPokemonName(String pokemonName) {
        return null;
    }

    @Override
    public List<OwnedPokemon> getAllUserByUsername(String username) {
        return null;
    }

    @Override
    public List<OwnedPokemon> getAllUserByID(int id) {
        return null;
    }

    @Override
    public List<OwnedPokemon> getAllOwnedPokemon() {
        Session session = sessionFactory.openSession();
        //noinspection unchecked
        List<OwnedPokemon> ownedpokemon = session.createQuery("from ownedpokemon").list();
        session.close();
        return ownedpokemon;
    }
}
