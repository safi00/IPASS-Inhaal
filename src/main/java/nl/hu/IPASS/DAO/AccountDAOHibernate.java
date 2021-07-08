package nl.hu.IPASS.DAO;

import nl.hu.IPASS.DAO.IDAO.AccountDAO;
import nl.hu.IPASS.domain.Account;
import org.jetbrains.annotations.NotNull;

public class AccountDAOHibernate implements AccountDAO {
    @Override
    public boolean save(@NotNull Account account) {
        return false;
    }

    @Override
    public boolean update(@NotNull Account account) {
        return false;
    }

    @Override
    public boolean delete(@NotNull Account account) {
        return false;
    }
}
