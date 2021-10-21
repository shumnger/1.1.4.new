package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    private final String tableName = "users";

    public UserDaoHibernateImpl() {
    }

    @Override
    public void createUsersTable() {

        Session session = null;
        Transaction tx = null;
        try {
            session = Util.getSession();
            tx = session.beginTransaction();

            Query<User> query = session.createSQLQuery("CREATE TABLE IF NOT EXISTS `users`.`" +
                    tableName + "` (\n" +
                    "  `id` BIGINT NOT NULL AUTO_INCREMENT,\n" +
                    "  `name` VARCHAR(45) NOT NULL,\n" +
                    "  `lastname` VARCHAR(45) NOT NULL,\n" +
                    "  `age` tinyint(3) NULL,\n" +
                    "  PRIMARY KEY (`id`))\n" +
                    "ENGINE = InnoDB\n" +
                    "DEFAULT CHARACTER SET = utf8;\n")
                    .addEntity(User.class);
            query.executeUpdate();
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                tx.rollback();
                session.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void dropUsersTable() {
        Session session = null;
        Transaction tx = null;
        try {
            session = Util.getSession();
            tx = session.beginTransaction();

            Query<User> query = session.createSQLQuery("DROP TABLE IF EXISTS `users`.`"
                    + tableName + "`")
                    .addEntity(User.class);
            query.executeUpdate();
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                tx.rollback();
                session.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {

        Session session = null;
        Transaction tx = null;
        try {
            session = Util.getSession();
            tx = session.beginTransaction();
            User user = new User(name, lastName, age);
            session.save(user);
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                tx.rollback();
                session.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void removeUserById(long id) {
        Session session = null;
        Transaction tx = null;
        try {
            session = Util.getSession();
            tx = session.beginTransaction();
            User user = session.get(User.class, id);
            session.delete(user);
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                tx.rollback();
                session.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<User> getAllUsers() {

        Session session = null;
        Transaction tx = null;
        try {
            session = Util.getSession();
            tx = session.beginTransaction();
            List<User> list = session.createQuery(" FROM User").list();
            tx.commit();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                tx.rollback();
                session.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public void cleanUsersTable() {

        Session session = null;
        Transaction tx = null;
        try {
            session = Util.getSession();
            tx = session.beginTransaction();
            session.createSQLQuery("TRUNCATE USERS").executeUpdate();
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                tx.rollback();
                session.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}