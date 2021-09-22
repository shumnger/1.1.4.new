package jm.task.core.jdbc;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import java.util.List;

public class Main {
    public static void main(String[] args)  {
        //* реализуйте алгоритм здесь
        UserService userService = new UserServiceImpl();

        User user1 = new User("Svetlana", "Petrova", (byte)19);
        User user2 = new User("Igor", "Sevostyanov", (byte)14);
        User user3 = new User("Petr", "Petrov", (byte)21);
        User user4 = new User("Jake", "Vazovski", (byte)24);

        userService.createUsersTable();

        userService.saveUser(user1.getName(), user1.getLastName(), user1.getAge());
        userService.saveUser(user2.getName(), user2.getLastName(), user2.getAge());
        userService.saveUser(user3.getName(), user3.getLastName(), user3.getAge());
        userService.saveUser(user4.getName(), user4.getLastName(), user4.getAge());


        List<User> allUsers = userService.getAllUsers();
        allUsers.forEach(System.out::println);
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}

