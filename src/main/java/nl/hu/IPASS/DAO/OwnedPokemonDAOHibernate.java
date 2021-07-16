package nl.hu.IPASS.DAO;

import nl.hu.IPASS.DAO.IDAO.OwnedPokemonDAO;
import nl.hu.IPASS.domain.OwnedPokemon;
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

@SuppressWarnings("DuplicatedCode")
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
    public boolean doesUserOwnPokemon(@NotNull User user, Pokemon pokemon) {
        Session session = sessionFactory.openSession();
        boolean returnBoolean = false;
        //noinspection unchecked
        List<OwnedPokemon> ownedPokemon = session.createQuery("from OwnedPokemon where owner = " + user.getId()).list();
        for (OwnedPokemon op : ownedPokemon){
            if (op.getPokemon() == pokemon) {
                returnBoolean = true;
                break;
            }
        }
        session.close();
        return returnBoolean;
    }

    @Override
    public boolean doesUserHavePokemonInFav(@NotNull User user, Pokemon pokemon) {
        Session session = sessionFactory.openSession();
        boolean returnBoolean = false;
        //noinspection unchecked
        List<OwnedPokemon> ownedPokemon = session.createQuery("from OwnedPokemon where owner = " + user.getId()).list();
        for (OwnedPokemon op : ownedPokemon){
            if (op.getPokemon() == pokemon && op.isFavourite()) {
                returnBoolean = true;
                break;
            }
        }
        session.close();
        return returnBoolean;
    }

    @Override
    public OwnedPokemon getOwnedPokemonByUserAndPokemon(@NotNull User user, Pokemon pokemon) {
        Session session = sessionFactory.openSession();
        OwnedPokemon returnValue = null;
        //noinspection unchecked
        List<OwnedPokemon> ownedPokemon = session.createQuery("from OwnedPokemon where owner = " + user.getId() + " and pokemon = " + pokemon.getPokedexNumber()).list();
        for (OwnedPokemon op : getAllOwnedPokemon()){
            if (op.getPokemon().getPokedexNumber() == pokemon.getPokedexNumber() && op.getOwner().getId() == user.getId()){
               returnValue = op;
            }
        }
        session.close();
        return returnValue;
    }

    @Override
    public List<OwnedPokemon> getAllOwnedPokemonByPokemon(Pokemon pokemon) {
        Session session = sessionFactory.openSession();
        //noinspection unchecked
        List<OwnedPokemon> ownedPokemon = session.createQuery("from OwnedPokemon where pokemon = " + pokemon.getPokedexNumber()).list();
        session.close();
        return ownedPokemon;
    }

    @Override
    public List<OwnedPokemon> getAllOwnedPokemonByUser(User user) {
        Session session = sessionFactory.openSession();
        //noinspection unchecked
        List<OwnedPokemon> ownedPokemon = session.createQuery("from OwnedPokemon where owner =" + user.getId()).list();
        session.close();
        return ownedPokemon;
    }

    @Override
    public List<OwnedPokemon> getAllFavPokemonByUser(User user) {
        Session session = sessionFactory.openSession();
        //noinspection unchecked
        List<OwnedPokemon> ownedPokemon = session.createQuery("from OwnedPokemon where owner =" + user.getId() + " and isFavourite = true").list();
        session.close();
        return ownedPokemon;
    }


    @Override
    public List<OwnedPokemon> getAllOwnedPokemon() {
        Session session = sessionFactory.openSession();
        //noinspection unchecked
        List<OwnedPokemon> ownedPokemon = session.createQuery("from OwnedPokemon").list();
        session.close();
        return ownedPokemon;
    }
}
