package jm.task.core.jdbc;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import java.util.List;

public class Main {
    public static void main(String[] args)  {
        UserDaoHibernateImpl userDao = new UserDaoHibernateImpl();
        userDao.createUsersTable();
        userDao.saveUser("Ivan", "Dub4ak", (byte)34);
        userDao.saveUser("Aleksandr", "Akimov", (byte)31);
        userDao.saveUser("Timyr", "Vergush", (byte)40);
        userDao.saveUser("Svyatoslav", "Panifidkin", (byte)29);

        userDao.getAllUsers();
        userDao.cleanUsersTable();
        userDao.dropUsersTable();
    }
}

