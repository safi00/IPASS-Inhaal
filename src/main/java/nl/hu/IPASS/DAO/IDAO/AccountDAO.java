package nl.hu.IPASS.DAO.IDAO;

import nl.hu.IPASS.domain.Account;
import org.jetbrains.annotations.NotNull;

public interface AccountDAO {
    boolean save  (@NotNull Account account);
    boolean update(@NotNull Account account);
    boolean delete(@NotNull Account account);
}
