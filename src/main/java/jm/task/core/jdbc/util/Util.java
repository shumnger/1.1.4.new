package jm.task.core.jdbc.util;
import jm.task.core.jdbc.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import static org.hibernate.cfg.AvailableSettings.DRIVER;

public class Util {

    private static final String URL = "jdbc:mysql://localhost:3306/users?useSSL=false";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    public Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("DB connection failed");
            e.printStackTrace();
        }
        return connection;
    }

    //Настройка для Hibernate
    private static SessionFactory sessionFactory;
    private static Properties properties = new Properties();
    private static Configuration configuration = new Configuration();
    public static SessionFactory getSessionFactory() {

        SessionFactory sessionFactory = null;

        try {
            properties.setProperty(Environment.DRIVER, "com.mysql.jdbc.Driver");
            properties.setProperty(Environment.URL, URL);
            properties.setProperty(Environment.USER, USER);
            properties.setProperty(Environment.PASS, PASSWORD);
            properties.setProperty(Environment.DIALECT, "org.hibernate.dialect.MySQL8Dialect");

            properties.setProperty(Environment.SHOW_SQL, "true");

            properties.setProperty(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");

            properties.setProperty(Environment.HBM2DDL_AUTO, "update");

            configuration.setProperties(properties);
            configuration.addAnnotatedClass(User.class);


            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties()).build();
            sessionFactory = configuration.buildSessionFactory();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return sessionFactory;
    }

    public static Session getSession() {
        return getSessionFactory().openSession();
    }
}